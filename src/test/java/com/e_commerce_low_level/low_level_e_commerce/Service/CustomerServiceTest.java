package com.e_commerce_low_level.low_level_e_commerce.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Customer.CustomerServcieImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Customer.CustomerService;

public class CustomerServiceTest {

    private CustomerRepo customerRepo = new CustomerRepoImpl();
    private CustomerService customerService = new CustomerServcieImpl(customerRepo);

    @Test
    void testInsertSuccess() {

        Address address = new Address();
        address.setNoRumah("G70");
        address.setNamaJalan("Jalan A.Yani");
        address.setKelurahan("Kelurah S");
        address.setKota("Pekanbaru");
        address.setProvinsi("Riau");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("syakir");
        customerEntity.setEmail("syakir@gmail.com");
        customerEntity.setPassword("secret");
        customerEntity.setAddress(address);

        boolean insert = customerService.insert(customerEntity);

        Assertions.assertTrue(insert);
    }

    @Test
    void testInsertFail() {

        Address address = new Address();
        address.setNoRumah("G70");
        address.setNamaJalan("Jalan A.Yani");
        address.setKelurahan("Kelurah S");
        address.setKota("   ");
        address.setProvinsi("Riau");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("");
        customerEntity.setEmail("syakir@gmail.com");
        customerEntity.setPassword("secret");
        customerEntity.setAddress(address);

        boolean insert = customerService.insert(customerEntity);

        Assertions.assertFalse(insert);
    }
}
