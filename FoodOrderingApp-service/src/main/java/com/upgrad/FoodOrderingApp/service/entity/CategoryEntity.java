package com.upgrad.FoodOrderingApp.service.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "category")

public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "uuid", unique = true)
    private String uuid;


    @Column(name = "category_name")
    private String categoryName;

//    @OneToMany(mappedBy = "restaurant_category")
//    private List<RestaurantCategory> restaurantCategoryList;

//    @ManyToMany(mappedBy = "categoryEntityList")
//    List<RestaurantEntity> restaurantEntityList;
//
//
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "category")
//    List<RestaurantEntity> restaurantEntityList;

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


//    public List<RestaurantEntity> getRestaurantEntityList() {
//        return restaurantEntityList;
//    }
//
//    public void setRestaurantEntityList(List<RestaurantEntity> restaurantEntityList) {
//        this.restaurantEntityList = restaurantEntityList;
//    }
}
