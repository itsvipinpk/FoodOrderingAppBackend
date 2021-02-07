package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDao {

    @PersistenceContext
    private EntityManager entityManager;


   /* To get List of order from the DB , corresponding to a address
   * */
    public List<OrderEntity> getOrdersByAddress(AddressEntity addressEntity) {
        try {
            List<OrderEntity> ordersEntities = entityManager.createNamedQuery("getOrdersByAddress", OrderEntity.class).setParameter("address", addressEntity).getResultList();
            return ordersEntities;
        } catch (NoResultException nre) {
            return null;
        }
    }


    /* To save order in the DB
     * */
    public OrderEntity saveOrder(OrderEntity orderEntity) {
        entityManager.persist(orderEntity);
        return orderEntity;
    }


    /* To get List of order from the db, corresponding to a customer
     * */
    public List<OrderEntity>getOrdersByCustomers(CustomerEntity customerEntity){
        try {
            return entityManager.createNamedQuery("getOrdersByCustomers", OrderEntity.class).setParameter("customer",customerEntity).getResultList();
        }catch (NoResultException e){
            return null;
        }

    }
}
