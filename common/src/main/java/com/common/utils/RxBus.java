package com.common.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * 作者：wangdakuan
 * 主要功能:全局数据交互
 * 创建时间：2015/10/23 09:59
 */
public class RxBus {
    private static final String TAG = RxBus.class.getSimpleName();
    private static RxBus instance;
    public static boolean DEBUG = false;

    public static synchronized RxBus get() {
        if (null == instance) {
            instance = new RxBus();
        }
        return instance;
    }

    private RxBus() {
    }

    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    /**
     * 注册监听
     *
     * @param tag   区分接收的数据
     * @param clazz 数据类型
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Observable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        remove(tag);
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        if (DEBUG) Log.d(TAG, "[register]subjectMapper: " + subjectMapper);
        return subject;
    }

    /**
     * 注册监听
     *
     * @param tag   区分接收的数据
     * @param clazz 数据类型
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Observable<T> register2(@NonNull Object tag, @NonNull Class<T> clazz) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        if (DEBUG) Log.d(TAG, "[register]subjectMapper: " + subjectMapper);
        return subject;
    }

    /**
     * 清空
     *
     * @return
     */
    public void empty() {
        subjectMapper.clear();
    }

    /**
     * 移除一条
     *
     * @return
     */
    public void remove(@NonNull Object tag) {
        subjectMapper.remove(tag);
    }

    /**
     * 是否存在
     *
     * @return
     */
    public boolean isSubject(@NonNull Object tag) {
        return subjectMapper.containsKey(tag);
    }

    /**
     * 注销
     *
     * @param tag        根据tag注销
     * @param observable 被观察者
     */
    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove((Subject) observable);
            if (isEmpty(subjects)) {
                subjectMapper.remove(tag);
            }
        }

        if (DEBUG) Log.d(TAG, "[unregister]subjectMapper: " + subjectMapper);
    }

    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    /**
     * 传递数据
     *
     * @param tag     设置接收者的TAG
     * @param content 数据
     */
    @SuppressWarnings("unchecked")
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = subjectMapper.get(tag);

        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
        if (DEBUG) Log.d(TAG, "[send]subjectMapper: " + subjectMapper);
    }


    /**
     * 是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

}
