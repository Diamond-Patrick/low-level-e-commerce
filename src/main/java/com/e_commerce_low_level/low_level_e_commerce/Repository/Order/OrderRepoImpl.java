package com.e_commerce_low_level.low_level_e_commerce.Repository.Order;

import java.time.LocalDate;
import java.util.List;

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
    public boolean insert(OrderEntity order, ProductEntity product, CustomerEntity customer) {

        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setKodeProduct(product);
            orderEntity.setIdCustomer(customer);
            orderEntity.setOrderQuantities(order.getOrderQuantities());
            orderEntity.setPaymentMethod(order.getPaymentMethod());
            orderEntity.setPurchaceDate(LocalDate.now());

            entityManager.persist(orderEntity);

            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            log.error(e.getMessage());
            return false;

        } finally {
            entityManager.close();
            log.info("entityManager already closed!");
        }
    }

    @Override
    public boolean remove(OrderEntity order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<OrderEntity> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<OrderEntity> findByPaymentMethod(PaymentMethod paymentMethod) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPaymentMethod'");
    }

}
