package com.upgrad.FoodOrderingApp.service.entity;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "coupon")
@NamedQueries(
        {
                @NamedQuery(name = "getCouponByCouponName", query = "SELECT c FROM CouponEntity c WHERE c.couponName = :coupon_name"),
                @NamedQuery(name = "getCouponByCouponId",query = "SELECT c FROM  CouponEntity c WHERE c.uuid = :uuid")
        }
)
public class CouponEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    @Size(max = 200)
    @NotNull
    private String uuid;

    @Column(name = "coupon_name")
    @Size(max = 255)
    private String couponName;

    @Column(name = "percent")
    private Integer percent;

    public CouponEntity(String couponId, String couponName, Integer percent) {
        this.uuid = couponId;
        this.couponName = couponName;
        this.percent = percent;
    }

    public CouponEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CouponEntity that = (CouponEntity) o;

        return new EqualsBuilder().append(id, that.id).append(uuid, that.uuid).append(couponName, that.couponName).append(percent, that.percent).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(uuid).append(couponName).append(percent).toHashCode();
    }

    @Override
    public String toString() {
        return "CouponEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", couponName='" + couponName + '\'' +
                ", percent=" + percent +
                '}';
    }
}
