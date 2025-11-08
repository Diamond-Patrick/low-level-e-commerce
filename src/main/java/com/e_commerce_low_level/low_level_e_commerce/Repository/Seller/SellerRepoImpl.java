package com.e_commerce_low_level.low_level_e_commerce.Repository.Seller;

import java.util.UUID;

import com.e_commerce_low_level.low_level_e_commerce.Entity.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SellerRepoImpl implements SellerRepo {

    @Override
    public boolean insert(SellerEntity sellerEntity) {

        EntityManager entityManager = UtilityEntityManagerFactory
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            SellerEntity seller = new SellerEntity();
            seller.setId(UUID.randomUUID().toString().substring(0, 5));
            seller.setOwnerName(sellerEntity.getOwnerName());
            seller.setShopName(sellerEntity.getShopName());
            seller.setEmail(sellerEntity.getEmail());
            seller.setPassword(sellerEntity.getPassword());
            seller.setAddress(sellerEntity.getAddress());

            entityManager.persist(seller);

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
    public boolean remove(SellerEntity sellerEntity) {
        EntityManager entityManager = UtilityEntityManagerFactory
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            SellerEntity idSeller = entityManager
                    .find(SellerEntity.class, sellerEntity.getId());

            entityManager.remove(idSeller);

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
    public boolean update(String id, SellerEntity sellerEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public SellerEntity find(SellerEntity sellerEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

}
