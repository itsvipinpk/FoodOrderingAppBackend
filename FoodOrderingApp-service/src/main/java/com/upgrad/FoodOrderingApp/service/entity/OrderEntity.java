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
import java.util.Objects;


@SuppressWarnings("All")
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "getOrdersByCouponId", query = "SELECT x FROM OrderEntity x WHERE x.coupon = :couponId"),
        @NamedQuery(name = "getAllOrders", query = "SELECT x FROM OrderEntity x"),
        @NamedQuery(name = "getOrdersByCustomers", query = "SELECT o FROM OrderEntity o WHERE o.customer = :customer ORDER BY o.date DESC"),
        @NamedQuery(name = "getOrdersByAddress", query = "SELECT x FROM OrderEntity x WHERE x.address = :address")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coupon_id")
    private CouponEntity coupon;

    @Column(name = "discount")
    private Double discount;

    @NotNull
    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    public OrderEntity(String orderId, double bill, CouponEntity couponEntity, double discount, Date orderDate, PaymentEntity payment, CustomerEntity customerEntity, AddressEntity addressEntity, RestaurantEntity restaurantEntity) {

        this.uuid = orderId;
        this.bill = bill;
        this.coupon = couponEntity;
        this.discount = discount;
        this.date = orderDate;
        this.payment = payment;
        this.customer = customerEntity;
        this.address = addressEntity;
        this.restaurant = restaurantEntity;
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

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
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

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", bill=" + bill +
                ", coupon=" + coupon +
                ", discount=" + discount +
                ", date=" + date +
                ", payment=" + payment +
                ", customer=" + customer +
                ", address=" + address +
                ", restaurant=" + restaurant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(bill, that.bill) &&
                Objects.equals(coupon, that.coupon) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(date, that.date) &&
                Objects.equals(payment, that.payment) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(address, that.address) &&
                Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, bill, coupon, discount, date, payment, customer, address, restaurant);
    }
}
