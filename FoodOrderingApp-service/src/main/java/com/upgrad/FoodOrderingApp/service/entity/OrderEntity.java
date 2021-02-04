package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("All")
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "getOrdersByCouponId", query = "SELECT x FROM OrderEntity x WHERE x.coupon = :couponId"),
        @NamedQuery(name = "getAllOrders", query = "SELECT x FROM OrderEntity x")

})
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "uuid", unique = true)
    private String uuid;

    @NotNull
    @Column(name = "bill")
    private Double bill;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coupon_id")
    private CouponEntity coupon;

    @Column(name = "discount")
    private Double discount;

    @NotNull
    @Column(name = "date")
    private Date date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "address_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AddressEntity address;

    @NotNull
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    public OrderEntity(String orderId, double bill, CouponEntity couponEntity, double discount, Date orderDate, PaymentEntity payment, CustomerEntity customerEntity, AddressEntity addressEntity, RestaurantEntity restaurantEntity) {

        this.uuid = orderId;
        this.bill = bill;
        this.coupon = couponEntity;
        this.discount = discount;
        this.date = orderDate;
        this.payment = payment;
        this.customer = customerEntity;
        this.address = addressEntity;
        this.restaurantId = restaurantEntity.getId();
    }

    public OrderEntity() {

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

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    public CouponEntity getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        return new EqualsBuilder().append(id, that.id).append(uuid, that.uuid).append(bill, that.bill).append(coupon, that.coupon).append(discount, that.discount).append(date, that.date).append(payment, that.payment).append(customer, that.customer).append(address, that.address).append(restaurantId, that.restaurantId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(uuid).append(bill).append(coupon).append(discount).append(date).append(payment).append(customer).append(address).append(restaurantId).toHashCode();
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", bill=" + bill +
                ", coupon=" + coupon +
                ", discount=" + discount +
                ", date=" + date +
                ", paymentId=" + payment +
                ", customerId=" + customer +
                ", address=" + address +
                ", restaurantId=" + restaurantId +
                '}';
    }


    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public AddressEntity getAddress() {
        return address;
    }
}
