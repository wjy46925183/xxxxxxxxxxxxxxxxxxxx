package com.dlg.personal.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dlg.data.home.model.DictionaryBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.base.ToolBarType;
import com.dlg.personal.home.fragment.EmployeeListFragment;
import com.dlg.personal.home.fragment.KeyHotFragment;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.viewmodel.key.AppKey;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：小明
 * 主要功能：热门和关键字搜索界面
 * 创建时间：2017/6/28 0028 11:06
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {


    private KeyHotFragment keyHotFragment;//热门 关键字页面
    private EmployeeListFragment employeeListFragment;//雇员列表
    private EditText searchText;//搜索内容
    private TextView cancel;//取消健
    private FrameLayout frameLayout;
    int page;
    private List<DictionaryBean> beanhis = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_keyhot, ToolBarType.NO);
        initView();
        init();

    }

    private void init() {
        replaceFragment(page, getIntent().getExtras());
    }

    //进入页面选择跳转那个页面
    public void replaceFragment(int page, Bundle bundle) {
        if (null != bundle && page != 2) {
            page = bundle.getInt("page", 1);
        }
        if (page == 1) {
            if (keyHotFragment == null) {
                keyHotFragment = new KeyHotFragment();
            }
            addFragmentToBackStack(R.id.frame_layout, keyHotFragment, "keyHotFragment");
        }
        if (page == 2) {
//            if(employeeListFragment == null){
            employeeListFragment = new EmployeeListFragment();
//            }
            if (null != bundle) {
                employeeListFragment.setArguments(bundle);
            }
            addFragmentToBackStack(R.id.frame_layout, employeeListFragment, "employeeListFragment");
        }
    }

    private void initView() {
        searchText = (EditText) findViewById(R.id.search_text);
        cancel = (TextView) findViewById(R.id.cancel);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        cancel.setOnClickListener(this);

    }


    //软键盘监听
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction()) {
            if (TextUtils.isEmpty(searchText.getText().toString())) {
                ToastUtils.showShort(mContext, "请输入搜索内容");
                searchText.setFocusable(true);
            } else {
                DictionaryBean dictionaryBean = new DictionaryBean();
                dictionaryBean.setDataName(searchText.getText().toString().trim());
                saveData(dictionaryBean);
                Bundle bundle = new Bundle();
                bundle.putSerializable("postName", dictionaryBean.getDataName());
                replaceFragment(2, bundle);
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 保存历史数据
     */
    private void saveData(DictionaryBean data) {
        beanhis = (List<DictionaryBean>) mACache.getAsObject(AppKey.CacheKey.KEY_HOT);
        if (null != beanhis && beanhis.size() >= 10) {
            beanhis.remove(0);
        } else {
            if (null == beanhis) {
                beanhis = new ArrayList<>();
            }
        }
        List<DictionaryBean> _bean = new ArrayList<>();
        _bean.addAll(beanhis);
        if (null != _bean && _bean.size() > 0) {
            for (DictionaryBean bean : _bean) {
                if (bean.getDataName().equals(data.getDataName())) {
                    beanhis.remove(bean);
                }
            }
        }
        beanhis.add(data);
        mACache.put(AppKey.CacheKey.KEY_HOT, (ArrayList) beanhis);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (stackFragmentNum() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}

