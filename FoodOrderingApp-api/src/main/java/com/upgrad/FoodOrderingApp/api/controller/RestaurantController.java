package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.RestaurantList;
import com.upgrad.FoodOrderingApp.api.model.RestaurantListResponse;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerResponse;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RestaurantController {
    @Autowired
    private RestaurantBusinessService restaurantBusinessService;

//    @CrossOrigin
//    @RequestMapping(
//            method = RequestMethod.GET,
//            path = "/restaurant",
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<RestaurantListResponse> getAllRestaurants ( ) {
//
//        List<RestaurantEntity> allQuestions = restaurantBusinessService.getAllRestaurant();
////        RestaurantListResponse
//
//
//    }


}

