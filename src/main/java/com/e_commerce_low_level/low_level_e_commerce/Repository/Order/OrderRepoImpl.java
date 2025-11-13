package com.e_commerce_low_level.low_level_e_commerce.Repository.Order;

import java.time.LocalDate;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.OrderEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.PaymentMethod;
import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRepoImpl implements OrderRepo {

    @Override
    public boolean purchase(CustomerEntity customer, ProductEntity productEntity, PaymentMethod paymentMethod) {

        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            OrderEntity order = new OrderEntity();
            order.setIdCustomer(customer);
            order.setKodeProduct(productEntity);
            order.setPaymentMethod(paymentMethod);
            order.setPurchaceDate(LocalDate.now());

            entityManager.persist(order);

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

}
