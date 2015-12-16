package com.nate.contactandnotes.activity.contact;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.ListView;

import com.gu.baselibrary.utils.NetUtils;
import com.gu.baselibrary.view.FancyIndexer;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.activity.base.CNBaseActivity;
import com.nate.contactandnotes.adapter.ContactsListViewAdapter;
import com.nate.contactandnotes.adapter.PhoneContactsListViewAdapter;
import com.nate.contactandnotes.db.DBController;
import com.nate.contactandnotes.model.ContactModel;
import com.nate.contactandnotes.model.PhoneContactModel;
import com.nate.contactandnotes.temp.Cheeses;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nate on 2015/11/20.从手机联系人中添加联系人
 */
public class AddContactByPhoneActivity extends CNBaseActivity {
    /**
     * 获取库Phon表字段
     **/
    private static final String[] PHONES_PROJECTION = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;
    @ViewInject(R.id.phone_contacts_lv)
    private ListView mListView;
    @ViewInject(R.id.phone_contacts_fancy_indexer)
    private FancyIndexer mFancyIndexer;
    private List<PhoneContactModel> contacts = new ArrayList<>();
    private PhoneContactsListViewAdapter mAdapter;
    private Cursor phoneCursor;


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
        mAdapter = new PhoneContactsListViewAdapter(this, R.layout.add_contact_by_phone_activity_item_layout, contacts);
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
                    PhoneContactModel contactModel = contacts.get(i);
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
        //先查询PhoneContactModel的数据
        contacts = DBController.selectAllPhoneContactModel();
        if (contacts == null || contacts.size() == 0) {
            //执行查询与导入操作
            getContactsFromPhoneAndSave();
            if (contacts == null || contacts.size() == 0) {
                showToast("手机联系人为空，请尝试手动添加");
                finish();
            } else {
                fillAndSortData();
            }
        } else {
            if (contacts.size() > 0) {
                // 排序
                Collections.sort(contacts);
            }
        }
    }


    /**
     * 查询手机上的联系人，组装成contacts，然后调用DBController.insertPhoneContactModels方法插入到应用的数据库中
     *
     * @throws DbException
     */
    @SuppressWarnings("deprecation")
    private void getContactsFromPhoneAndSave() {
        contacts = new ArrayList<>();
        ContentResolver resolver = this.getContentResolver();
        phoneCursor =
                resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        PHONES_PROJECTION,
                        null,
                        null,
                        null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                //得到手机号码
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                //当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;
                //得到联系人名称
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
                PhoneContactModel model = new PhoneContactModel();
                model.setIsAdded(false);
                model.setName(contactName);
                model.setPhone(phoneNumber);
                model.setPinyin(null);
                contacts.add(model);
            }
        }
        if (!contacts.isEmpty()) {
            DBController.insertPhoneContactModels(contacts);
        }
        startManagingCursor(phoneCursor);
    }
}
