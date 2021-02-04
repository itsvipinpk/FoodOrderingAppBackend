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
            throw new PaymentMethodNotFoundException("PAY-001","No payment method found");
        }
        return paymentEntity;
    }


    public PaymentEntity getPaymentByUUID(String uuid) throws  PaymentMethodNotFoundException{
        PaymentEntity paymentEntity = paymentDao.getPaymentByUUID(uuid);
        if(paymentEntity == null){
            throw new PaymentMethodNotFoundException("PAY-002","No payment method found");
        }
        return paymentEntity;
    }
}
