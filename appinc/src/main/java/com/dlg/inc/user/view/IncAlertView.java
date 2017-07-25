package com.dlg.inc.user.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.dlg.inc.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 */
public class IncAlertView {

    public enum Style {
        ActionSheet,
        Alert
    }

    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
    );
    public static final int HORIZONTAL_BUTTONS_MAXCOUNT = 2;
    public static final String OTHERS = "others";
    public static final String DESTRUCTIVE = "destructive";
    public static final String CANCEL = "cancel";
    public static final String TITLE = "title";
    public static final String MSG = "msg";
    public static final int CANCELPOSITION = -1;

    private String title;
    private String msg;
    private List<String> mDestructive;
    private List<String> mOthers;
    private String cancel;
    private ArrayList<String> mDatas = new ArrayList<>();

    private Context context;
    private ViewGroup contentContainer;
    private ViewGroup decorView;
    private ViewGroup rootView;
    private ViewGroup loAlertHeader;

    private Style style = Style.Alert;

    private IncOnDismissListener onDismissListener;
    private IncOnItemClickListener onItemClickListener;
    private boolean isDismissing;

    private Animation outAnim;
    private Animation inAnim;
    private int gravity = Gravity.CENTER;
    private boolean isChildCenter = false;
    private String selectStr = null;

    public IncAlertView(String title, String msg, String cancel, String[] destructive, String[] others,
						Context context, Style style, IncOnItemClickListener onItemClickListener) {
        this.context = context;
        if (style != null)
            this.style = style;
        this.onItemClickListener = onItemClickListener;

        initData(title, msg, cancel, destructive, others);
    }

    /**
     * 新增支持自定义布局的函数
     * @param isZDY 是否是自定义布局
     * @param isChildCenter 是否把选项文字居中显示
     * @param selectStr 如果要做保留上次选中效果，就传一个已经选中的字符串进来
     * @return
     */
    public IncAlertView setView(boolean isZDY, boolean isChildCenter, String selectStr) {
        this.isChildCenter = isChildCenter;
        this.selectStr = String.valueOf(selectStr);

        ViewGroup view = null;
        if (isZDY) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = (ViewGroup) inflater.inflate(R.layout.inc_choose_picture_dialog, null);
        }

        initViews(view);
        init();
        initEvents();

        return this;
    }

    protected void initData(String title, String msg, String cancel, String[] destructive, String[] others) {

        this.title = title;
        this.msg = msg;
        if (destructive != null) {
            this.mDestructive = Arrays.asList(destructive);
            this.mDatas.addAll(mDestructive);
        }
        if (others != null) {
            this.mOthers = Arrays.asList(others);
            this.mDatas.addAll(mOthers);
        }
        if (cancel != null) {
            this.cancel = cancel;
            if (style == Style.Alert && mDatas.size() < HORIZONTAL_BUTTONS_MAXCOUNT) {
                this.mDatas.add(0, cancel);
            }
        }
    }

    protected void initViews(ViewGroup view) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        decorView = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

        rootView = (ViewGroup) layoutInflater.inflate(R.layout.inc_layout_alertview, decorView, false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
        int margin_alert_left_right = 0;
        switch (style) {
            case ActionSheet:
                params.gravity = Gravity.BOTTOM;
                margin_alert_left_right = context.getResources().getDimensionPixelSize(R.dimen.margin_actionsheet_left_right);
                params.setMargins(0, 0, 0, 0);
//                params.setMargins(margin_alert_left_right, 0, margin_alert_left_right, margin_alert_left_right);//原来的。
                contentContainer.setLayoutParams(params);
                gravity = Gravity.BOTTOM;
                if (view == null) {
                    initActionSheetViews(layoutInflater);
                } else {
                    initMyViews(view);
                }
                break;
            case Alert:
                params.gravity = Gravity.CENTER;
                margin_alert_left_right = context.getResources().getDimensionPixelSize(R.dimen.margin_alert_left_right);
                params.setMargins(margin_alert_left_right, 0, margin_alert_left_right, 0);
                contentContainer.setLayoutParams(params);
                gravity = Gravity.CENTER;
                initAlertViews(layoutInflater);
                break;
        }
    }

    protected void initHeaderView(ViewGroup viewGroup) {
        loAlertHeader = (ViewGroup) viewGroup.findViewById(R.id.loAlertHeader);
        TextView tvAlertTitle = (TextView) viewGroup.findViewById(R.id.tvAlertTitle);
        TextView tvAlertMsg = (TextView) viewGroup.findViewById(R.id.tvAlertMsg);
        if (title != null) {
            tvAlertTitle.setText(title);
        } else {
            tvAlertTitle.setVisibility(View.GONE);
        }
        if (msg != null) {
            tvAlertMsg.setText(msg);
        } else {
            tvAlertMsg.setVisibility(View.GONE);
        }
    }

    protected void initListView() {
        ListView alertButtonListView = (ListView) contentContainer.findViewById(R.id.alertButtonListView);
        if (cancel != null && style == Style.Alert) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.inc_item_alertbutton, null);
            TextView tvAlert = (TextView) itemView.findViewById(R.id.tvAlert);
            tvAlert.setText(cancel);
            tvAlert.setClickable(true);
            tvAlert.setTypeface(Typeface.DEFAULT_BOLD);
            tvAlert.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_cancel));
            tvAlert.setBackgroundResource(R.drawable.inc_bg_alertbutton_bottom);
            tvAlert.setOnClickListener(new OnTextClickListener(CANCELPOSITION));
            alertButtonListView.addFooterView(itemView);
        }
        IncAlertViewAdapter adapter = new IncAlertViewAdapter(mDatas, mDestructive);
        alertButtonListView.setAdapter(adapter);
        alertButtonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(IncAlertView.this, position);
                dismiss();
            }
        });
    }

    protected void initMyListView() {
        ListView alertButtonListView = (ListView) contentContainer.findViewById(R.id.dialoglist);

        IncNewAlertViewAdapter adapter = new IncNewAlertViewAdapter(mDatas, selectStr, isChildCenter);
        alertButtonListView.setAdapter(adapter);
        alertButtonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(IncAlertView.this, position);
                dismiss();
            }
        });
    }

    protected void initMyViews(ViewGroup viewGroup) {
//        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.inc_layout_alertview_actionsheet, contentContainer);
//        initHeaderView(viewGroup);
        contentContainer.addView(viewGroup);

        initMyListView();
        TextView tvAlertCancel = (TextView) contentContainer.findViewById(R.id.cancle_btn);

        if (cancel != null) {
            tvAlertCancel.setVisibility(View.VISIBLE);
            tvAlertCancel.setText(cancel);
        }
        if (isChildCenter) {
            tvAlertCancel.setVisibility(View.GONE);
        }
        tvAlertCancel.setOnClickListener(new OnTextClickListener(CANCELPOSITION));
    }

    protected void initActionSheetViews(LayoutInflater layoutInflater) {
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.inc_layout_alertview_actionsheet, contentContainer);
        initHeaderView(viewGroup);

        initListView();
        TextView tvAlertCancel = (TextView) contentContainer.findViewById(R.id.tvAlertCancel);
        if (cancel != null) {
            tvAlertCancel.setVisibility(View.VISIBLE);
            tvAlertCancel.setText(cancel);
        }
        tvAlertCancel.setOnClickListener(new OnTextClickListener(CANCELPOSITION));
    }

    protected void initAlertViews(LayoutInflater layoutInflater) {

        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.inc_layout_alertview_alert, contentContainer);
        initHeaderView(viewGroup);

        int position = 0;
        if (mDatas.size() <= HORIZONTAL_BUTTONS_MAXCOUNT) {
            ViewStub viewStub = (ViewStub) contentContainer.findViewById(R.id.viewStubHorizontal);
            viewStub.inflate();
            LinearLayout loAlertButtons = (LinearLayout) contentContainer.findViewById(R.id.loAlertButtons);
            for (int i = 0; i < mDatas.size(); i++) {
                if (i != 0) {
                    View divier = new View(context);
                    divier.setBackgroundColor(context.getResources().getColor(R.color.bgColor_divier));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen.size_divier), LinearLayout.LayoutParams.MATCH_PARENT);
                    loAlertButtons.addView(divier, params);
                }
                View itemView = LayoutInflater.from(context).inflate(R.layout.inc_item_alertbutton, null);
                TextView tvAlert = (TextView) itemView.findViewById(R.id.tvAlert);
                tvAlert.setClickable(true);

                if (mDatas.size() == 1) {
                    tvAlert.setBackgroundResource(R.drawable.inc_bg_alertbutton_bottom);
                } else if (i == 0) {
                    tvAlert.setBackgroundResource(R.drawable.inc_bg_alertbutton_left);
                } else if (i == mDatas.size() - 1) {
                    tvAlert.setBackgroundResource(R.drawable.inc_bg_alertbutton_right);
                }
                String data = mDatas.get(i);
                tvAlert.setText(data);

                if (data == cancel) {
                    tvAlert.setTypeface(Typeface.DEFAULT_BOLD);
                    tvAlert.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_cancel));
                    tvAlert.setOnClickListener(new OnTextClickListener(CANCELPOSITION));
                    position = position - 1;
                } else if (mDestructive != null && mDestructive.contains(data)) {
                    tvAlert.setTextColor(context.getResources().getColor(R.color.textColor_alert_button_destructive));
                }

                tvAlert.setOnClickListener(new OnTextClickListener(position));
                position++;
                loAlertButtons.addView(itemView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            }
        } else {
            ViewStub viewStub = (ViewStub) contentContainer.findViewById(R.id.viewStubVertical);
            viewStub.inflate();
            initListView();
        }
    }

    protected void init() {
        inAnim = getInAnimation();
        outAnim = getOutAnimation();
    }

    protected void initEvents() {
    }

    public IncAlertView addExtView(View extView) {
        loAlertHeader.addView(extView);
        return this;
    }

    private void onAttached(View view) {
        decorView.addView(view);
        contentContainer.startAnimation(inAnim);
    }

    public void show() {
        if (isShowing()) {
            return;
        }
        onAttached(rootView);
    }

    public boolean isShowing() {
        View view = decorView.findViewById(R.id.outmost_container);
        return view != null;
    }

    public void dismiss() {
        if (isDismissing) {
            return;
        }

        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                decorView.post(new Runnable() {
                    @Override
                    public void run() {
                        decorView.removeView(rootView);
                        isDismissing = false;
                        if (onDismissListener != null) {
                            onDismissListener.onDismiss(IncAlertView.this);
                        }
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        contentContainer.startAnimation(outAnim);
        isDismissing = true;
    }

    public Animation getInAnimation() {
        int res = IncAlertAnimateUtil.getAnimationResource(this.gravity, true);
        return AnimationUtils.loadAnimation(context, res);
    }

    public Animation getOutAnimation() {
        int res = IncAlertAnimateUtil.getAnimationResource(this.gravity, false);
        return AnimationUtils.loadAnimation(context, res);
    }

    public IncAlertView setOnDismissListener(IncOnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    class OnTextClickListener implements View.OnClickListener {

        private int position;

        public OnTextClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(IncAlertView.this, position);
            dismiss();
        }
    }

    public void setMarginBottom(int marginBottom) {
        int margin_alert_left_right = context.getResources().getDimensionPixelSize(R.dimen.margin_alert_left_right);
        params.setMargins(margin_alert_left_right, 0, margin_alert_left_right, marginBottom);
        contentContainer.setLayoutParams(params);
    }

    public IncAlertView setCancelable(boolean isCancelable) {
        View view = rootView.findViewById(R.id.outmost_container);

        if (isCancelable) {
            view.setOnTouchListener(onCancelableTouchListener);
        } else {
            view.setOnTouchListener(null);
        }
        return this;
    }

    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                dismiss();
            }
            return false;
        }
    };
}