package com.e_commerce_low_level.low_level_e_commerce.Repository;

import java.util.UUID;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerRepoImpl implements CustomerRepo {

    @Override
    public boolean insert(CustomerEntity customerEntity) {

        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CustomerEntity customer = new CustomerEntity();
            customer.setIdCustomer(UUID.randomUUID().toString().substring(0, 4));
            customer.setName(customerEntity.getName());
            customer.setEmail(customerEntity.getEmail());
            customer.setPassword(customerEntity.getPassword());
            customer.setAddress(customerEntity.getAddress());

            entityManager.persist(customer);

            transaction.commit();
            return true;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
            return false;

        } finally {
            entityManager.close();
            log.info("entityManager already closed !");
        }
    }

    @Override
    public void remove(CustomerEntity customerEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void update(String id, CustomerEntity customerEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CustomerEntity find(CustomerEntity customerEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

}
