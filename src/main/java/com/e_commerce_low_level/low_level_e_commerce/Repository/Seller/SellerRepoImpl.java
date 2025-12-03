package com.e_commerce_low_level.low_level_e_commerce.Repository.Seller;

import java.util.UUID;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
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
        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            SellerEntity idSeller = entityManager
                    .find(SellerEntity.class, id);

            if (idSeller != null) {

                if (sellerEntity.getOwnerName() != null)
                    idSeller.setOwnerName(sellerEntity.getOwnerName());

                if (sellerEntity.getEmail() != null)
                    idSeller.setEmail(sellerEntity.getEmail());

                if (sellerEntity.getPassword() != null)
                    idSeller.setPassword(sellerEntity.getPassword());

                if (sellerEntity.getShopName() != null)
                    idSeller.setShopName(sellerEntity.getShopName());

                // update address data
                Address idSellerAdd = idSeller.getAddress();
                Address sellerAddEntity = sellerEntity.getAddress();

                if (sellerAddEntity.getNoRumah() != null)
                    idSellerAdd.setNoRumah(sellerAddEntity.getNoRumah());

                if (sellerAddEntity.getNamaJalan() != null)
                    idSellerAdd.setNamaJalan(sellerAddEntity.getNamaJalan());

                if (sellerAddEntity.getKelurahan() != null)
                    idSellerAdd.setKelurahan(sellerAddEntity.getKelurahan());

                if (sellerAddEntity.getKota() != null)
                    idSellerAdd.setKota(sellerAddEntity.getKota());

                if (sellerAddEntity.getProvinsi() != null)
                    idSellerAdd.setProvinsi(sellerAddEntity.getProvinsi());

                entityManager.merge(idSeller);
            }

            transaction.commit();

            return idSeller != null ? true : false;

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
    public SellerEntity find(SellerEntity sellerEntity) {
        EntityManager entityManager = UtilityEntityManagerFactory
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            SellerEntity idSeller = entityManager
                    .find(SellerEntity.class, sellerEntity.getId());

            transaction.commit();

            return idSeller;

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
