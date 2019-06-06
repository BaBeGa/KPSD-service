package com.th.ac.ku.kps.cpe.kpsdelivery.model.finder.Acception;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AcceptionRequest {
    private int orderId;
    private int userId;
    @JsonGetter
    public int getOrderId() {
        return orderId;
    }
    @JsonSetter
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    @JsonGetter
    public int getUserId() {
        return userId;
    }
    @JsonSetter
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
