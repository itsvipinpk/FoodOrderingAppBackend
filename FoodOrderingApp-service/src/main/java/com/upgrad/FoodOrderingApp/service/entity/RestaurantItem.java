package com.upgrad.FoodOrderingApp.service.entity;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant_item")
public class RestaurantItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @NotNull
//    @OneToOne
//    @JoinColumn(name="item_id")
//     private ItemEntity itemId;
//
//    @OneToOne
//    @JoinColumn(name= "restaurant_id")
//    private RestaurantEntity restaurantId;
}
