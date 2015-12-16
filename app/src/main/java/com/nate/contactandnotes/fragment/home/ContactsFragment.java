package com.nate.contactandnotes.fragment.home;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.gu.baselibrary.view.FancyIndexer;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.activity.contact.NewContactActivity;
import com.nate.contactandnotes.adapter.ContactsListViewAdapter;
import com.nate.contactandnotes.db.DBController;
import com.nate.contactandnotes.fragment.base.CNBaseFragment;
import com.nate.contactandnotes.model.ContactModel;
import com.nate.contactandnotes.temp.Cheeses;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nate on 2015/11/17.联系人列表界面
 */
public class ContactsFragment extends CNBaseFragment {

    @ViewInject(R.id.contacts_lv)
    private ListView mListView;
    @ViewInject(R.id.contacts_fancy_indexer)
    private FancyIndexer mFancyIndexer;
    @ViewInject(R.id.empty_view_ll)
    private LinearLayout emptyView;
    private List<ContactModel> contacts = new ArrayList<>();
    private ContactsListViewAdapter mAdapter;

    /**
     * @return Fragment绑定的布局文件id
     */
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.contacts_fragment_layout;
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
        // 填充数据, 并排序
        fillAndSortData();
        mAdapter = new ContactsListViewAdapter(getContext(), R.layout.contacts_fragment_item_layout, contacts);
        mListView.setAdapter(mAdapter);
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.contacts_fragment_header_layout, null);
        mListView.addHeaderView(headView, null, false);
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
                        mListView.setSelection(i + 1);//因为这里加入了headview
                        break;
                    }
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    ContactModel model = (ContactModel) parent.getAdapter().getItem(position);
                    showToast(model.getName());
                }
            }
        });

        //设置headview点击监听
        headView.findViewById(R.id.add_contact_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go(NewContactActivity.class);
            }
        });
        headView.findViewById(R.id.group_contact_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("分组");
            }
        });

        //设置emptyview
        mListView.setEmptyView(emptyView);
    }

    @Event(value = R.id.empty_add_ll)
    private void onEmptyAdd(View view) {
        go(NewContactActivity.class);
    }

    /**
     * 填充,排序
     */
    private void fillAndSortData() {
        boolean china = getResources().getConfiguration().locale.getCountry().equals("CN");
        String[] datas = china ? Cheeses.NAMES : Cheeses.sCheeseStrings;
//        for (int i = 0; i < datas.length; i++) {
//            contacts.add(new ContactModel(datas[i]));
//        }

        contacts = DBController.selectAllContactModel();
        if (contacts != null && contacts.size() > 0) {
            // 排序
            Collections.sort(contacts);
        }
    }
}
