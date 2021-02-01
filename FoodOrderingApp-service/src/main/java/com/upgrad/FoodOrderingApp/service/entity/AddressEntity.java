package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("all")
@Entity
@Table(name = "address",uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid"})})
@NamedQueries({

        @NamedQuery(name = "getAddressByUuid",query = "SELECT a from AddressEntity a where a.uuid = :uuid"),
})

public class AddressEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    @Size(max = 200)
    @NotNull
    private String uuid;

    @Column(name = "flat_buil_number")
    @Size(max = 255)
    private String flatBuilNo;

    @Column(name = "locality")
    @Size(max = 255)
    private String locality;

    @Column(name = "city")
    @Size(max = 30)
    private String city;

    @Column(name = "pincode")
    @Size(max = 30)
    private String pincode;

    @Column(name = "active")
    private Integer active = 1;


}
