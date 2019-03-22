package com.th.ac.ku.kps.cpe.kpsdelivery;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "delivery")
public class OrdersEntity {
    private int idOrder;
    private int idUser;
    private int idDriver;

    @Id
    @Column(name = "id_order")
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
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
    @Column(name = "id_driver")
    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return idOrder == that.idOrder &&
                idUser == that.idUser &&
                idDriver == that.idDriver;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idUser, idDriver);
    }
}
