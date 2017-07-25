package com.dlg.inc.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseFragment;
import com.dlg.inc.home.activity.IncHomeActivity;
import com.dlg.inc.home.view.IncHomePromptCardView;
import com.dlg.inc.home.view.IncHomeTypeView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：wangdakuan
 * 主要功能：零工市场
 * 创建时间：2017/7/24 13:23
 */
public class IncOddMarketFragment extends IncBaseFragment implements View.OnClickListener {
    private ImageView mBtnList;  //list列表按钮
    private LinearLayout mLayoutTask; //进行中按钮总布局
    private CircleImageView mImageTask; //进行中图片
    private IncHomeTypeView mHomeType; //类型
    private IncHomePromptCardView mHomeTvCard; //底部布局
    private LinearLayout mLayoutBtn;
    private ImageView mIvKefu;  //客服按钮
    private ImageView mIvMyLocation; //地址
    private ViewPager mHomeMarketPager; //显示卡片view
    private FrameLayout mLayoutFragment; //列表内容显示布局

    private FragmentManager mManager;
    private IncOddJobMarketFragment mIncOddJobMarketFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.inc_fragment_markethome, null);
        initView(inflate);
        initData();
        initListener();
        return inflate;
    }

    private void initListener() {
        mBtnList.setOnClickListener(this);
    }

    private void initData() {
    }

    private void initView(View inflate) {
        mBtnList = (ImageView) inflate.findViewById(R.id.btn_list);
        mLayoutTask = (LinearLayout) inflate.findViewById(R.id.layout_task);
        mImageTask = (CircleImageView) inflate.findViewById(R.id.image_task);
        mHomeType = (IncHomeTypeView) inflate.findViewById(R.id.home_type);
        mHomeTvCard = (IncHomePromptCardView) inflate.findViewById(R.id.home_tv_card);
        mLayoutBtn = (LinearLayout) inflate.findViewById(R.id.layout_btn);
        mIvKefu = (ImageView) inflate.findViewById(R.id.iv_kefu);
        mIvMyLocation = (ImageView) inflate.findViewById(R.id.iv_my_location);
        mHomeMarketPager = (ViewPager) inflate.findViewById(R.id.home_market_pager);
        mLayoutFragment = (FrameLayout) inflate.findViewById(R.id.layout_fragment);

        mManager = getChildFragmentManager();
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_list) {//列表
            if(mActivity instanceof IncHomeActivity){
                ((IncHomeActivity) mActivity).setMapGroupVisibility(View.GONE);
            }
            if (mIncOddJobMarketFragment == null) {
                mIncOddJobMarketFragment = new IncOddJobMarketFragment();
            }
            mManager.beginTransaction().replace(R.id.layout_fragment, mIncOddJobMarketFragment).commit();

        }
    }


    /**
     * 从列表返回地图主页
     */
    public void removeListFragment() {
        if(mActivity instanceof IncHomeActivity){
            ((IncHomeActivity) mActivity).setMapGroupVisibility(View.VISIBLE);
        }
        if (mIncOddJobMarketFragment != null) {
            mManager.beginTransaction().remove(mIncOddJobMarketFragment).commit();
            mIncOddJobMarketFragment = null;
        }
    }

}
