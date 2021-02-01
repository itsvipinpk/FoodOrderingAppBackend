package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    //To save the address
    public AddressEntity saveAddress(AddressEntity addressEntity){
        entityManager.persist(addressEntity);
        return addressEntity;
    }
}
