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
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRepoImpl implements OrderRepo {

    @Override
    public boolean purchase(CustomerEntity customer, ProductEntity productEntity, OrderEntity orderEntity) {

        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            OrderEntity order = new OrderEntity();
            order.setIdCustomer(customer);
            order.setKodeProduct(productEntity);
            order.setPaymentMethod(orderEntity.getPaymentMethod());
            order.setPurchaceDate(LocalDate.now());
            order.setOrderQuantities(orderEntity.getOrderQuantities());

            entityManager.persist(order);

            ProductEntity kodeProduct = entityManager
                    .find(ProductEntity.class, productEntity.getKodeProduct());

            if (kodeProduct != null) {
                // 50 - 4
                kodeProduct.setStock(kodeProduct.getStock() - orderEntity.getOrderQuantities());
                entityManager.merge(kodeProduct);
            }

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
    public List<OrderEntity> paymentMethodSort(PaymentMethod paymentMethod) {

        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<OrderEntity> query = criteriaBuilder
                    .createQuery(OrderEntity.class);
            Root<OrderEntity> oE = query.from(OrderEntity.class);
            query.select(oE);

            // SELECT * FROM orders;

            Predicate equal = criteriaBuilder.equal(oE.get("paymentMethod"), paymentMethod);
            // WHERE paymentMethod = ?;

            query.where(equal);

            TypedQuery<OrderEntity> typedQuery = entityManager.createQuery(query);
            List<OrderEntity> resultList = typedQuery.getResultList();

            transaction.commit();
            return resultList.isEmpty() ? null : resultList;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
            return null;

        } finally {
            entityManager.close();
            log.info("entityManager already closed !");
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

            CriteriaQuery<OrderEntity> query = criteriaBuilder
                    .createQuery(OrderEntity.class);
            Root<OrderEntity> oE = query.from(OrderEntity.class);
            query.select(oE);

            TypedQuery<OrderEntity> typedQuery = entityManager.createQuery(query);
            List<OrderEntity> resultList = typedQuery.getResultList();

            transaction.commit();
            return resultList;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
            return null;

        } finally {
            entityManager.close();
            log.info("entityManager already closed !");
        }
    }

    @Override
    public Double countingOmset() {
        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Double> query = criteriaBuilder
                    .createQuery(Double.class);
            Root<OrderEntity> oE = query.from(OrderEntity.class);

            Join<OrderEntity, ProductEntity> join = oE.join("kodeProduct");

            // mendapatkan data jumlah barang yang dibeli
            // mendapatkan data harga barang persatu product
            // jumlah barang yg dibeli * harga persatu product
            Expression<Double> prod = criteriaBuilder
                    .prod(oE.get("orderQuantities"), join.get("harga"));

            // product a = 10 * 13
            // product b = 15 * 4
            // select sum() from orders;
            query.select(criteriaBuilder.sum(prod));

            TypedQuery<Double> typedQuery = entityManager.createQuery(query);
            Double singleResult = typedQuery.getSingleResult();

            transaction.commit();

            return singleResult;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
            return 0.0;

        } finally {
            entityManager.close();
            log.info("entityManager already closed !");
        }
    }

    @Override
    public boolean remove(OrderEntity orderEntity) {

        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            OrderEntity order = entityManager
                    .find(OrderEntity.class, orderEntity.getId());

            entityManager.remove(order);

            transaction.commit();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;

        } finally {
            entityManager.close();
            log.info("entiManager already closed!");
        }
    }

}
