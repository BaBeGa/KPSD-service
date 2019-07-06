package com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.get;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.DriverhistoryEntity;

import java.util.List;

public class driverorderGetResponse {
    private Integer status;
    private String msg;
    private List<DriverhistoryEntity> driverHistory;


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
    public List<DriverhistoryEntity> getDriverHistory() {
        return driverHistory;
    }
    @JsonSetter
    public void setDriverHistory(List<DriverhistoryEntity> driverHistory) {
        this.driverHistory = driverHistory;
    }
}
