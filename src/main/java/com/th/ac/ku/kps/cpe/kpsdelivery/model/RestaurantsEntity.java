package com.th.ac.ku.kps.cpe.kpsdelivery.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "restaurants", schema = "delivery")
public class RestaurantsEntity {
    private int id;
    private String name;
    private String address;
    private String phonenumber;
    private double latValue;
    private double lonValue;
    private byte isActive;
    private String image;
    private Double numRating;
    private Double avgRating;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Basic
    @Column(name = "lat_value")
    public double getLatValue() {
        return latValue;
    }

    public void setLatValue(double latValue) {
        this.latValue = latValue;
    }

    @Basic
    @Column(name = "lon_value")
    public double getLonValue() {
        return lonValue;
    }

    public void setLonValue(double lonValue) {
        this.lonValue = lonValue;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "num_rating")
    public Double getNumRating() {
        return numRating;
    }

    public void setNumRating(Double numRating) {
        this.numRating = numRating;
    }

    @Basic
    @Column(name = "avg_rating")
    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantsEntity that = (RestaurantsEntity) o;
        return id == that.id &&
                Double.compare(that.latValue, latValue) == 0 &&
                Double.compare(that.lonValue, lonValue) == 0 &&
                isActive == that.isActive &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phonenumber, that.phonenumber) &&
                Objects.equals(image, that.image) &&
                Objects.equals(numRating, that.numRating) &&
                Objects.equals(avgRating, that.avgRating) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phonenumber, latValue, lonValue, isActive, image, numRating, avgRating, createdAt, updatedAt, deletedAt);
    }
}
