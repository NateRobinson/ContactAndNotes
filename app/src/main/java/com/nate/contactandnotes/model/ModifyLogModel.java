package com.nate.contactandnotes.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Administrator on 2015/11/17. 后期可以加入修改日志，用户每次做一次修改保存一次
 */
@Table(name = "modifylogmodel")
public class ModifyLogModel implements Serializable {

    @Column(name = "id", isId = true)
    private int id;//自增长id
    @Column(name = "noteid")
    private int noteId;//属于哪个note
    @Column(name = "longitude")
    private double longitude;//位置经度
    @Column(name = "latitude")
    private double latitude;//位置维度
    @Column(name = "address")
    private String address;//地址信息
    @Column(name = "address")
    private Date modifyDate;//修改的时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
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

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
