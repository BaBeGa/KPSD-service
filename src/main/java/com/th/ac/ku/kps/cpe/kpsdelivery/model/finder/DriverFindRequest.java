package com.th.ac.ku.kps.cpe.kpsdelivery.model.finder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
//import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.OrderStatus;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.sql.Timestamp;

public class DriverFindRequest {
    private int id; // order id
    private Integer customerId;
    //private Integer driverId;
    //private Double customerLatValue;
    //private Double customerLonValue;
    //private Double driverLatValue;
    //private Double driverLonValue;
    private Integer restaurantID;
    //private Double restaurantLatValue;
    //private Double restaurantLonValue;
    //private OrderStatus status;
    //private Date orderDate;
    //private Time requiredTime;
    //private Time waitingTime;
    //private Time shippedTime;
    //private Time totalTime;
    //private Integer totalPrice;
    //private Integer totalLength;
    //private Integer quantity;
    //private Integer discount;
    //private Timestamp createdAt;
    //private Timestamp updatedAt;
    //private Timestamp deletedAt;
    private Integer dislimit;
    @JsonGetter
    public Integer getDislimit() {
        return dislimit;
    }
    @JsonSetter
    public void setDislimit(Integer dislimit) {
        this.dislimit = dislimit;
    }
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
    public Integer getRestaurantID() {
        return restaurantID;
    }
    @JsonSetter
    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }
}
