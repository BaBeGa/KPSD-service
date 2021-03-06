package com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.Acception;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AcceptionResponse {
    private Integer status;
    private String msg;
    private Integer cusId;
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
    public Integer getCusId() {
        return cusId;
    }
    @JsonSetter
    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
    @JsonGetter
    public String getFirebaseResponse() {
        return firebaseResponse;
    }
    @JsonSetter
    public void setFirebaseResponse(String firebaseResponse) {
        this.firebaseResponse = firebaseResponse;
    }
}
