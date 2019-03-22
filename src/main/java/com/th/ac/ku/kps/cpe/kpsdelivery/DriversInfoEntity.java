package com.th.ac.ku.kps.cpe.kpsdelivery;

import com.th.ac.ku.kps.cpe.kpsdelivery.allenum.DriverInfoStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "drivers_info", schema = "delivery")
public class DriversInfoEntity {
    private int idDriverInfo;
    private int idUser;
    private Double latitude;
    private Double longtitude;
    private DriverInfoStatus status;

    @Id
    @Column(name = "id_driver_info")
    public int getIdDriverInfo() {
        return idDriverInfo;
    }

    public void setIdDriverInfo(int idDriverInfo) {
        this.idDriverInfo = idDriverInfo;
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
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public DriverInfoStatus getStatus() {
        return status;
    }

    public void setStatus(DriverInfoStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriversInfoEntity that = (DriversInfoEntity) o;
        return idDriverInfo == that.idDriverInfo &&
                idUser == that.idUser &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longtitude, that.longtitude) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDriverInfo, idUser, latitude, longtitude, status);
    }
}
