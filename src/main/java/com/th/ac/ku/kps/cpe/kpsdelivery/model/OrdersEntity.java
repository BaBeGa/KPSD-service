package com.th.ac.ku.kps.cpe.kpsdelivery.model;

import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.OrderStatus;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "delivery")
public class OrdersEntity {
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
    private Double totalLength;
    private double foodPrice;
    private Double lengthPrice;
    private Double percentPrice;
    private Double totalPrice;
    private Integer quantity;
    private Double discount;
    private Double driverRating;
    private Double restaurantRating;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private Double startPrice;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "driver_id")
    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    @Basic
    @Column(name = "restaurant_id")
    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Basic
    @Column(name = "customer_lat_value")
    public Double getCustomerLatValue() {
        return customerLatValue;
    }

    public void setCustomerLatValue(Double customerLatValue) {
        this.customerLatValue = customerLatValue;
    }

    @Basic
    @Column(name = "customer_lon_value")
    public Double getCustomerLonValue() {
        return customerLonValue;
    }

    public void setCustomerLonValue(Double customerLonValue) {
        this.customerLonValue = customerLonValue;
    }

    @Basic
    @Column(name = "driver_lat_value")
    public Double getDriverLatValue() {
        return driverLatValue;
    }

    public void setDriverLatValue(Double driverLatValue) {
        this.driverLatValue = driverLatValue;
    }

    @Basic
    @Column(name = "driver_lon_value")
    public Double getDriverLonValue() {
        return driverLonValue;
    }

    public void setDriverLonValue(Double driverLonValue) {
        this.driverLonValue = driverLonValue;
    }

    @Basic
    @Column(name = "restaurant_lat_value")
    public Double getRestaurantLatValue() {
        return restaurantLatValue;
    }

    public void setRestaurantLatValue(Double restaurantLatValue) {
        this.restaurantLatValue = restaurantLatValue;
    }

    @Basic
    @Column(name = "restaurant_lon_value")
    public Double getRestaurantLonValue() {
        return restaurantLonValue;
    }

    public void setRestaurantLonValue(Double restaurantLonValue) {
        this.restaurantLonValue = restaurantLonValue;
    }

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "order_date")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "required_time")
    public Time getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(Time requiredTime) {
        this.requiredTime = requiredTime;
    }

    @Basic
    @Column(name = "waiting_time")
    public Time getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Time waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Basic
    @Column(name = "shipped_time")
    public Time getShippedTime() {
        return shippedTime;
    }

    public void setShippedTime(Time shippedTime) {
        this.shippedTime = shippedTime;
    }

    @Basic
    @Column(name = "total_time")
    public Time getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }

    @Basic
    @Column(name = "total_length")
    public Double getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Double totalLength) {
        this.totalLength = totalLength;
    }

    @Basic
    @Column(name = "food_price")
    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Basic
    @Column(name = "length_price")
    public Double getLengthPrice() {
        return lengthPrice;
    }

    public void setLengthPrice(Double lengthPrice) {
        this.lengthPrice = lengthPrice;
    }

    @Basic
    @Column(name = "percent_price")
    public Double getPercentPrice() {
        return percentPrice;
    }

    public void setPercentPrice(Double percentPrice) {
        this.percentPrice = percentPrice;
    }

    @Basic
    @Column(name = "total_price")
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "discount")
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "driver_rating")
    public Double getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(Double driverRating) {
        this.driverRating = driverRating;
    }

    @Basic
    @Column(name = "restaurant_rating")
    public Double getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(Double restaurantRating) {
        this.restaurantRating = restaurantRating;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "deleted_at")
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "start_price")
    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id &&
                Double.compare(that.foodPrice, foodPrice) == 0 &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(driverId, that.driverId) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(customerLatValue, that.customerLatValue) &&
                Objects.equals(customerLonValue, that.customerLonValue) &&
                Objects.equals(driverLatValue, that.driverLatValue) &&
                Objects.equals(driverLonValue, that.driverLonValue) &&
                Objects.equals(restaurantLatValue, that.restaurantLatValue) &&
                Objects.equals(restaurantLonValue, that.restaurantLonValue) &&
                Objects.equals(status, that.status) &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(requiredTime, that.requiredTime) &&
                Objects.equals(waitingTime, that.waitingTime) &&
                Objects.equals(shippedTime, that.shippedTime) &&
                Objects.equals(totalTime, that.totalTime) &&
                Objects.equals(totalLength, that.totalLength) &&
                Objects.equals(lengthPrice, that.lengthPrice) &&
                Objects.equals(percentPrice, that.percentPrice) &&
                Objects.equals(totalPrice, that.totalPrice) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(driverRating, that.driverRating) &&
                Objects.equals(restaurantRating, that.restaurantRating) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(startPrice, that.startPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, driverId, restaurantId, customerLatValue, customerLonValue, driverLatValue, driverLonValue, restaurantLatValue, restaurantLonValue, status, orderDate, requiredTime, waitingTime, shippedTime, totalTime, totalLength, foodPrice, lengthPrice, percentPrice, totalPrice, quantity, discount, driverRating, restaurantRating, createdAt, updatedAt, deletedAt, startPrice);
    }
}
