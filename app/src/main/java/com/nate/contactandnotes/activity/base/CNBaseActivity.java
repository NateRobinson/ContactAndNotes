package com.nate.contactandnotes.activity.base;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gu.baselibrary.baseui.BaseActivity;
import com.nate.contactandnotes.R;

/**
 * Created by Administrator on 2015/11/16. contact and notes app base activity
 */
public abstract class CNBaseActivity extends BaseActivity {

    //公用的toolbar
    protected Toolbar mToolbar = null;
    //toolbar中的title
    protected TextView mToolbarTitle = null;

    /**
     * Toolbar类型 WITHBACK--带返回；NOBACK--没有返回
     */
    public enum ToolbarType {
        WITHBACK, NOBACK;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.common_toolbar_title);
    }


    /**
     * 根据每个页面的需求设置toolbar
     *
     * @param type  toolbar 类型
     * @param title 标题
     */
    protected void setCustomToolbar(ToolbarType type, int title) {
        if (mToolbar != null && mToolbarTitle != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(null);
            mToolbarTitle.setText(title);
            switch (type) {
                case WITHBACK:
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    break;
                case NOBACK:
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    break;
                default:
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    break;
            }
        }
    }

    /**
     * 根据每个页面的需求设置toolbar
     *
     * @param type  toolbar 类型
     * @param title 标题
     */
    protected void setCustomToolbar(ToolbarType type, String title) {
        if (mToolbar != null && mToolbarTitle != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(null);
            mToolbarTitle.setText(title);
            switch (type) {
                case WITHBACK:
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    break;
                case NOBACK:
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    break;
                default:
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    break;
            }
        }
    }
}
