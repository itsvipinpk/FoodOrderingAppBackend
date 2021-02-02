package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@SuppressWarnings("All")
@Entity
@Table(name = "orders")
public class OrdersEntity {

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

    @Column(name = "coupon_id" )
    private Integer couponId;

    @Column(name ="discount")
    private Double discount;

    @NotNull
    @Column(name = "date")
    private Date date;

    @Column(name = "payment_id")
    private Integer paymentId;

    @NotNull
    @Column(name = "customer_id")
    private   Integer    customerId;

    @NotNull
    @Column(name = "address_id")
    private   Integer    addressId;

    @NotNull
    @Column(name = "restaurant_id")
    private   Integer restaurantId;



}
