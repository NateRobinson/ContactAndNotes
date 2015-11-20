package com.nate.contactandnotes.model;

import com.gu.baselibrary.utils.PinyinUtil;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Administrator on 2015/11/17.联系人实体类
 */
@Table(name = "phonecontactmodel")
public class PhoneContactModel implements Serializable, Comparable<PhoneContactModel> {

    @Column(name = "id", isId = true)
    private int id;//主键，自增长id
    @Column(name = "name")
    private String name;//联系人姓名
    @Column(name = "phone")
    private String phone;//联系人手机号
    @Column(name = "pinyin")
    private String pinyin;//名字拼音
    @Column(name = "isadded")//是否已经被添加
    private boolean isAdded;

    public PhoneContactModel() {
    }

    public PhoneContactModel(String name) {
        this.name = name;
        this.pinyin = PinyinUtil.getPinyin(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin =  PinyinUtil.getPinyin(name);
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    /**
     * Compares this object to the specified object to determine their relative
     * order.
     *
     * @param another the object to compare to this instance.
     * @return a negative integer if this instance is less than {@code another};
     * a positive integer if this instance is greater than
     * {@code another}; 0 if this instance has the same order as
     * {@code another}.
     * @throws ClassCastException if {@code another} cannot be converted into something
     *                            comparable to {@code this} instance.
     */
    @Override
    public int compareTo(PhoneContactModel another) {
        return pinyin.compareTo(another.pinyin);
    }
}
