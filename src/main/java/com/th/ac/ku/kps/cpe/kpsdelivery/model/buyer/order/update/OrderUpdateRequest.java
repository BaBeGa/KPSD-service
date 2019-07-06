package com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.update;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.OrderStatus;

import java.sql.Time;
import java.sql.Timestamp;

public class OrderUpdateRequest {
    private int id;
//    private Integer customerId;
//    private Integer driverId;
//    private Integer restaurantId;
//    private Double customerLatValue;
//    private Double customerLonValue;
    private Double driverLatValue;
    private Double driverLonValue;
//    private Double restaurantLatValue;
//    private Double restaurantLonValue;
    private OrderStatus status;
    private Double driverRating;
    private Double restaurantRating;
//    private Date orderDate;
//    private Time requiredTime;
//    private Time waitingTime;
//    private Time shippedTime;
//    private Time totalTime;
//    private double foodPrice;
//    private Double lengthPrice;
//    private Double totalPrice;
//    private Double totalLength;
//    private Integer quantity;
//    private Double discount;

    @JsonGetter
    public int getId() {
        return id;

    }
    @JsonSetter
    public void setId(int id) {
        this.id = id;
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
    public OrderStatus getStatus() {
        return status;
    }
    @JsonSetter
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    @JsonGetter
    public Double getDriverRating() {
        return driverRating;
    }
    @JsonSetter
    public void setDriverRating(Double driverRating) {
        this.driverRating = driverRating;
    }
    @JsonGetter
    public Double getRestaurantRating() {
        return restaurantRating;
    }
    @JsonSetter
    public void setRestaurantRating(Double restaurantRating) {
        this.restaurantRating = restaurantRating;
    }
}
