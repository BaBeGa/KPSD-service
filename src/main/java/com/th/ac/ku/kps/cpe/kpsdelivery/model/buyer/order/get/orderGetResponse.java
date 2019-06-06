package com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.get;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.OrderStatus;
import com.th.ac.ku.kps.cpe.kpsdelivery.model.MenusEntity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class orderGetResponse {
    private Integer status;
    private String msg;
    private ArrayList<MenusEntity> menus;
    private Integer customerId;
    private Integer driverId;
    private Integer restaurantId;
    private Double customerLatValue;
    private Double customerLonValue;
    private Double driverLatValue;
    private Double driverLonValue;
    private Double restaurantLatValue;
    private Double restaurantLonValue;
    private OrderStatus orderStatus;
    private Date orderDate;
    private Time requiredTime;
    private Time waitingTime;
    private Time shippedTime;
    private Time totalTime;
    private Double totalLength;
    private double foodPrice;
    private Double lengthPrice;
    private Double percentPrice;
    private Double totalPrice;
    private Integer quantity;
    private Double discount;
    private Double orderRating;
    private Timestamp updatedAt;

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
    public ArrayList<MenusEntity> getMenus() {
        return menus;
    }
    @JsonSetter
    public void setMenus(ArrayList<MenusEntity> menus) {
        this.menus = menus;
    }
    @JsonGetter
    public Integer getCustomerId() {
        return customerId;
    }
    @JsonSetter
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    @JsonGetter
    public Integer getDriverId() {
        return driverId;
    }
    @JsonSetter
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    @JsonGetter
    public Integer getRestaurantId() {
        return restaurantId;
    }
    @JsonSetter
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    @JsonGetter
    public Double getCustomerLatValue() {
        return customerLatValue;
    }
    @JsonSetter
    public void setCustomerLatValue(Double customerLatValue) {
        this.customerLatValue = customerLatValue;
    }
    @JsonGetter
    public Double getCustomerLonValue() {
        return customerLonValue;
    }
    @JsonSetter
    public void setCustomerLonValue(Double customerLonValue) {
        this.customerLonValue = customerLonValue;
    }
    @JsonGetter
    public Double getDriverLatValue() {
        return driverLatValue;
    }
    @JsonSetter
    public void setDriverLatValue(Double driverLatValue) {
        this.driverLatValue = driverLatValue;
    }
    @JsonGetter
    public Double getDriverLonValue() {
        return driverLonValue;
    }
    @JsonSetter
    public void setDriverLonValue(Double driverLonValue) {
        this.driverLonValue = driverLonValue;
    }
    @JsonGetter
    public Double getRestaurantLatValue() {
        return restaurantLatValue;
    }
    @JsonSetter
    public void setRestaurantLatValue(Double restaurantLatValue) {
        this.restaurantLatValue = restaurantLatValue;
    }
    @JsonGetter
    public Double getRestaurantLonValue() {
        return restaurantLonValue;
    }
    @JsonSetter
    public void setRestaurantLonValue(Double restaurantLonValue) {
        this.restaurantLonValue = restaurantLonValue;
    }
    @JsonGetter
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    @JsonSetter
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    @JsonGetter
    public Date getOrderDate() {
        return orderDate;
    }
    @JsonSetter
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    @JsonGetter
    public Time getRequiredTime() {
        return requiredTime;
    }
    @JsonSetter
    public void setRequiredTime(Time requiredTime) {
        this.requiredTime = requiredTime;
    }
    @JsonGetter
    public Time getWaitingTime() {
        return waitingTime;
    }
    @JsonSetter
    public void setWaitingTime(Time waitingTime) {
        this.waitingTime = waitingTime;
    }
    @JsonGetter
    public Time getShippedTime() {
        return shippedTime;
    }
    @JsonSetter
    public void setShippedTime(Time shippedTime) {
        this.shippedTime = shippedTime;
    }
    @JsonGetter
    public Time getTotalTime() {
        return totalTime;
    }
    @JsonSetter
    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }
    @JsonGetter
    public Double getTotalLength() {
        return totalLength;
    }
    @JsonSetter
    public void setTotalLength(Double totalLength) {
        this.totalLength = totalLength;
    }
    @JsonGetter
    public double getFoodPrice() {
        return foodPrice;
    }
    @JsonSetter
    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
    @JsonGetter
    public Double getLengthPrice() {
        return lengthPrice;
    }
    @JsonSetter
    public void setLengthPrice(Double lengthPrice) {
        this.lengthPrice = lengthPrice;
    }
    @JsonGetter
    public Double getPercentPrice() {
        return percentPrice;
    }
    @JsonSetter
    public void setPercentPrice(Double percentPrice) {
        this.percentPrice = percentPrice;
    }
    @JsonGetter
    public Double getTotalPrice() {
        return totalPrice;
    }
    @JsonSetter
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    @JsonGetter
    public Integer getQuantity() {
        return quantity;
    }
    @JsonSetter
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @JsonGetter
    public Double getDiscount() {
        return discount;
    }
    @JsonSetter
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    @JsonGetter
    public Double getOrderRating() {
        return orderRating;
    }
    @JsonSetter
    public void setOrderRating(Double orderRating) {
        this.orderRating = orderRating;
    }
    @JsonGetter
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    @JsonSetter
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
