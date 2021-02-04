package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.*;
import com.upgrad.FoodOrderingApp.service.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    RestaurantDao restaurantDao;

    @Autowired
    RestaurantItemDao restaurantItemDao;

    @Autowired
    CategoryItemDao categoryItemDao;

    @Autowired
    CategoryDao categoryDao;


    /**
     * This method gets top five popular items of a restaurant.
     *
     * @param restaurantEntity Restaurant whose top five items are to be queried.
     * @return top five items
     */
    public List<ItemEntity> getItemsByPopularity(RestaurantEntity restaurantEntity) {
        return itemDao.getOrdersByRestaurant(restaurantEntity);
    }



    /* This method is to get Items By Category And Restaurant and returns list of ItemEntity it takes restaurantUuid & categoryUuid as input.
     */
    public List<ItemEntity> getItemsByCategoryAndRestaurant(String restaurantUuid, String categoryUuid) {

        //Calls getRestaurantByUuid of restaurantDao to get the  RestaurantEntity
        RestaurantEntity restaurantEntity = restaurantDao.restaurantByUUID(restaurantUuid);

        //Calls getCategoryByUuid of categoryDao to get the  CategoryEntity
        CategoryEntity categoryEntity = categoryDao.getCategoryByUuid(categoryUuid);

        //Calls getItemsByRestaurant of restaurantItemDao to get the  list of RestaurantItemEntity
        List<RestaurantItemEntity> restaurantItemEntities = restaurantItemDao.getItemsByRestaurant(restaurantEntity);

        //Calls getItemsByCategory of categoryItemDao to get the  list of CategoryItemEntity
        List<CategoryItemEntity> categoryItemEntities = categoryItemDao.getItemsByCategory(categoryEntity);

        //Creating list of item entity common to the restaurant and category.
        List<ItemEntity> itemEntities = new LinkedList<>();

        restaurantItemEntities.forEach(restaurantItemEntity -> {
            categoryItemEntities.forEach(categoryItemEntity -> {
                if(restaurantItemEntity.getItem().equals(categoryItemEntity.getItem())){
                    itemEntities.add(restaurantItemEntity.getItem());
                }
            });
        });

        return itemEntities;
    }
}
