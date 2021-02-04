package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
import com.upgrad.FoodOrderingApp.service.businness.OrderService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderItemEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerOrderResponse> getAllCustomerOrders(
            @RequestHeader("authorization") final String authorization
    ) throws AuthorizationFailedException {

        String[] authParts = authorization.split("Bearer ");
        final String accessToken = authParts[1];

        CustomerEntity customerEntity = customerService.getCustomer(accessToken);

        OrderListCustomer orderListCustomer = new OrderListCustomer();
        orderListCustomer.contactNumber(customerEntity.getContactNumber())
                .emailAddress(customerEntity.getEmailAddress())
                .id(UUID.fromString(customerEntity.getUuid()))
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName());


        List<OrderEntity> ordersByCustomers = orderService.getOrdersByCustomers(customerEntity.getUuid());
        List<OrderList> orderListResponse = new ArrayList<>();
        for (OrderEntity e : ordersByCustomers) {
            OrderListCoupon coupon = new OrderListCoupon();
            coupon.couponName(e.getCoupon().getCouponName())
                    .percent(e.getCoupon().getPercent())
                    .id(UUID.fromString(e.getCoupon().getUuid()));

            OrderListAddressState state = new OrderListAddressState();
            state.id(UUID.fromString(e.getAddress().getState().getStateUuid()))
                    .stateName(e.getAddress().getState().getStateName());

            OrderListAddress address = new OrderListAddress();
            address.id(UUID.fromString(e.getAddress().getUuid()))
                    .city(e.getAddress().getCity())
                    .flatBuildingName(e.getAddress().getFlatBuilNumber())
                    .locality(e.getAddress().getLocality())
                    .city(e.getAddress().getCity())
                    .pincode(e.getAddress().getPincode())
                    .state(state);

            OrderListPayment payment = new OrderListPayment();
            payment.id(UUID.fromString(e.getPayment().getUuid()))
                    .paymentName(e.getPayment().getPaymentName());


            List<OrderItemEntity> orderItemsByOrderId = orderService.getOrderItemsByOrderId(e.getUuid());
            List<ItemQuantityResponse> itemQuantities = new ArrayList<>();
            for (OrderItemEntity oi : orderItemsByOrderId) {

                ItemEntity itemEntity = orderService.getItemByItemId(oi.getItemId());
                ItemQuantityResponseItem itemQty = new ItemQuantityResponseItem();
                itemQty.id(UUID.fromString(itemEntity.getUuid()))
                        .itemName(itemEntity.getItemName())
                        .itemPrice(itemEntity.getPrice())
                        .type(ItemQuantityResponseItem.TypeEnum.fromValue(itemEntity.getType().getValue()));


                ItemQuantityResponse item = new ItemQuantityResponse();
                item.price(oi.getPrice())
                        .quantity(oi.getQuantity())
                        .item(itemQty);

                itemQuantities.add(item);

            }
            OrderList data = new OrderList();
            data.date(e.getDate().toString())
                    .id(UUID.fromString(e.getUuid()))
                    .bill(BigDecimal.valueOf(e.getBill()))
                    .discount(BigDecimal.valueOf(e.getDiscount()))
                    .coupon(coupon)
                    .customer(orderListCustomer)
                    .address(address)
                    .payment(payment)
                    .itemQuantities(itemQuantities);

            orderListResponse.add(data);

        }
        //Creating the OrderList
        CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
        customerOrderResponse.setOrders(orderListResponse);
        return new ResponseEntity<CustomerOrderResponse>(customerOrderResponse, HttpStatus.OK);


    }

}