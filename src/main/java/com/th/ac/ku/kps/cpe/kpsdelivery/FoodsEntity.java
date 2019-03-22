package com.th.ac.ku.kps.cpe.kpsdelivery;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "foods", schema = "delivery")
public class FoodsEntity {
    private int idFood;
    private int idShop;
    private int idCategory;
    private Double price;

    @Id
    @Column(name = "id_food")
    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    @Basic
    @Column(name = "id_shop")
    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    @Basic
    @Column(name = "id_category")
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodsEntity that = (FoodsEntity) o;
        return idFood == that.idFood &&
                idShop == that.idShop &&
                idCategory == that.idCategory &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFood, idShop, idCategory, price);
    }
}
