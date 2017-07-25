package com.dlg.inc.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.string.LogUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.wallet.model.BankBean;
import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.inc.wallet.adapter.BankListAdapter;
import com.dlg.viewmodel.Wallet.SelectBankViewModel;
import com.dlg.viewmodel.Wallet.presenter.SelectBankPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：邹前坤
 * 主要功能：
 * 创建时间： 2017/7/21  16:44
 */

public class IncSelectBankActivity extends IncBaseActivity implements SelectBankPresenter {
	private LinearLayout mLayoutTitle;
	private EditText mSearchText;//
	private TextView mCancel;//取消
	private RecyclerView mLvBankList;//集合
	private SelectBankViewModel viewModel;
	private List<BankBean>data=new ArrayList<>();
	private BankListAdapter adapter;
	private List<BankBean> seData;
	private int beanType=0;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inc_page_select_bank, IncToolBarType.NO);

		initView();
		initData();
	}



	private void initView() {
		mLayoutTitle = (LinearLayout) findViewById(R.id.layout_title);
		mSearchText = (EditText) findViewById(R.id.search_text);
		mCancel = (TextView) findViewById(R.id.cancel);
		mLvBankList = (RecyclerView) findViewById(R.id.lv_bank_list);
		mLvBankList.setLayoutManager(new LinearLayoutManager(mContext));
	}

	/**
	 *初始化数据
	 */
	private void initData() {
		viewModel=new SelectBankViewModel(this,this,this);
		viewModel.getBank("");
		adapter = new BankListAdapter(this,mLvBankList,data, R.layout.inc_item_bank_list);
		mLvBankList.setAdapter(adapter);
		adapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				IncToastUtils.showShort(IncSelectBankActivity.this,"点击了"+position);
				Intent intent =new Intent();
				if (beanType==0){
					intent.putExtra("bean",data.get(position));
				}else {
					intent.putExtra("bean",seData.get(position));
				}

				setResult(RESULT_OK,intent);
				finish();
			}
		});

		mSearchText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String trim = mSearchText.getText().toString().trim();
				filterData(trim);
			}
		});
	}

	/**
	 * 监听文字改变 过滤银行
	 * @param trim
	 */
	private void filterData(String trim) {
		if (TextUtils.isEmpty(trim)){
			return;
		}
		seData = new ArrayList<>();
		beanType = 1;
		for (int i = 0; i <data.size() ; i++) {
			if (data.get(i).getBankName().contains(trim)){
				seData.add(data.get(i));
			}
		}

		adapter.setData(seData);
	}

	@Override
	public void getBank(List<BankBean> been) {
		LogUtils.d("得到了银行数据 "+been.size());
		data.clear();
		data.addAll(been);
		adapter.notifyDataSetChanged();
	}
}
