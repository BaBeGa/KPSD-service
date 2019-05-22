package com.th.ac.ku.kps.cpe.kpsdelivery.model;

import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.UserType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "delivery")
public class UsersEntity {
    private int id;
    private String name;
    private String email;
    private Timestamp emailVerifiedAt;
    private String password;
    private UserType type;
    private String image;
    private Byte isActiveAccount;
    private String fcmToken;
    private String rememberToken;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "email_verified_at")
    public Timestamp getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Timestamp emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
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
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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
    @Column(name = "is_active_account")
    public Byte getIsActiveAccount() {
        return isActiveAccount;
    }

    public void setIsActiveAccount(Byte isActiveAccount) {
        this.isActiveAccount = isActiveAccount;
    }

    @Basic
    @Column(name = "fcm_token")
    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    @Basic
    @Column(name = "remember_token")
    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
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
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(emailVerifiedAt, that.emailVerifiedAt) &&
                Objects.equals(password, that.password) &&
                Objects.equals(type, that.type) &&
                Objects.equals(image, that.image) &&
                Objects.equals(isActiveAccount, that.isActiveAccount) &&
                Objects.equals(fcmToken, that.fcmToken) &&
                Objects.equals(rememberToken, that.rememberToken) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, emailVerifiedAt, password, type, image, isActiveAccount, fcmToken, rememberToken, createdAt, updatedAt, deletedAt);
    }
}
