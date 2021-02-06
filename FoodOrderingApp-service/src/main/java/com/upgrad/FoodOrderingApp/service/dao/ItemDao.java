package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * This method gets top five popular items of a restaurant.
     *
     * @param restaurant Restaurant whose top five items are to be queried.
     * @return top five items
     */
    public List<ItemEntity> getOrdersByRestaurant(RestaurantEntity restaurant) {
        List<ItemEntity> items =
                entityManager
                        .createNamedQuery("topFivePopularItemsByRestaurant", ItemEntity.class)
                        .setParameter(0, restaurant.getId())
                        .getResultList();
        if (items != null) {
            return items;
        }
        return Collections.emptyList();
    }


    public ItemEntity getItemByItemId(Integer itemId) {

        ItemEntity item = entityManager
                .createNamedQuery("itemByItemId", ItemEntity.class)
                .setParameter("id", itemId)
                .getSingleResult();
        return item;
    }

    public ItemEntity getItemsByUuid(String itemUuid) {
        try {
            return entityManager.createNamedQuery("getItemsByUuid", ItemEntity.class).setParameter("uuid", itemUuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
