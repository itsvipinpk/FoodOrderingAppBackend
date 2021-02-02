package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
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


}

