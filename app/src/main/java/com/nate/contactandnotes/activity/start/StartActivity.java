package com.nate.contactandnotes.activity.start;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.gu.baselibrary.utils.NetUtils;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.activity.base.CNBaseActivity;
import com.nate.contactandnotes.activity.home.HomeActivity;


/**
 * Created by Nate on 2015/11/17.
 */
public class StartActivity extends CNBaseActivity {

    private static final int GO_TO_GESTURE_PASSWORD_ACTIVITY = 1;
    private static final int DELAY_TIME = 800;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_TO_GESTURE_PASSWORD_ACTIVITY:
                    goThenKill(HomeActivity.class);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 绑定布局文件
     *
     * @return id of layout resource
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.start_activity_layout;
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
        mHandler.sendEmptyMessageDelayed(GO_TO_GESTURE_PASSWORD_ACTIVITY, DELAY_TIME);
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
}
