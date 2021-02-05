package com.upgrad.FoodOrderingApp.service.dao;


import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerAuthDao {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * This method stores authentication access token in the database
     */
    public void createCustomerAuthToken(CustomerAuthEntity customerAuthEntity) {
        entityManager.persist(customerAuthEntity);
    }



    /**
     * @param accessToken access-token obtained during successful login.
     * @return CustomerAuthEntity object if given access token exists in the database.
     * @author: Vipin P K
     */
    public CustomerAuthEntity getCustomerAuthByToken(final String accessToken) {
        try {
            return entityManager
                    .createNamedQuery("customerAuthByToken", CustomerAuthEntity.class)
                    .setParameter("accessToken", accessToken)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * This method updates the customers logout time in the database.
     * @param updatedCustomerAuthEntity CustomerAuthEntity object to update.
     */
    public void updateCustomerAuth(final CustomerAuthEntity updatedCustomerAuthEntity) {
        entityManager.merge(updatedCustomerAuthEntity);
    }
}