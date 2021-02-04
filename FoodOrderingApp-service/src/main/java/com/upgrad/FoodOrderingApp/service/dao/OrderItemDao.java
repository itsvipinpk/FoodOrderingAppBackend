package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.OrderItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderItemDao {

    @PersistenceContext
    private EntityManager entityManager;


    //To get all the order corresponding to the address
    public List<OrderItemEntity> getOrderItemsByOrderId(String orderUuid) {
        try {
            List<OrderItemEntity> orderItemEntities = entityManager.createNamedQuery("orderItemsByOrderId", OrderItemEntity.class).setParameter("uuid", orderUuid).getResultList();
            return orderItemEntities;
        } catch (NoResultException nre) {
            return null;
        }
    }

}
