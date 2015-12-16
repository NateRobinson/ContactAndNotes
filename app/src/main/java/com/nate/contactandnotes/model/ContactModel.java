package com.nate.contactandnotes.model;

import com.gu.baselibrary.utils.PinyinUtil;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Nate on 2015/11/17.联系人实体类
 */
@Table(name = "contactmodel")
public class ContactModel implements Serializable,Comparable<ContactModel> {

    @Column(name = "id", isId = true)
    private int id;//主键，自增长id
    @Column(name = "name")
    private String name;//联系人姓名
    @Column(name = "phone")
    private String phone;//联系人手机号
    @Column(name = "headpic")
    private String headPic;//联系人头像存放地址
    @Column(name = "desc")
    private String desc;//联系人的描述
    @Column(name = "companyname")
    private String companyName;//联系人公司
    @Column(name = "visitingcardpic")
    private String visitingCardPic;//联系人名片存放地址
    @Column(name = "createdate")
    private Date createDate;//联系人创建日期
    @Column(name = "qqnum")
    private String qqNum;//联系人qq号
    @Column(name = "weixinnum")
    private String weixinNum;//联系人微信号
    @Column(name = "pinyin")
    private String pinyin;//名字拼音

    public ContactModel() {
    }

    public ContactModel(String name) {
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

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVisitingCardPic() {
        return visitingCardPic;
    }

    public void setVisitingCardPic(String visitingCardPic) {
        this.visitingCardPic = visitingCardPic;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getWeixinNum() {
        return weixinNum;
    }

    public void setWeixinNum(String weixinNum) {
        this.weixinNum = weixinNum;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
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
    public int compareTo(ContactModel another) {
        return pinyin.compareTo(another.pinyin);
    }
}
