package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "restaurant")
@NamedQueries(
        {
                @NamedQuery( name = "getAllRestaurants", query = "select r from RestaurantEntity r")
        }
)
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "uuid", unique = true)
    private String uuid;

    @NotNull
    @Size(max = 50)
    @Column(name = "restaurant_name")
    private String restaurantName;

    @Size(max = 255)
    @Column(name = " photo_url")
    private String  photoUrl;

    @NotNull
    @Column(name = "customer_rating")
    private Double customerRating;

    @NotNull
    @Column(name ="average_price_for_two")
    private Integer AveragePriceForTwo;

    @NotNull
    @Column(name ="number_of_customers_rated")
    private Integer numberOfCustomersRated;

    @OneToOne
    @JoinColumn(name= "address_id")
    private Address address;

//    @OneToMany(mappedBy= "restaurant_category",fetch = FetchType.EAGER)
//     private List<RestaurantCategory> category;

//    @ManyToMany
//    @JoinTable(
//            name = "restaurant_category",
//            joinColumns = @JoinColumn(name = "restaurant_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    List<CategoryEntity> categoryEntityList;

//    public List<CategoryEntity> getCategoryEntityList() {
//        return categoryEntityList;
//    }
//
//    public void setCategoryEntityList(List<CategoryEntity> categoryEntityList) {
//        this.categoryEntityList = categoryEntityList;
//    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Double getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(Double customerRating) {
        this.customerRating = customerRating;
    }

    public Integer getAveragePriceForTwo() {
        return AveragePriceForTwo;
    }

    public void setAveragePriceForTwo(Integer averagePriceForTwo) {
        AveragePriceForTwo = averagePriceForTwo;
    }

    public Integer getNumberOfCustomersRated() {
        return numberOfCustomersRated;
    }

    public void setNumberOfCustomersRated(Integer numberOfCustomersRated) {
        this.numberOfCustomersRated = numberOfCustomersRated;
    }



    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this, obj).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
