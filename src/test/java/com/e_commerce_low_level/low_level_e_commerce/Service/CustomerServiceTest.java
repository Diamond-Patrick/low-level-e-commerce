package com.e_commerce_low_level.low_level_e_commerce.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Customer.CustomerService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Customer.CustomerServiceImpl;

public class CustomerServiceTest {

    private CustomerRepo customerRepo = new CustomerRepoImpl();
    private CustomerService customerService = new CustomerServiceImpl(customerRepo);

    @Test
    void testInsert() {

        Address address = new Address("A.09", "Jalan Manggis", "Kelurahan 1",
                "Solo", "Jawa Tengah");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("Taufiq");
        customerEntity.setEmail("taufiq@gmail.com");
        customerEntity.setPassword("rahasia");
        customerEntity.setAddress(address);

        boolean insert = customerService.insert(customerEntity);

        Assertions.assertTrue(insert);
    }

    @Test
    void testInsertFail() {

        Address address = new Address("", "Jalan Manggis", "Kelurahan 1",
                "Solo", "Jawa Tengah");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("Taufiq");
        customerEntity.setEmail("taufiq@gmail.com");
        customerEntity.setPassword("rahasia");
        customerEntity.setAddress(address);

        boolean insert = customerService.insert(customerEntity);

        Assertions.assertFalse(insert);
    }
}
