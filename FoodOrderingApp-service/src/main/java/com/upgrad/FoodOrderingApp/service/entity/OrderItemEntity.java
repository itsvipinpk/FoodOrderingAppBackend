package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;



@SuppressWarnings("All")
@Entity
@Table(name = "order_item")
@NamedQueries({
        @NamedQuery(name = "orderItemsByOrderId", query = "SELECT x FROM OrderItemEntity x WHERE x.orderId = :uuid")
})
public class OrderItemEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    @NotNull
    private Integer orderId;

    @Column(name = "item_id")
    @NotNull
    private Integer itemId;

    @Column(name = "price")
    @NotNull
    private Integer price;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderItemEntity that = (OrderItemEntity) o;

        return new EqualsBuilder().append(id, that.id).append(orderId, that.orderId).append(itemId, that.itemId).append(price, that.price).append(quantity, that.quantity).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(orderId).append(itemId).append(price).append(quantity).toHashCode();
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
