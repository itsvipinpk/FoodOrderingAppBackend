package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.ItemDao;
import com.upgrad.FoodOrderingApp.service.dao.OrderItemDao;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private ItemDao itemDao;


    public CouponEntity getCouponByCouponId(String couponId) {
        return orderDao.getCouponByCouponId(couponId);
    }

    public OrderEntity saveOrder(OrderEntity orderEntity) {

        //Updating the order in the db using the method saveOrder of orderDao.
        OrderEntity updatedEntity = orderDao.saveOrder(orderEntity);

        return updatedEntity;
    }


    public OrderItemEntity saveOrderItem(OrderItemEntity orderItemEntity) {
        //Updating the OrderItemEntity in the db using the method saveOrderItem of orderDao.
        OrderItemEntity updatedEntity = orderDao.saveOrderItem(orderItemEntity);

        return updatedEntity;
    }

    public List<OrderEntity> getOrdersByCustomers(String customerUuid) {
        CustomerEntity customer = customerDao.getCustomerByCustomerUuid(customerUuid);
        //fetching list of orders for a given customer id
        return orderDao.getOrdersByCustomers(customer.getId());

    }

    public Object getCouponByCouponName(String myCoupon) {
        return null;
    }

    public List<OrderItemEntity> getOrderItemsByOrderId(String orderUuid) {
        return orderItemDao.getOrderItemsByOrderId(orderUuid);
    }

    public ItemEntity getItemByItemId(Integer itemId) {
        return itemDao.getItemByItemId(itemId);
    }
}


