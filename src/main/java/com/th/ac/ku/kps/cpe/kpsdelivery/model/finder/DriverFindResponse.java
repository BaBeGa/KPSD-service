package com.th.ac.ku.kps.cpe.kpsdelivery.model.finder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;

public class DriverFindResponse {
    private Integer status;
    private String msg;
    private ArrayList list;
    private ArrayList listfirebaseResponse;
    private String firebaseResponse;

    @JsonGetter
    public Integer getStatus() {
        return status;
    }
    @JsonSetter
    public void setStatus(Integer status) {
        this.status = status;
    }
    @JsonGetter
    public String getMsg() {
        return msg;
    }
    @JsonSetter
    public void setMsg(String msg) {
        this.msg = msg;
    }
    @JsonGetter
    public ArrayList getList() {
        return list;
    }
    @JsonSetter
    public void setList(ArrayList list) {
        this.list = list;
    }
    @JsonGetter
    public ArrayList getListfirebaseResponse() {
        return listfirebaseResponse;
    }
    @JsonSetter
    public void setListfirebaseResponse(ArrayList listfirebaseResponse) {
        this.listfirebaseResponse = listfirebaseResponse;
    }
    @JsonGetter
    public String getFirebaseResponse() {
        return firebaseResponse;
    }
    @JsonSetter
    public void setFirebaseResponse(String firebaseRespobse) {
        this.firebaseResponse = firebaseRespobse;
    }
}
