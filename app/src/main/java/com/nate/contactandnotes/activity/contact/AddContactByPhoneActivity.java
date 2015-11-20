package com.nate.contactandnotes.activity.contact;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import com.gu.baselibrary.utils.NetUtils;
import com.gu.baselibrary.view.FancyIndexer;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.activity.base.CNBaseActivity;
import com.nate.contactandnotes.adapter.ContactsListViewAdapter;
import com.nate.contactandnotes.model.ContactModel;
import com.nate.contactandnotes.temp.Cheeses;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nate on 2015/11/20.从手机联系人中添加联系人
 */
public class AddContactByPhoneActivity extends CNBaseActivity {

    @ViewInject(R.id.phone_contacts_lv)
    private ListView mListView;
    @ViewInject(R.id.phone_contacts_fancy_indexer)
    private FancyIndexer mFancyIndexer;
    private List<ContactModel> contacts = new ArrayList<>();
    private ContactsListViewAdapter mAdapter;

    /**
     * 绑定布局文件
     *
     * @return id of layout resource
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.add_contact_by_phone_activity_layout;
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
        // 填充数据, 并排序
        fillAndSortData();
        mAdapter = new ContactsListViewAdapter(this, R.layout.contacts_fragment_item_layout, contacts);
        mListView.setAdapter(mAdapter);
        mFancyIndexer.setOnTouchLetterChangedListener(new FancyIndexer.OnTouchLetterChangedListener() {
            @Override
            public void onTouchLetterChanged(String letter) {
                //如果为向上的箭头，直接滑到最顶端
                if (TextUtils.equals("↑", letter)) {
                    mListView.setSelection(0);
                    return;
                }
                // 从集合中查找第一个拼音首字母为letter的索引, 进行跳转
                for (int i = 0; i < contacts.size(); i++) {
                    ContactModel contactModel = contacts.get(i);
                    String s = contactModel.getPinyin().charAt(0) + "";
                    if (TextUtils.equals(s, letter)) {
                        // 匹配成功, 中断循环, 跳转到i位置
                        mListView.setSelection(i);//因为这里加入了headview
                        break;
                    }
                }
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

    /**
     * 填充,排序
     */
    private void fillAndSortData() {
        boolean china = getResources().getConfiguration().locale.getCountry().equals("CN");
        String[] datas = china ? Cheeses.NAMES : Cheeses.sCheeseStrings;
        for (int i = 0; i < datas.length; i++) {
            contacts.add(new ContactModel(datas[i]));
        }
        if (contacts.size() > 0) {
            // 排序
            Collections.sort(contacts);
        }
    }
}
