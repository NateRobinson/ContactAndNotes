package com.nate.contactandnotes.activity.start;

import android.os.Bundle;

import com.gu.baselibrary.utils.NetUtils;
import com.gu.baselibrary.view.MaterialLockView;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.activity.base.CNBaseActivity;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by Nate on 2015/11/17  手势密码验证页面
 */
public class GesturePasswordActivity extends CNBaseActivity {

    @ViewInject(R.id.gesture_password_view)
    private MaterialLockView materialLockView;
    private String CorrectPattern = "123";

    /**
     * 绑定布局文件
     *
     * @return id of layout resource
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.gesture_password_activity_layout;
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
        setCustomToolbar(ToolbarType.WITHBACK, R.string.title_gesture_password_string);
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {
            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                if (!SimplePattern.equals(CorrectPattern))
                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Wrong);
                else
                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                super.onPatternDetected(pattern, SimplePattern);
            }
        });
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
