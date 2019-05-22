package com.th.ac.ku.kps.cpe.kpsdelivery.model.user.get;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;

public class userGetResponse {
    private Integer status;
    private String msg;
    private UserType userType;
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
    public UserType getUserType() {
        return userType;
    }
    @JsonSetter
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
