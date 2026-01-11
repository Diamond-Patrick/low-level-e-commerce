package com.e_commerce_low_level.low_level_e_commerce.Repository.Seller;

import java.util.UUID;

import com.e_commerce_low_level.low_level_e_commerce.Entities.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entities.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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

    @Override
    public SellerEntity findByEmailPassword(SellerEntity sellerEntity) {
        EntityManager entityManager = UtilityEntityManagerFactory
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            // SELECT * FROM customer;
            CriteriaQuery<SellerEntity> query = criteriaBuilder
                    .createQuery(SellerEntity.class);
            Root<SellerEntity> se = query.from(SellerEntity.class);
            query.select(se);

            // WHERE email = ? And password == ?;
            Predicate emailEqual = criteriaBuilder
                    .equal(se.get("email"), sellerEntity.getEmail()); // email

            Predicate passwordEqual = criteriaBuilder
                    .equal(se.get("password"), sellerEntity.getPassword()); // password

            Predicate and = criteriaBuilder.and(emailEqual, passwordEqual); // AND

            query.where(and); // WHERE

            TypedQuery<SellerEntity> typedQuery = entityManager.createQuery(query);
            SellerEntity singleResult = typedQuery.getSingleResult();

            transaction.commit();

            return singleResult;

        } catch (Exception e) {
            log.error(e.getMessage());
            transaction.rollback();
            return null;

        } finally {
            entityManager.close();
            log.info("entityManager already closed !");
        }
    }

}
