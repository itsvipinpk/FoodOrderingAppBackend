package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CouponEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDao {

    @PersistenceContext
    private EntityManager entityManager;


    //To get all the order corresponding to the address
    public List<OrderEntity> getOrdersByAddress(AddressEntity addressEntity) {
        try {
            List<OrderEntity> ordersEntities = entityManager.createNamedQuery("getOrdersByAddress", OrderEntity.class).setParameter("address", addressEntity).getResultList();
            return ordersEntities;
        } catch (NoResultException nre) {
            return null;
        }
    }

    //To get all the coupon corresponding to the coupon id
    public CouponEntity getCouponByCouponId(String couponId) {
        try {
            OrderEntity entity = entityManager.createNamedQuery("getOrdersByCouponId", OrderEntity.class).setParameter("couponId", couponId).getSingleResult();
            return entity.getCoupon();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public OrderEntity saveOrder(OrderEntity orderEntity) {
        entityManager.merge(orderEntity);
        return orderEntity;
    }

    public OrderItemEntity saveOrderItem(OrderItemEntity orderItemEntity) {
        entityManager.merge(orderItemEntity);
        return orderItemEntity;
    }

    public List<OrderEntity> getOrdersByCustomers(Integer customerId) {
        try {
            List<OrderEntity> orderEntities = entityManager.createNamedQuery("getOrdersByCustomerId", OrderEntity.class).setParameter("customerId", customerId).getResultList();
            return orderEntities;
        } catch (NoResultException nre) {
            return null;
        }
    }
}
