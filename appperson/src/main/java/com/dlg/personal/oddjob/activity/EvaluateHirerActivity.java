package com.dlg.personal.oddjob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.DialogUtils;
import com.dlg.data.home.model.DictionaryBean;
import com.dlg.data.oddjob.model.BaseOddDetailBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.oddjob.adapter.PJWordsAdapter;
import com.dlg.personal.oddjob.view.EmployeePublicView;
import com.dlg.personal.oddjob.view.HirerPublicView;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.home.DictionaryViewModel;
import com.dlg.viewmodel.home.presenter.DictionaryPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.key.UserRole;
import com.dlg.viewmodel.oddjob.GoToEvaluteViewModel;
import com.dlg.viewmodel.oddjob.presenter.GoToEvaluatePresenter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 作者：王进亚
 * 主要功能：雇主评价
 * 创建时间：2017/7/11 16:41
 */

public class EvaluateHirerActivity extends BaseActivity implements DictionaryPresenter, GoToEvaluatePresenter {

    private HirerPublicView mPublicView;
    private EmployeePublicView mEmployeePublicView; //雇员控件
    private RatingBar mStarbar;
    private TextView mPingfenDetailText;
    private GridView mPfWordsGridview;
    private EditText mDescEdit;
    private Button mEvaluateBtn;
    private List<DictionaryBean> starTexts = new ArrayList<>();
    private List<DictionaryBean> evaluatItems = new ArrayList<>();
    private PJWordsAdapter adapter;
    private int rating1 = 1;
    private SweetAlertDialog sweetAlertDialog;
    private GoToEvaluteViewModel goToEvaluteViewModel;
    private DictionaryViewModel dictionaryViewModel;
    private DictionaryViewModel starViewModel;

    private BaseOddDetailBean mOrderDetailBean; //入传递的参数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_evaluate);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            mOrderDetailBean = (BaseOddDetailBean) bundle.getSerializable("DetailBean");
        }
        initView();
    }

    private void initView() {
        getToolBarHelper().getToolbarTitle().setText("评价");
        mPublicView = (HirerPublicView) findViewById(R.id.public_view);
        mEmployeePublicView = (EmployeePublicView) findViewById(R.id.employee_view);
        mStarbar = (RatingBar) findViewById(R.id.starbar);
        mPingfenDetailText = (TextView) findViewById(R.id.pingfen_detail_text);
        mPfWordsGridview = (GridView) findViewById(R.id.grid_view);
        mDescEdit = (EditText) findViewById(R.id.desc_edit);
        mEvaluateBtn = (Button) findViewById(R.id.evaluate_btn);


        mPublicView.getCheckBox().setVisibility(View.GONE);
        adapter = new PJWordsAdapter(this, evaluatItems);
        mPfWordsGridview.setAdapter(adapter);

        final int role = (int) mACache.getAsObject(AppKey.CacheKey.USER_ROLE);
        if (role == UserRole.hirer.getRole()) {//用户角色 1 雇员 2 雇主 3 代理商
            if (null != mOrderDetailBean) {
                mPublicView.setContentEmployee(mOrderDetailBean);
            }
            mPublicView.setVisibility(View.VISIBLE);
            dictionaryViewModel = new DictionaryViewModel(this, this, this);
            dictionaryViewModel.getDictionaryData("employer.evaluation", "0");//雇主评价标签

            starViewModel = new DictionaryViewModel(this, this, this);
            starViewModel.getDictionaryData("employer.evaluation.describe", "0");//星星评价语
        } else if (role == UserRole.employee.getRole()) {
            if (null != mOrderDetailBean) {
                mEmployeePublicView.setContent(mOrderDetailBean);
            }
            mEmployeePublicView.setVisibility(View.VISIBLE);
            //雇员
            dictionaryViewModel = new DictionaryViewModel(this, this, this);
            dictionaryViewModel.getDictionaryData("employee.evaluation", "0");//雇主评价标签

            starViewModel = new DictionaryViewModel(this, this, this);
            starViewModel.getDictionaryData("employee.evaluation.describe", "0");//星星评价语
        } else {
            //代理商
        }
        mStarbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating1 = (int) rating;
                evaluate(rating1);
            }
        });
        mEvaluateBtn.setOnClickListener(new View.OnClickListener() {//确认评价
            @Override
            public void onClick(View v) {
                dialog = DialogUtils.loadingProgressDialog(mContext);
                String des = "";
                if (!TextUtils.isEmpty(mDescEdit.getText())) {
                    des = mDescEdit.getText().toString();
                }
                if (goToEvaluteViewModel != null) {
                    goToEvaluteViewModel.onDestroy();
                }
                goToEvaluteViewModel = new GoToEvaluteViewModel(mContext, new BasePresenter() {
                    @Override
                    public void requestStart() {

                    }

                    @Override
                    public void requestComplete() {
                        if (null != dialog) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void requestNext(String msg) {
                        startActivity(new Intent(EvaluateHirerActivity.this, OddActivity.class));
                    }

                    @Override
                    public void requestError(String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        if (null != dialog) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void logInError() {

                    }
                }, EvaluateHirerActivity.this);
                if (role == UserRole.hirer.getRole()) {
                    goToEvaluteViewModel.goToEvaluate(mOrderDetailBean.getBusinessNumber(), mOrderDetailBean.getEmployeeId()
                            , mOrderDetailBean.getEmployerId(), des, adapter.getTabsFromView(), rating1 + "");
                } else {
                    goToEvaluteViewModel.goToEvaluate(mOrderDetailBean.getBusinessNumber(), mOrderDetailBean.getEmployerId()
                            , mOrderDetailBean.getEmployeeId(), des, adapter.getTabsFromView(), rating1 + "");
                }
            }
        });
    }

    /**
     * 请求成功会回掉
     *
     * @param dictionaryBean
     */
    @Override
    public void getDictionary(List<DictionaryBean> dictionaryBean) {
        if (dictionaryBean != null && dictionaryBean.size() > 0) {
            switch (dictionaryBean.get(0).getGroupCode()) {
                case "employer.evaluation": //雇主评价标签
                case "employee.evaluation": //雇员评价标签
                    evaluatItems.clear();
                    evaluatItems.addAll(dictionaryBean);
                    break;
                case "employer.evaluation.describe"://雇主星星个数对应的描述
                case "employee.evaluation.describe"://雇员星星个数对应的描述
                    starTexts.clear();
                    starTexts.addAll(dictionaryBean);
                    evaluate(1);//默认描述是1星的描述
                    break;
            }
        }

    }

    /**
     * 评价标签 处理
     *
     * @param ratingStarNum
     */
    private void evaluate(int ratingStarNum) {
        for (int i = 0; i < starTexts.size(); i++) {
            DictionaryBean dictionaryBean = starTexts.get(i);
            String specialIdentification = dictionaryBean.getSpecialIdentification();
            if (specialIdentification.equals(ratingStarNum + "")) {
                switch (ratingStarNum) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        adapter.setEvaluateItems(getGoodOrBadEvalutes(false));//差评
                        mPingfenDetailText.setText(dictionaryBean.getDataValue());
                        break;
                    case 5:
                        adapter.setEvaluateItems(getGoodOrBadEvalutes(true));//好评
                        mPingfenDetailText.setText(dictionaryBean.getDataValue());
                        break;
                }
                break;
            }
        }
    }

    /**
     * 获取差评或者好评的标签
     *
     * @param isGood
     * @return
     */
    private List<DictionaryBean> getGoodOrBadEvalutes(boolean isGood) {
        List<DictionaryBean> list = new ArrayList<>();
        for (int i = 0; i < evaluatItems.size(); i++) {
            if (isGood) {
                if (evaluatItems.get(i).getSpecialIdentification().equals("5")) {
                    list.add(evaluatItems.get(i));
                }
            } else {
                if (evaluatItems.get(i).getSpecialIdentification().equals("1")) {
                    list.add(evaluatItems.get(i));
                }
            }

        }
        return list;
    }

    @Override
    public void gotoEvaluate(String evaluates) {
//        startActivity(new Intent(this, OddActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (goToEvaluteViewModel != null) {
            goToEvaluteViewModel.onDestroy();
        }
        if (dictionaryViewModel != null) {
            dictionaryViewModel.onDestroy();
        }
        if (starViewModel != null) {
            starViewModel.onDestroy();
        }
    }
}
