package com.th.ac.ku.kps.cpe.kpsdelivery;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shop", schema = "delivery")
public class ShopEntity {
    private int idShop;
    private int idUser;
    private String shopName;
    private Double latitude;
    private Double longtitude;
    private String descrpition;

    @Id
    @Column(name = "id_shop")
    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    @Basic
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "shop_name")
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    @Basic
    @Column(name = "descrpition")
    public String getDescrpition() {
        return descrpition;
    }

    public void setDescrpition(String descrpition) {
        this.descrpition = descrpition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopEntity that = (ShopEntity) o;
        return idShop == that.idShop &&
                idUser == that.idUser &&
                Objects.equals(shopName, that.shopName) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longtitude, that.longtitude) &&
                Objects.equals(descrpition, that.descrpition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idShop, idUser, shopName, latitude, longtitude, descrpition);
    }
}
