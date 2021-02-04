package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantService;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantBusinessService;

    /*
     It's a  GET request , with no parameters
     retrieve all the restaurants in order of their ratings and,
     display the response in a JSON format with the corresponding HTTP status
     Within each restaurant, the list of categories is displayed in a categories string in
     alphabetical order of their category name
    * */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/restaurant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getAllRestaurants() {
        final List<RestaurantEntity> restaurantEntities = restaurantBusinessService.getAllRestaurant();
        List<RestaurantList> restaurantLists = new ArrayList<>();
        RestaurantListResponse restaurantListResponse = new RestaurantListResponse();

        for (int i = 0; i < restaurantEntities.size(); i++) {
            List<CategoryEntity> category = restaurantEntities.get(i).getCategory();
            String categoryName = new String();
            for (int j = 0; j < category.size(); j++) {

                categoryName += category.get(j).getCategoryName();
                categoryName += ",";
            }
            String[] split = categoryName.split(",");
            Arrays.sort(split);
            Arrays.toString(split);
            String resultCategoryValues = String.join(",", split);

            restaurantLists.add(
                    new RestaurantList()
                            .id(UUID.fromString(restaurantEntities.get(i).getUuid()))
                            .restaurantName(restaurantEntities.get(i).getRestaurantName())
                            .photoURL(restaurantEntities.get(i).getPhotoUrl())
                            .customerRating(restaurantEntities.get(i).getCustomerRating())
                            .averagePrice(restaurantEntities.get(i).getAveragePriceForTwo())
                            .numberCustomersRated(restaurantEntities.get(i).getNumberOfCustomersRated())
                            .address(
                                    new RestaurantDetailsResponseAddress()
                                            .id(UUID.fromString(restaurantEntities.get(i).getAddressEntity().getUuid()))
                                            .flatBuildingName(restaurantEntities.get(i).getAddressEntity().getFlatBuilNumber())
                                            .locality(restaurantEntities.get(i).getAddressEntity().getLocality())
                                            .city(restaurantEntities.get(i).getAddressEntity().getCity())
                                            .pincode(restaurantEntities.get(i).getAddressEntity().getPincode())
                                            .state(new RestaurantDetailsResponseAddressState()
                                                    .id(UUID.fromString(restaurantEntities.get(i).getAddressEntity().getState().getStateUuid()))
                                                    .stateName(restaurantEntities.get(i).getAddressEntity().getState().getStateName())
                                            )
                            )
                            .categories(resultCategoryValues)
            );
        }
        restaurantListResponse.setRestaurants(restaurantLists);
        return new ResponseEntity(restaurantListResponse, HttpStatus.OK);

    }
}


