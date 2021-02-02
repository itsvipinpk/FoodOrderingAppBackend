package com.upgrad.FoodOrderingApp.service.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
@NamedQueries(
        {
//                @NamedQuery(name = "getAllRestaurantByCategoryId", query = "select c from CategoryEntity c where c.uuid = :categoryId")
                @NamedQuery(name = "getAllCategories", query = "select c from CategoryEntity c "),
                @NamedQuery(name = "getAllRestaurantByCategoryId", query = "select c from CategoryEntity c ")
        }
)

public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Size(max = 255)
    @Column(name = "category_name")
    private String categoryName;

    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "category")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "category")
    private List<RestaurantEntity> restaurants = new ArrayList<RestaurantEntity>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "category")
    private List<ItemEntity> items = new ArrayList<ItemEntity>();



    /*  getters and setters
     * */

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<RestaurantEntity> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantEntity> restaurants) {
        this.restaurants = restaurants;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
