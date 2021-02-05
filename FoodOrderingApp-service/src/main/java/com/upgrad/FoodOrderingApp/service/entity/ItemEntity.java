package com.upgrad.FoodOrderingApp.service.entity;

import com.upgrad.FoodOrderingApp.service.common.ItemType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
@Entity
@Table(name = "item")
@NamedQueries(
        {
                @NamedQuery(name = "getAllItems", query = "select i from ItemEntity i ")
        }
)
@NamedNativeQueries({
        // Using native query as named queries do not support LIMIT in nested statements.
        @NamedNativeQuery(
                name = "topFivePopularItemsByRestaurant",
                query =
                        "select * from item where id in "
                                + "(select item_id from order_item where order_id in "
                                + "(select id from orders where restaurant_id = ? ) "
                                + "group by order_item.item_id "
                                + "order by (count(order_item.order_id)) "
                                + "desc LIMIT 5)",
                resultClass = ItemEntity.class)
})

public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "uuid", unique = true)
    private String uuid;

    @NotNull
    @Size(max = 30)
    @Column(name = "item_name")
    private String itemName;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @Column(name = "type")
    @Size(max = 10)
    @NotNull
    private ItemType type;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_item",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<CategoryEntity> category = new ArrayList<CategoryEntity>();


    /* getters and setters
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {

        this.type = type;
    }


}
