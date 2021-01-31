package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "flat_buil_number")
    private String flatBuilNumber;

    @Column(name ="locality")
    private String locality;

    @Column(name ="city")
    private String city;

    @Column(name ="pincode")
    private   String  pincode;

    @Column(name ="state_id")
      private Integer stateId;

     @Column(name ="active")
     private  Integer  active;

}
