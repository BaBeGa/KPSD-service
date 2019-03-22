package com.th.ac.ku.kps.cpe.kpsdelivery;

import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "delivery")
public class UsersEntity {
    private int idUser;
    private String username;
    private String password;
    private UserType userType;
    private String token;
    private Double latitude;
    private Double longtitude;

    @Id
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longtitude")
    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return idUser == that.idUser &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(token, that.token) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longtitude, that.longtitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, username, password, userType, token, latitude, longtitude);
    }
}
