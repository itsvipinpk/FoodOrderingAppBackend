package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.*;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.exception.CouponNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private CouponDao couponDao;

    public CouponEntity getCouponByCouponId(String couponId) throws CouponNotFoundException {
        CouponEntity couponEntity = couponDao.getCouponByCouponId(couponId);
        if(couponEntity == null){
            throw new CouponNotFoundException("CPF-002","No coupon by this id");
        }
        return couponEntity;

    }
    public CouponEntity getCouponByCouponName(String couponName) throws CouponNotFoundException {
        if (couponName == null || couponName == "") {
            throw new CouponNotFoundException("CPF-002", "Coupon name field should not be empty");
        }
        CouponEntity couponEntity = couponDao.getCouponByCouponName(couponName);
        if (couponEntity == null) {
            throw new CouponNotFoundException("CPF-001", "No coupon by this name");
        }
        return couponEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderEntity saveOrder(OrderEntity orderEntity) {
        OrderEntity updatedEntity = orderDao.saveOrder(orderEntity);
        return updatedEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderItemEntity saveOrderItem(OrderItemEntity orderItemEntity) {
        OrderItemEntity updatedEntity = orderItemDao.saveOrderItem(orderItemEntity);
        return updatedEntity;
    }
    public List<OrderEntity> getOrdersByCustomers(String customerUuid) {
        CustomerEntity customer = customerDao.getCustomerByCustomerUuid(customerUuid);
        return orderDao.getOrdersByCustomers(customer);

    }

    public ItemEntity getItemByItemId(Integer itemId) {
        return itemDao.getItemByItemId(itemId);
    }

    public List<OrderItemEntity> getOrderItemsByOrder(OrderEntity e) {
        return orderItemDao.getOrderItemsByOrder(e);
    }


}


