package com.nate.contactandnotes.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.gu.baselibrary.baseadapter.BaseViewHolder;
import com.gu.baselibrary.baseadapter.MyBaseAdapter;
import com.nate.contactandnotes.R;
import com.nate.contactandnotes.model.ContactModel;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.联系人界面的adapter
 */
public class ContactsListViewAdapter extends MyBaseAdapter<ContactModel> {
    public ContactsListViewAdapter(Context context, int resource, List<ContactModel> list) {
        super(context, resource, list);
    }

    /**
     * @param viewHolder
     * @param contactModel
     * @return void 返回类型
     * @Title: setConvert
     * @Description: 抽象方法，由子类去实现每个itme如何设置
     */
    @Override
    public void setConvert(BaseViewHolder viewHolder, ContactModel contactModel) {
        String currentLetter = contactModel.getPinyin().charAt(0) + "";
        String indexStr = null;
        if (viewHolder.getPosition() == 0) {
            // 1. 如果是第一位
            indexStr = currentLetter;
        } else {
            // 获取上一个拼音
            String preLetter = list.get(viewHolder.getPosition() - 1).getPinyin().charAt(0) + "";
            if (!TextUtils.equals(currentLetter, preLetter)) {
                // 2. 当跟上一个不同时, 赋值, 显示
                indexStr = currentLetter;
            }
        }
        viewHolder.getView(R.id.contacts_item_index_tv).setVisibility(indexStr == null ? View.GONE : View.VISIBLE);
        viewHolder.setTextView(R.id.contacts_item_index_tv, indexStr).setTextView(R.id.contacts_item_name_tv, contactModel.getName());
    }
}
