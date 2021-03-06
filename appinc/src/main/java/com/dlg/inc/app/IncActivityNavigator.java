package com.dlg.inc.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Iterator;
import java.util.Stack;

/**
 * 作者：wangdakuan
 * 主要功能: 全局管理activity跳转
 * 创建时间：2016/7/15 16:06
 */
public class IncActivityNavigator {
    private static Stack<AppCompatActivity> activityStack = new Stack<>();// activity栈
    private static boolean isShared = false; //是否开启共享跳转（只支持5.0）
    private static Pair<View, String> sharedElements[]; //过渡动画View
    private static IncActivityNavigator activityNavigator;

    public IncActivityNavigator() {
    }

    public static IncActivityNavigator navigator() {
        isShared = false;
        sharedElements = null;
        if (null == activityNavigator) {
            activityNavigator = new IncActivityNavigator();
        }
        return activityNavigator;
    }

    /**
     * 是否开启共享
     *
     * @param isShared
     * @return
     */
    public IncActivityNavigator setShared(boolean isShared) {
        this.isShared = isShared;
        return this;
    }

    /**
     * 共享的布局
     *
     * @param sharedElements
     * @return
     */
    public IncActivityNavigator setSharedElements(Pair<View, String>... sharedElements) {
        this.sharedElements = sharedElements;
        return this;
    }

    /**
     * @param targetActivity
     */
    public void navigateTo(@NonNull Class<? extends Activity> targetActivity) {
        navigateTo(targetActivity, new Intent());
    }

    /**
     * @param targetActivity
     */
    public void navigateTo(@NonNull Class<? extends Activity> targetActivity, int requestCode) {
        navigateTo(targetActivity, new Intent(), requestCode);
    }

    /**
     * @param targetActivity
     */
    public void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        navigateTo(targetActivity, intent);
    }

    /**
     * @param targetActivity
     */
    public void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        navigateTo(targetActivity, intent, requestCode);
    }

    /**
     * @param targetActivity
     * @param intent
     */
    public void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent, int requestCodet) {
        Activity currentActivity = getLastActivity();
        navigateTo(currentActivity, targetActivity, intent, requestCodet);
    }

    /**
     * @param targetActivity
     * @param intent
     */
    public void navigateTo(@NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent) {
        Activity currentActivity = getLastActivity();
        navigateTo(currentActivity, targetActivity, intent);
    }

    /**
     * @param context
     * @param targetActivity
     * @param intent
     */
    public void navigateTo(@NonNull Activity context, @NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent) {
        if (isShared && null != sharedElements && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            navigateMaterialTo(context, targetActivity, intent);
        } else {
            intent.setClass(context, targetActivity);
            context.startActivity(intent);
        }
    }

    /**
     * @param context
     * @param targetActivity
     * @param intent
     */
    public void navigateTo(@NonNull Activity context, @NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent, int requestCodet) {
        if (isShared && null != sharedElements && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            navigateMaterialTo(context, targetActivity, intent);
        } else {
            intent.setClass(context, targetActivity);
            context.startActivityForResult(intent, requestCodet);
        }
    }

    /**
     * 共享跳转
     *
     * @param context
     * @param targetActivity
     * @param intent
     */
    public void navigateMaterialTo(@NonNull Activity context, @NonNull Class<? extends Activity> targetActivity, @NonNull Intent intent) {
        intent.setClass(context, targetActivity);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                sharedElements);
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    /**
     * 关闭当前activity
     */
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getLastActivity().onBackPressed();
        } else {
            getLastActivity().onBackPressed();
        }
    }

    /**
     * 关闭当前activity
     */
    public void finish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getLastActivity().finish();
        } else {
            getLastActivity().finish();
        }
    }

    /**
     * 添加页面到栈管理
     *
     * @param activity 页面
     */
    public void addActivity(AppCompatActivity activity) {
        activityStack.add(activity);
    }

    /**
     * 销毁栈中的页面
     *
     * @param activity 页面
     */
    public void removerActivity(AppCompatActivity activity) {
        activityStack.remove(activity);
    }

    /**
     * 保留指定的页面  销毁其它页面
     *
     * @param classes 指定保留的页面
     */
    public void keepRemoverActivity(Stack<Class> classes) {
        Iterator<AppCompatActivity> sListIterator = activityStack.iterator();
        while (sListIterator.hasNext()) {
            AppCompatActivity act = sListIterator.next();
            boolean isRemove = false;
            for (int j = 0, sizeClass = classes.size(); j < sizeClass; j++) {
                if (act.getClass() == classes.get(j)) {
                    isRemove = true;
                    break;
                }
            }
            if (!isRemove) {
                act.finish();
                sListIterator.remove();
            }
        }
    }

    /**
     * 销毁指定页面
     *
     * @param classes 指定保留的页面
     */
    public void removerActivity(Stack<Class> classes) {
        Iterator<AppCompatActivity> sListIterator = activityStack.iterator();
        while (sListIterator.hasNext()) {
            AppCompatActivity act = sListIterator.next();
            boolean isRemove = false;
            for (int j = 0, sizeClass = classes.size(); j < sizeClass; j++) {
                if (act.getClass() == classes.get(j)) {
                    isRemove = true;
                    break;
                }
            }
            if (isRemove) {
                act.finish();
                sListIterator.remove();
            }
        }
    }

    /**
     * 保留几个
     * postion
     */
    public static void finishSpecifiedActivity(int postion) {
        if (activityStack != null) {
            while (activityStack.size() > postion) {
                AppCompatActivity activity = activityStack.get(postion);
                if (activity == null) {
                    break;
                }
                activity.finish();
                activityStack.remove(activity);
            }
        }
    }

    /**
     * 全部移除销毁
     */
    public static void removeAll() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                AppCompatActivity activity = getFirstElement();
                if (activity == null) {
                    break;
                }
                activity.finish();
                activityStack.remove(activity);
            }
        }
    }

    /**
     * 获取栈顶的activity，先进后出原则
     *
     * @return
     */
    public static AppCompatActivity getLastActivity() {
        return activityStack.lastElement();
    }

    /**
     * 获取栈顶的activity，先进后出原则
     *
     * @return
     */
    public static AppCompatActivity getLastActivity(int p) {
        if (p == 1) {
            return getLastActivity();
        }
        if (p <= activityStack.size()) {
            return activityStack.get(activityStack.size() - p);
        }
        return null;
    }

    /**
     * 获取栈底的activity，先进后出原则
     *
     * @return
     */
    public static AppCompatActivity getFirstElement() {
        return activityStack.firstElement();
    }

    public static void finishLastTwo() {
        activityStack.get(activityStack.size() - 1).finish();
        activityStack.get(activityStack.size() - 2).finish();
    }
}
