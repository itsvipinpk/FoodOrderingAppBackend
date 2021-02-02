package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@SuppressWarnings("all")
public class RestaurantDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<RestaurantEntity> getAllRestaurant() {
        try {
            List<RestaurantEntity> resultList = entityManager.createNamedQuery("getAllRestaurants", RestaurantEntity.class).getResultList();
            return resultList;

        } catch (Exception e) {
            return null;
        }
    }


    public List<ItemEntity> getAllItems() {
        try {
            List<ItemEntity> resultList = entityManager.createNamedQuery("getAllItems", ItemEntity.class).getResultList();
            return resultList;

        } catch (Exception e) {
            return null;
        }
    }

    public List<CategoryEntity> getAllCategories() {
        try {
            List<CategoryEntity> resultList = entityManager.createNamedQuery("getAllCategories", CategoryEntity.class).getResultList();
            return resultList;

        } catch (Exception e) {
            return null;
        }
    }

            /**
             * Fetch the restaurant based on UUID.
             *
             * @param uuid
             * @return RestaurantEntity if found in database else null.
             */
            public RestaurantEntity restaurantByUUID (String uuid){
                try {
                    return entityManager
                            .createNamedQuery("restaurantByUUID", RestaurantEntity.class)
                            .setParameter("uuid", uuid)
                            .getSingleResult();
                } catch (NoResultException nre) {

                    return null;
                }
            }

    }