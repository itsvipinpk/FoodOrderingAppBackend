package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.SignupCustomerRequest;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerResponse;
import com.upgrad.FoodOrderingApp.service.businness.CustomerBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CustomerController {

    /**
     * @CustomerBusinessService :Customer business logic services are implemented here.
     * */
    @Autowired
    private CustomerBusinessService customerBusinessService;


    /**
     * This endpoint is used to signup a new customer
     *
     * @param signupCustomerRequest this argument contains all the attributes required for creating a
     *     new customer in the database.
     * @return ResponseEntity<SignupCustomerResponse> type object along with HttpStatus CREATED.
     * @throws SignUpRestrictedException if customer entered details not meet the requirements.
     * @author: Vipin P K
     */

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/customer/signup",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupCustomerResponse> signup
            ( @RequestBody(required = false) final SignupCustomerRequest signupCustomerRequest) throws SignUpRestrictedException {

        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(signupCustomerRequest.getFirstName());
        customerEntity.setLastName(signupCustomerRequest.getLastName());
        customerEntity.setEmailAddress(signupCustomerRequest.getEmailAddress());
        customerEntity.setContactNumber(signupCustomerRequest.getContactNumber());
        customerEntity.setPassword(signupCustomerRequest.getPassword());

        CustomerEntity createdCustomerEntity = customerBusinessService.createCustomer(customerEntity);

        SignupCustomerResponse signupCustomerResponse =
                new SignupCustomerResponse()
                        .id(createdCustomerEntity.getUuid())
                        .status("CUSTOMER SUCCESSFULLY REGISTERED");


        return new ResponseEntity<SignupCustomerResponse>(signupCustomerResponse, HttpStatus.CREATED);

    }

}
