package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/*
 * This Entity class represents the "payment" table in DB
 * */

@Entity
@Table(name = "payment",uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid"})})
@NamedQueries({
        @NamedQuery(name = "getAllPayments",query = "SELECT p FROM PaymentEntity p"),
        @NamedQuery(name = "getPaymentByUUID",query = "SELECT p FROM PaymentEntity p WHERE p.uuid = :uuid")
})
public class PaymentEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    @Size(max = 200)
    @NotNull
    private String uuid;

    @Column(name = "payment_name")
    @Size(max = 255)
    private String paymentName;

    public PaymentEntity(){

    }

    public PaymentEntity(String paymentId, String paymentName) {

        this.uuid = paymentId;
        this.paymentName = paymentName;
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

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        return new EqualsBuilder().append(id, that.id).append(uuid, that.uuid).append(paymentName, that.paymentName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(uuid).append(paymentName).toHashCode();
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", paymentName='" + paymentName + '\'' +
                '}';
    }
}
