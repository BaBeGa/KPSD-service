package com.th.ac.ku.kps.cpe.kpsdelivery.model.finder;

public class DriverFindResponse {
    private Integer status;
    private String msg;
    private String firebaseResponse;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFirebaseResponse() {
        return firebaseResponse;
    }

    public void setFirebaseResponse(String firebaseResponse) {
        this.firebaseResponse = firebaseResponse;
    }
}
