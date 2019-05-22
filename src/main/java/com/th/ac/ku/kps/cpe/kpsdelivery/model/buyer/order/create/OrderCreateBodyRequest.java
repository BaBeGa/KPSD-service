package com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.create;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.OrderStatus;

import javax.persistence.Enumerated;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class OrderCreateBodyRequest {
    //int can't be null,but Integer can.
    private int id;
    private Integer customerId;
    private Integer driverId;
    private Integer restaurantId;
    private Double customerLatValue;
    private Double customerLonValue;
    private Double driverLatValue;
    private Double driverLonValue;
    private Double restaurantLatValue;
    private Double restaurantLonValue;
    private OrderStatus status;
    private Date orderDate;
    private Time requiredTime;
    private Time waitingTime;
    private Time shippedTime;
    private Time totalTime;
    private double foodPrice;
    private Double lengthPrice;
    private Double totalPrice;
    private Double totalLength;
    private Integer quantity;
    private Double discount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    @JsonGetter
    public int getId() {
        return id;
    }
    @JsonSetter
    public void setId(int id) {
        this.id = id;
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
    @Enumerated
    @JsonGetter
    public OrderStatus getStatus() {
        return status;
    }
    @Enumerated
    @JsonSetter
    public void setStatus(OrderStatus status) {
        this.status = status;
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
    public Double getTotalPrice() {
        return totalPrice;
    }
    @JsonSetter
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    @JsonSetter
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    @JsonGetter
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    @JsonSetter
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    @JsonGetter
    public Timestamp getDeletedAt() {
        return deletedAt;
    }
    @JsonSetter
    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}
