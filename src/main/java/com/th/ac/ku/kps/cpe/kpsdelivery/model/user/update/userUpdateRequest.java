package com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;

import java.sql.Timestamp;
//import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;

//import java.sql.Timestamp;

public class userUpdateRequest {
    private int id;
    private String name;
    private String email;
    private Timestamp emailVerifiedAt;
    private String password;
    private UserType type;
    private String image;
    private Byte isActiveAccount;
    private Byte workStatus;
    private String fcmToken;
    private String address;
    private String address2;
    private String subdistrict;
    private String district;
    private String province;
    private String zipcode;
    private String phonenumber;
    private Double latValue;
    private Double lonValue;
    private String firstname;
    private String lastname;
//    private String rememberToken;
//    private Timestamp createdAt;
//    private Timestamp updatedAt;
//    private Timestamp deletedAt;

    @JsonGetter
    public int getId() {
        return id;
    }
    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }
    @JsonGetter
    public Byte getIsActiveAccount() {
        return isActiveAccount;
    }
    @JsonSetter
    public void setIsActiveAccount(Byte isActiveAccount) {
        this.isActiveAccount = isActiveAccount;
    }
    @JsonGetter
    public Byte getWorkStatus() {
        return workStatus;
    }
    @JsonSetter
    public void setWorkStatus(Byte workStatus) {
        this.workStatus = workStatus;
    }

    @JsonGetter
    public String getFcmToken() {
        return fcmToken;
    }
    @JsonSetter
    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
    @JsonGetter
    public String getName() {
        return name;
    }
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }
    @JsonGetter
    public String getEmail() {
        return email;
    }
    @JsonSetter
    public void setEmail(String email) {
        this.email = email;
    }
    @JsonGetter
    public Timestamp getEmailVerifiedAt() {
        return emailVerifiedAt;
    }
    @JsonSetter
    public void setEmailVerifiedAt(Timestamp emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }
    @JsonGetter
    public String getPassword() {
        return password;
    }
    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }
    @JsonGetter
    public UserType getType() {
        return type;
    }
    @JsonSetter
    public void setType(UserType type) {
        this.type = type;
    }
    @JsonGetter
    public String getImage() {
        return image;
    }
    @JsonSetter
    public void setImage(String image) {
        this.image = image;
    }
    @JsonGetter
    public String getAddress() {
        return address;
    }
    @JsonSetter
    public void setAddress(String address) {
        this.address = address;
    }
    @JsonGetter
    public String getAddress2() {
        return address2;
    }
    @JsonSetter
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    @JsonGetter
    public String getSubdistrict() {
        return subdistrict;
    }
    @JsonSetter
    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }
    @JsonGetter
    public String getDistrict() {
        return district;
    }
    @JsonSetter
    public void setDistrict(String district) {
        this.district = district;
    }
    @JsonGetter
    public String getProvince() {
        return province;
    }
    @JsonSetter
    public void setProvince(String province) {
        this.province = province;
    }
    @JsonGetter
    public String getZipcode() {
        return zipcode;
    }
    @JsonSetter
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    @JsonGetter
    public String getPhonenumber() {
        return phonenumber;
    }
    @JsonSetter
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    @JsonGetter
    public Double getLatValue() {
        return latValue;
    }
    @JsonSetter
    public void setLatValue(Double latValue) {
        this.latValue = latValue;
    }
    @JsonGetter
    public Double getLonValue() {
        return lonValue;
    }
    @JsonSetter
    public void setLonValue(Double lonValue) {
        this.lonValue = lonValue;
    }
    @JsonGetter
    public String getFirstname() {
        return firstname;
    }
    @JsonSetter
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    @JsonGetter
    public String getLastname() {
        return lastname;
    }
    @JsonSetter
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
