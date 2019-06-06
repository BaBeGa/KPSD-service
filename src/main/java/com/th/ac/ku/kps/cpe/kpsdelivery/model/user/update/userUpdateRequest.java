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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Double getLatValue() {
        return latValue;
    }

    public void setLatValue(Double latValue) {
        this.latValue = latValue;
    }

    public Double getLonValue() {
        return lonValue;
    }

    public void setLonValue(Double lonValue) {
        this.lonValue = lonValue;
    }
}
