package com.e_commerce_low_level.low_level_e_commerce.Repository;

import java.util.UUID;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
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
    public boolean remove(CustomerEntity customerEntity) {

        EntityManager entityManager = UtilityEntityManagerFactory
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CustomerEntity idCustomer = entityManager.find(CustomerEntity.class, customerEntity.getIdCustomer());

            entityManager.remove(idCustomer);

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
    public boolean update(String id, CustomerEntity customerEntity) {
        EntityManager entityManager = UtilityEntityManagerFactory.getEntityManagerFactory()
                .createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CustomerEntity idCustomer = entityManager
                    .find(CustomerEntity.class, id);

            if (idCustomer != null) {

                if (customerEntity.getName() != null)
                    idCustomer.setName(customerEntity.getName());

                if (customerEntity.getEmail() != null)
                    idCustomer.setEmail(customerEntity.getEmail());

                if (customerEntity.getPassword() != null)
                    idCustomer.setPassword(customerEntity.getPassword());

                // update address data
                Address idCustAdd = idCustomer.getAddress();
                Address custEntityAdd = customerEntity.getAddress();

                if (custEntityAdd.getNoRumah() != null)
                    idCustAdd.setNoRumah(custEntityAdd.getNoRumah());

                if (custEntityAdd.getNamaJalan() != null)
                    idCustAdd.setNamaJalan(custEntityAdd.getNamaJalan());

                if (custEntityAdd.getKelurahan() != null)
                    idCustAdd.setKelurahan(custEntityAdd.getKelurahan());

                if (custEntityAdd.getKota() != null)
                    idCustAdd.setKota(custEntityAdd.getKota());

                if (custEntityAdd.getProvinsi() != null)
                    idCustAdd.setProvinsi(custEntityAdd.getProvinsi());

                entityManager.merge(idCustomer);
            }

            transaction.commit();

            return idCustomer != null ? true : false;

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
    public CustomerEntity find(CustomerEntity customerEntity) {
        EntityManager entityManager = UtilityEntityManagerFactory
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            CustomerEntity idCustomer = entityManager
                    .find(CustomerEntity.class, customerEntity.getIdCustomer());

            transaction.commit();

            return idCustomer;

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