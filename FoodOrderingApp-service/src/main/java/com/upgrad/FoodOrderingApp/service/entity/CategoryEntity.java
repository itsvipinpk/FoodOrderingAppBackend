package com.upgrad.FoodOrderingApp.service.entity;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
@NamedQueries(
        {
//                @NamedQuery(name = "getAllRestaurantByCategoryId", query = "select c from CategoryEntity c where c.uuid = :categoryId")
                @NamedQuery(name = "getAllCategories", query = "select c from CategoryEntity c "),
                @NamedQuery(name = "getAllRestaurantByCategoryId", query = "select c from CategoryEntity c "),
                @NamedQuery(
                        name = "categoryByUuid",
                        query = "select c from CategoryEntity c where c.uuid=:uuid order by categoryName"),
                @NamedQuery(
                        name = "getAllCategoriesOrderedByName",
                        query = "select c from CategoryEntity c order by categoryName asc"),
                @NamedQuery(
                        name = "getCategoriesByRestaurant",
                        query =
                                "Select c from CategoryEntity c where id in (select rc.categoryId from RestaurantCategoryEntity rc where rc.restaurantId = "
                                        + "(select r.id from RestaurantEntity r where "
                                        + " r.uuid=:restaurantUuid) )  order by c.categoryName")
        }
)
public class CategoryEntity implements Serializable {

@Table(name = "category",uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid"})})
@NamedQueries({

        @NamedQuery(name = "getCategoryByUuid",query = "SELECT c FROM CategoryEntity c WHERE c.uuid = :uuid"),
        @NamedQuery(name = "getAllCategoriesOrderedByName",query = "SELECT c FROM CategoryEntity c ORDER BY c.categoryName ASC "),
})
public class CategoryEntity implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    @Size(max = 200)
    @NotNull
    private String uuid;


    @NotNull
    @Column(name = "category_name")
    @Size(max = 255)
    private String categoryName;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "restaurant_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    private List<RestaurantEntity> restaurants;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<ItemEntity> items;


    //Created direct relation as the Test Mockito expects ListOf items as a variable in CategoryEntity.
    @ManyToMany
    @JoinTable(name = "category_item", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<ItemEntity> items = new ArrayList<>();



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

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
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
