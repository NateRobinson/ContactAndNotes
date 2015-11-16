package com.nate.contactandnotes.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.gu.baselibrary.utils.NetUtils;
import com.gu.baselibrary.view.MaterialLockView;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.activity.base.CNBaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

public class MainActivity extends CNBaseActivity {


    @ViewInject(R.id.correct_pattern_edittext)
    private EditText correct_pattern_edittext;
    @ViewInject(R.id.pattern)
    private MaterialLockView materialLockView;
    @ViewInject(R.id.stealthmode)
    private CheckBox stealthmode;
    private String CorrectPattern;

    /**
     * 绑定布局文件
     *
     * @return id of layout resource
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
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
        return true;
    }

    /**
     * @return 返回自定义的动画切换方式
     */
    @Override
    protected TransitionMode getCustomPendingTransitionType() {
        return TransitionMode.SCALE;
    }

    /**
     * 初始化所有布局和event事件
     */
    @Override
    protected void initViewsAndEvents() {
        materialLockView.setOnPatternListener(new MaterialLockView.OnPatternListener() {
            @Override
            public void onPatternDetected(List<MaterialLockView.Cell> pattern, String SimplePattern) {
                Log.e("guxuewu", SimplePattern);
                if (!SimplePattern.equals(CorrectPattern))
                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Wrong);
                else
                    materialLockView.setDisplayMode(MaterialLockView.DisplayMode.Correct);
                super.onPatternDetected(pattern, SimplePattern);
            }
        });
        stealthmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                materialLockView.setInStealthMode(isChecked);
            }
        });
        correct_pattern_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CorrectPattern = "" + s;
            }

            @Override
            public void afterTextChanged(Editable s) {

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
