package com.nate.contactandnotes.model;

import java.sql.Date;

/**
 * Created by Administrator on 2015/11/17.联系人实体类
 */
public class ContactModel {

    private int id;//主键，自增长id

    private String name;//联系人姓名

    private String phone;//联系人手机号

    private String headPic;//联系人头像存放地址

    private String desc;//联系人的描述

    private String companyName;//联系人公司

    private String visitingCardPic;//联系人名片存放地址

    private Date createDate;//联系人创建日期

    private String qqNum;//联系人qq号

    private String weixinNum;//联系人微信号

}
