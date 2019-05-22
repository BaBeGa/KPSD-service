package com.th.ac.ku.kps.cpe.kpsdelivery.model.user.update;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
//import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;

//import java.sql.Timestamp;

public class userUpdateRequest {
    private int id;
//    private String name;
//    private String email;
//    private Timestamp emailVerifiedAt;
//    private String password;
//    private UserType type;
//    private String image;
//    private Byte isActiveAccount;
    private String fcmToken;
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
    public String getFcmToken() {
        return fcmToken;
    }
    @JsonSetter
    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
