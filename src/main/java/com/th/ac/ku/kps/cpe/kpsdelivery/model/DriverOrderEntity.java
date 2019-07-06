package com.th.ac.ku.kps.cpe.kpsdelivery.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class DriverOrderEntity {
    private String name;
    private int price;
    private Integer quantity;

    @JsonGetter
    public String getName() {
        return name;
    }
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }
    @JsonGetter
    public int getPrice() {
        return price;
    }
    @JsonSetter
    public void setPrice(int price) {
        this.price = price;
    }
    @JsonGetter
    public Integer getQuantity() {
        return quantity;
    }
    @JsonSetter
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
