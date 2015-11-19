package com.nate.contactandnotes.activity.contact;

import android.os.Bundle;
import android.view.View;

import com.gu.baselibrary.utils.NetUtils;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.activity.base.CNBaseActivity;

import org.xutils.view.annotation.Event;

/**
 * Created by Administrator on 2015/11/19.新的朋友界面
 */
public class NewContactActivity extends CNBaseActivity {
    /**
     * 绑定布局文件
     *
     * @return id of layout resource
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.new_contact_activity_layout;
    }

    /**
     * 是否开启应用的全屏展示 安卓4.4以上可以开启APP全屏模式
     *
     * @return
     */
    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    /**
     * 是否绑定了EventBus
     *
     * @return
     */
    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    /**
     * 处理Bundle传参
     *
     * @param extras
     */
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    /**
     * @return true--自定义页面的切换动画   false--不自定义
     */
    @Override
    protected boolean isCustomPendingTransition() {
        return false;
    }

    /**
     * @return 返回自定义的动画切换方式
     */
    @Override
    protected TransitionMode getCustomPendingTransitionType() {
        return null;
    }

    /**
     * 初始化所有布局和event事件
     */
    @Override
    protected void initViewsAndEvents() {
        setCustomToolbar(ToolbarType.WITHBACK, R.string.title_add_contact_string);
    }

    /**
     * 网络连接连起来了
     *
     * @param type
     */
    @Override
    protected void doOnNetworkConnected(NetUtils.NetType type) {

    }

    /**
     * 网络连接断开
     */
    @Override
    protected void doOnNetworkDisConnected() {

    }

    @Event(value = R.id.add_new_contact_by_phone_ll)
    private void onAddByPhone(View view) {
        showToast("从手机联系人中添加");
    }

    @Event(value = R.id.add_new_contact_by_create_ll)
    private void onAddByCreate(View view) {
        showToast("手动添加");
    }

}
