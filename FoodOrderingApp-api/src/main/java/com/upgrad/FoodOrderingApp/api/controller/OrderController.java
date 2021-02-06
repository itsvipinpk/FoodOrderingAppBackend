package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.*;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ItemService itemService;



    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/order/coupon/{coupon_name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CouponDetailsResponse> getCouponByCouponName(@RequestHeader(value = "authorization") final String authorization, @PathVariable(value = "coupon_name") final String couponName) throws AuthorizationFailedException, CouponNotFoundException {
        String[] authParts = authorization.split("Bearer ");
        final String accessToken = authParts[1];

        CustomerEntity customerEntity = customerService.getCustomer(accessToken);

        CouponEntity couponEntity = orderService.getCouponByCouponName(couponName);

        CouponDetailsResponse couponDetailsResponse = new CouponDetailsResponse()
                .couponName(couponEntity.getCouponName())
                .id(UUID.fromString(couponEntity.getUuid()))
                .percent(couponEntity.getPercent());

        return new ResponseEntity<CouponDetailsResponse>(couponDetailsResponse, HttpStatus.OK);


    }

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


            List<OrderItemEntity> orderItemsByOrderId = orderService.getOrderItemsByOrder(e);
            List<ItemQuantityResponse> itemQuantities = new ArrayList<>();
            for (OrderItemEntity oi : orderItemsByOrderId) {

                ItemEntity itemEntity = orderService.getItemByItemId(oi.getItem().getId());
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


    //POST saveorder
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/order", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SaveOrderResponse> saveOrder(@RequestHeader(value = "authorization") final String authorization, @RequestBody(required = false) final SaveOrderRequest saveOrderRequest) throws AuthorizationFailedException, PaymentMethodNotFoundException, AddressNotFoundException, RestaurantNotFoundException, ItemNotFoundException, CouponNotFoundException {
        String[] authParts = authorization.split("Bearer ");
        final String accessToken = authParts[1];

        OrderEntity orderEntity= new OrderEntity();

        CustomerEntity customerEntity = customerService.getCustomer(accessToken);

        CouponEntity couponEntity = orderService.getCouponByCouponId(saveOrderRequest.getCouponId().toString());

        PaymentEntity paymentEntity = paymentService.getPaymentByUUID(saveOrderRequest.getPaymentId().toString());

        AddressEntity addressEntity = addressService.getAddressByUUID(saveOrderRequest.getAddressId(), customerEntity);

        RestaurantEntity restaurantEntity = restaurantService.restaurantByUUID(saveOrderRequest.getRestaurantId().toString());

        orderEntity.setBill(saveOrderRequest.getBill().doubleValue());
        orderEntity.setUuid(UUID.randomUUID().toString());
        orderEntity.setDiscount(saveOrderRequest.getDiscount().doubleValue());
        orderEntity.setCoupon(couponEntity);
        orderEntity.setCustomer(customerEntity);
        orderEntity.setDate(new Timestamp(System.currentTimeMillis()));
        orderEntity.setRestaurant(restaurantEntity);
        orderEntity.setPayment(paymentEntity);
        orderEntity.setAddress(addressEntity);



        OrderEntity updatedOrderEntity= orderService.saveOrder(orderEntity);

        List<ItemQuantity> itemQuantities = saveOrderRequest.getItemQuantities();
        for(ItemQuantity i : itemQuantities){

            OrderItemEntity orderItemEntity = new OrderItemEntity();

            ItemEntity itemEntity =  itemService.getItemByUUID(i.getItemId().toString());

            orderItemEntity.setItem(itemEntity);
            orderItemEntity.setOrder(orderEntity);
            orderItemEntity.setPrice(i.getPrice());
            orderItemEntity.setQuantity(i.getQuantity());

            OrderItemEntity savedOrderItem = orderService.saveOrderItem(orderItemEntity);

        }

        SaveOrderResponse response = new SaveOrderResponse().id(updatedOrderEntity.getUuid()).status("ORDER SUCCESSFULLY PLACED");
       return new ResponseEntity<SaveOrderResponse>(response,HttpStatus.CREATED);





    }



}