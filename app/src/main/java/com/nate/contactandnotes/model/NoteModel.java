package com.nate.contactandnotes.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.sql.Date;

/**
 * Created by Nate on 2015/11/17.笔记实体类
 */
@Table(name = "notemodel")
public class NoteModel {
    @Column(name = "id", isId = true)
    private int id;//每一条笔记的唯一id
    @Column(name = "title")
    private String title;//笔记的标题
    @Column(name = "content")
    private String content;//笔记内容
    @Column(name = "pics")
    private String pics;//笔记图片,图片路径以;号隔开，到时可以解析成数组进行展示
    @Column(name = "records")
    private String records;//录音文件存放路径，以;号隔开
    @Column(name = "videos")
    private String videos;//视频文件存放路径以；号隔开
    @Column(name = "contactId")
    private String contactId;//关联的联系人Id
    @Column(name = "createDate")
    private Date createDate;//笔记创建日期
    @Column(name = "longitude")
    private double longitude;//位置经度
    @Column(name = "latitude")
    private double latitude;//位置维度
    @Column(name = "address")
    private String address;//地址信息

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
