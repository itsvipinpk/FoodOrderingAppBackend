package com.upgrad.FoodOrderingApp.service.dao;
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

        List<RestaurantEntity> resultList= entityManager.createNativeQuery("SELECT r.*, c.category_name\n" +
                    "  FROM restaurant r\n" +
                    "LEFT OUTER JOIN restaurant_category rc\n" +
                    "  ON r.id = rc.restaurant_id\n" +
                    "LEFT OUTER JOIN category c\n" +
                    "  ON rc.category_id = c.id").getResultList();


            return resultList;
        } catch (Exception e) {
            return null;
        }
    }


}

