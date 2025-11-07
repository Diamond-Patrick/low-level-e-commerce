package com.e_commerce_low_level.low_level_e_commerce.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;

public class CustomerRepoTest {

    private CustomerRepo customerRepo = new CustomerRepoImpl();

    @Test
    void testInsert() {

        Address address = new Address();
        address.setNoRumah("no.23");
        address.setNamaJalan("jln semangka");
        address.setKelurahan("kelurahan a");
        address.setKota("Jakarta Selatan");
        address.setProvinsi("DKI Jakarta");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("Abu");
        customerEntity.setEmail("abu@gmail.com");
        customerEntity.setPassword("rahasia");
        customerEntity.setAddress(address);

        boolean insert = customerRepo.insert(customerEntity);

        Assertions.assertTrue(insert);
    }

    @Test
    void testRemove() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer("85e4");

        boolean remove = customerRepo.remove(customerEntity);

        Assertions.assertTrue(remove);
    }

    @Test
    void testRemoveFail() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer("hhhh");

        boolean remove = customerRepo.remove(customerEntity);

        Assertions.assertFalse(remove);
    }

    @Test
    void testUpdate() {

        Address address = new Address();
        address.setKelurahan("depok");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail("taufiq@gmail.com");
        customerEntity.setName("taufiq");
        customerEntity.setAddress(address);

        boolean update = customerRepo.update("7092", customerEntity);

        Assertions.assertTrue(update);
    }

    @Test
    void testUpdateFail() {

        Address address = new Address();
        address.setKelurahan("depok");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail("taufiq@gmail.com");
        customerEntity.setName("taufiq");
        customerEntity.setAddress(address);

        boolean update = customerRepo.update("7777", customerEntity);

        Assertions.assertFalse(update);
    }

}
