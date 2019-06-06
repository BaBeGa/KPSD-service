package com.th.ac.ku.kps.cpe.kpsdelivery.model.buyer.order.update;

import com.fasterxml.jackson.annotation.JsonGetter;
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
//    @JsonGetter
//    public Integer getDriverId() {
//        return driverId;
//    }
    @JsonGetter
    public Double getDriverLatValue() {
        return driverLatValue;
    }
    @JsonGetter
    public Double getDriverLonValue() {
        return driverLonValue;
    }
    @JsonGetter
    public OrderStatus getStatus() {
        return status;
    }

//    @JsonGetter
//    public Double getTotalLength() {
//        return totalLength;
//    }


}
