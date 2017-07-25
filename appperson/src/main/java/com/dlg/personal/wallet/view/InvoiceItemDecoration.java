package com.dlg.personal.wallet.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者：邹前坤
 * 主要功能：处理一下分割线
 * 创建时间： 2017/7/11  16:25
 */
@Deprecated
public class InvoiceItemDecoration extends RecyclerView.ItemDecoration {
	/**
	 *
	 * @param outRect 边界
	 * @param view recyclerView ItemView
	 * @param parent recyclerView
	 * @param state recycler 内部数据管理
	 */
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		//设定底部边距为1px
		outRect.set(0, 0, 0, 1);
	}
}
