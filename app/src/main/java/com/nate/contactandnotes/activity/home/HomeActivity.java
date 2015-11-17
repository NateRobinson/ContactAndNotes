package com.nate.contactandnotes.activity.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gu.baselibrary.utils.NetUtils;
import com.gu.baselibrary.view.ForbidViewPager;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.activity.base.CNBaseActivity;
import com.nate.contactandnotes.fragment.ContactsFragment;
import com.nate.contactandnotes.fragment.MySettingFragment;
import com.nate.contactandnotes.fragment.NotesFragment;
import com.nate.contactandnotes.model.TabModel;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by Nate on 2015/11/17.app主页面
 */
public class HomeActivity extends CNBaseActivity {

    @ViewInject(R.id.home_activity_common_tab_layout)
    private CommonTabLayout mCommonTabLayout;
    @ViewInject(R.id.home_activity_vp)
    private ForbidViewPager mViewPager;

    private String[] titles = {"记录", "联系人", "设置"};
    private int[] iconUnselectIds = {
            R.mipmap.tab_notes_unselect, R.mipmap.tab_contacts_unselect,
            R.mipmap.tab_setting_unselect};
    private int[] iconSelectedIds = {
            R.mipmap.tab_notes_selected, R.mipmap.tab_contacts_selected,
            R.mipmap.tab_setting_selected};
    private ArrayList<CustomTabEntity> tabs = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    /**
     * 绑定布局文件
     *
     * @return id of layout resource
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.home_activity_layout;
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
        setCustomToolbar(ToolbarType.NOBACK, titles[0]);
        for (int i = 0; i < titles.length; i++) {
            tabs.add(new TabModel(titles[i], iconSelectedIds[i], iconUnselectIds[i]));
        }
        fragments.add(new NotesFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new MySettingFragment());
        mCommonTabLayout.setTabData(tabs);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(0);
        mViewPager.setScanScroll(false);//关闭viewpager的滑动功能

        //两位数 可以用来设置提示消息
        //mCommonTabLayout.showMsg(0, 55);

        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                setCustomToolbar(ToolbarType.NOBACK, titles[position]);
            }

            @Override
            public void onTabReselect(int position) {
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

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}
