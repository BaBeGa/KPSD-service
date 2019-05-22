package com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.create;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class OrderCreateResponse {
    private Integer status;
    private String msg;
    private Integer id_order;

    @JsonGetter
    public Integer getStatus() { return status; }

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
    public Integer getId_order() {
        return id_order;
    }

    @JsonSetter
    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }
}
