package com.nate.contactandnotes.fragment;

import com.nate.contactandnotes.R;
import com.nate.contactandnotes.fragment.base.CNBaseFragment;

/**
 * Created by Nate on 2015/11/17.最近笔记页面
 */
public class NotesFragment extends CNBaseFragment {
    /**
     * @return Fragment绑定的布局文件id
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.notes_fragment_layout;
    }

    /**
     * 是否绑定EventBus
     */
    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    /**
     * 当用户第一次可以看到这个Fragment的时候，我们可以在里面进行一些数据的请求初始化操作
     */
    @Override
    protected void ontUserFirsVisible() {

    }

    /**
     * Fragment用户不可见的时候可以 做的事情 就是onPause中应该做的事情就放这个方法
     */
    @Override
    protected void onUserInvisible() {

    }

    /**
     * Fragment用户可见的时候，可以做的事情
     */
    @Override
    protected void onUserVisible() {

    }

    /**
     * 初始化一些布局和数据
     */
    @Override
    protected void initViewsAndEvents() {

    }
}
