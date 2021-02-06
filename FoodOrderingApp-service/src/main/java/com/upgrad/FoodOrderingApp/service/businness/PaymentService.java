package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.PaymentDao;
import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import com.upgrad.FoodOrderingApp.service.exception.PaymentMethodNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*This service class handles all business logic retailed to payment
 *  */
@Service
public class PaymentService {

    @Autowired
    PaymentDao paymentDao;

    /*This method retrieve all payment method
     * */
    public List<PaymentEntity> getAllPaymentMethods() throws  PaymentMethodNotFoundException{
        List<PaymentEntity> paymentEntity = paymentDao.getAllPaymentMethods();
        if(paymentEntity == null){
            throw new PaymentMethodNotFoundException("PNF-001","No payment method found");
        }
        return paymentEntity;
    }


    public PaymentEntity getPaymentByUUID(String uuid) throws  PaymentMethodNotFoundException{
        uuid="89ec97f5-4c58-480c-9899-e0b8649ab3d2";
        PaymentEntity paymentEntity = paymentDao.getPaymentByUUID(uuid);
        if(paymentEntity == null){
            throw new PaymentMethodNotFoundException("PNF-002","No payment method found by this id");
        }
        return paymentEntity;
    }

}
