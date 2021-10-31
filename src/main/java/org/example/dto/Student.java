package org.example.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 */
public class Student implements Serializable {
    String name;
//    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    Date gmtCreate;

    String gmtModify;

    int old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(String gmtModify) {
        this.gmtModify = gmtModify;
    }
}
