package com.th.ac.ku.kps.cpe.kpsdelivery;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart", schema = "delivery")
public class CartEntity {
    private int idCart;
    private int idOrder;
    private int idFood;
    private int quality;

    @Id
    @Column(name = "id_cart")
    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    @Basic
    @Column(name = "id_order")
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Basic
    @Column(name = "id_food")
    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    @Basic
    @Column(name = "quality")
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return idCart == that.idCart &&
                idOrder == that.idOrder &&
                idFood == that.idFood &&
                quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCart, idOrder, idFood, quality);
    }
}
