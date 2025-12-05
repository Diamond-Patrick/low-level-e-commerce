package com.e_commerce_low_level.low_level_e_commerce.Repository.Product;

import java.util.List;

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
public class ProductRepoImpl implements ProductRepo {

    @Override
    public boolean insert(ProductEntity productEntity) {
        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            entityManager.persist(productEntity);

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
    public boolean remove(ProductEntity productEntity) {
        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            ProductEntity kodeProduct = entityManager
                    .find(ProductEntity.class, productEntity.getKodeProduct());
            entityManager.remove(kodeProduct);

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
    public boolean update(String id, ProductEntity productEntity) {
        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            ProductEntity kodeProduct = entityManager
                    .find(ProductEntity.class, id);

            if (kodeProduct != null) {

                if (productEntity.getName() != null)
                    kodeProduct.setName(productEntity.getName());

                if (productEntity.getHarga() != null)
                    kodeProduct.setHarga(productEntity.getHarga());

                if (productEntity.getStock() != null)
                    kodeProduct.setStock(productEntity.getStock() + kodeProduct.getStock()); // stock lama + stock baru

                if (productEntity.getDescription() != null)
                    kodeProduct.setDescription(productEntity.getDescription());

                if (productEntity.getGambar() != null)
                    kodeProduct.setGambar(productEntity.getGambar());

                entityManager.merge(kodeProduct);
            }

            transaction.commit();
            return kodeProduct != null ? true : false;

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
    public List<ProductEntity> findAll() {
        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            // SELECT * FROM product;
            CriteriaQuery<ProductEntity> query = criteriaBuilder
                    .createQuery(ProductEntity.class);
            Root<ProductEntity> p = query.from(ProductEntity.class);
            query.select(p);

            TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(query);
            List<ProductEntity> resultList = typedQuery.getResultList();

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

}
