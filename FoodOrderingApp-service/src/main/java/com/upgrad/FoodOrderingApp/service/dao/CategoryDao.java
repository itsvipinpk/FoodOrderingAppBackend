package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDao {


  @PersistenceContext private EntityManager entityManager;

  /**
   * This method fetches CategoryEntity from database based Category UUID.
   * @param categoryUuid
   * @return CategoryEntity or null if there is no category in database by given categoryUuid.
   */
  public CategoryEntity getCategoryByUuid(final String categoryUuid) {
    try {
      return entityManager
          .createNamedQuery("categoryByUuid", CategoryEntity.class)
          .setParameter("uuid", categoryUuid)
          .getSingleResult();
    } catch (NoResultException nre) {
      return null;
    }
  }

  /**
   * This method fetches all CategoryEntity from db
   * @return List of categoryEntity
   */
  public List<CategoryEntity> getAllCategoriesOrderedByName() {

    return entityManager
        .createNamedQuery("getAllCategoriesOrderedByName", CategoryEntity.class)
        .getResultList();
  }

  /**
   * This method fetches all CategoryEntity from db for given restaurant
   * @param restaurantUuid
   * @return List of categoryEntity
   */
  public List<CategoryEntity> getCategoriesByRestaurant(final String restaurantUuid) {
    try {
      return entityManager
          .createNamedQuery("getCategoriesByRestaurant", CategoryEntity.class)
          .setParameter("restaurantUuid", restaurantUuid)
          .getResultList();
    } catch (NoResultException nre) {
      return null;
    }
  }

    @PersistenceContext
    private EntityManager entityManager;


    //To get category by the id if no result it returns null.
    public CategoryEntity getCategoryByUuid(String uuid) {
        try {
            CategoryEntity categoryEntity = entityManager.createNamedQuery("getCategoryByUuid",CategoryEntity.class).setParameter("uuid",uuid).getSingleResult();
            return categoryEntity;
        }catch (NoResultException nre){
            return null;
        }
    }

    //To get list categories  from the db if no result it returns null.
    public List<CategoryEntity> getAllCategoriesOrderedByName() {
        try {
            List<CategoryEntity> categoryEntities = entityManager.createNamedQuery("getAllCategoriesOrderedByName",CategoryEntity.class).getResultList();
            return categoryEntities;
        }catch (NoResultException nre){
            return null;
        }
    }

}
