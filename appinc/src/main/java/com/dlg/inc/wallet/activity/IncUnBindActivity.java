package com.dlg.inc.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.dlg.data.wallet.model.BindBean;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.inc.wallet.adapter.IncUnBindAdapter;
import com.dlg.viewmodel.Wallet.CashViewModel;
import com.dlg.viewmodel.Wallet.presenter.CashPresenter;
import com.dlg.viewmodel.common.UnBindViewModel;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：关蕤
 * 主要功能：解绑页面
 * 创建时间：2017/7/13 16:10
 */
public class IncUnBindActivity extends IncBaseActivity implements CashPresenter,IncUnBindAdapter.unBindClickListener, SuccessObjectPresenter {
    private ListView mLvBind;
    private CashViewModel viewModel ;
    private List<BindBean> bindBeanList = new ArrayList<>();
    private IncUnBindAdapter adapter ;
    private UnBindViewModel mUnBindViewModel; //解除绑定
    private int position ;  //解绑位置

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc_page_unbind, IncToolBarType.Default);
        mToolBarHelper.getToolbarTitle().setText("编辑");
        initView();
        initData();
    }

    /**
     * 页面控件初始化
     */
    private void initView() {
        mLvBind = (ListView) findViewById(R.id.lv_bind);
    }

    /**
     * 变量初始化
     */
    private void initData() {
        viewModel = new CashViewModel(this,this,this);
        mUnBindViewModel = new UnBindViewModel(this,this,this);
        viewModel.getBindList();
        adapter = new IncUnBindAdapter(bindBeanList,mContext,this);
        mLvBind.setAdapter(adapter);

        getToolBarHelper().getToolbarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void toMap(BindBean bean) {
        bindBeanList.add(bean);
        adapter.notifyDataSetChanged();
    }

    /**
     * 解绑操作
     * @param position
     */
    @Override
    public void unBind(int position) {
        this.position = position ;
        mUnBindViewModel.unBind(bindBeanList.get(position).getId());
    }

    /**
     * 解绑成功
     * @param success
     */
    @Override
    public void onSuccess(boolean success) {
        if(success){
            IncToastUtils.showLong(mContext,"解绑成功");
            bindBeanList.remove(position);
            adapter.notifyDataSetChanged();
        }
    }
}
