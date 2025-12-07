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
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            OrderEntity orderEntity = entityManager.find(OrderEntity.class, order.getId());

            entityManager.remove(orderEntity);

            transaction.commit();
            return true;

        } catch (Exception e) {
            log.error(e.getMessage());
            transaction.rollback();
            return false;

        } finally {
            entityManager.close();
            log.info("entityManager already closed!");
        }

    }

    @Override
    public List<OrderEntity> findAll() {

        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            // SELECT * FROM product;
            CriteriaQuery<OrderEntity> query = criteriaBuilder
                    .createQuery(OrderEntity.class);
            Root<OrderEntity> oE = query.from(OrderEntity.class);
            query.select(oE);

            TypedQuery<OrderEntity> typedQuery = entityManager.createQuery(query);
            List<OrderEntity> resultList = typedQuery.getResultList();

            transaction.commit();
            return resultList;

        } catch (Exception e) {
            log.info(e.getMessage());
            transaction.rollback();
            return null;

        } finally {
            entityManager.close();
            log.info("entityManager already closed!");
        }
    }

    @Override
    public List<OrderEntity> findByPaymentMethod(PaymentMethod paymentMethod) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPaymentMethod'");
    }

}
