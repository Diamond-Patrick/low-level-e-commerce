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
        address.setNamaJalan("Jalan AH Nasution");
        address.setKelurahan("Kelurah B");
        address.setKota("Bandung");
        address.setProvinsi("Jawa Barat");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("Khairy");
        customerEntity.setEmail("khairy@gmail.com");
        customerEntity.setPassword("rahasia");
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
        address.setKota("Malang");
        address.setProvinsi("Riau");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("");
        customerEntity.setEmail("syakir@gmail.com");
        customerEntity.setPassword("    ");
        customerEntity.setAddress(address);

        boolean insert = customerService.insert(customerEntity);

        Assertions.assertFalse(insert);
    }

    @Test
    void testRemoveSuccess() {
        boolean remove = customerService.remove("88c0");

        Assertions.assertTrue(remove);
    }

    @Test
    void testRemoveFail() {
        boolean remove = customerService.remove("99c9");
        Assertions.assertFalse(remove);
    }

    @Test
    void testRemoveIdCustomerBlank() {
        boolean remove = customerService.remove("     ");
        Assertions.assertFalse(remove);
    }

    @Test
    void testUpdateSuccess() {

        Address address = new Address();
        address.setNoRumah("B.56");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setPassword("very secret");
        customerEntity.setAddress(address);

        boolean update = customerService.update("1cb9", customerEntity);

        Assertions.assertTrue(update);
    }

    @Test
    void testUpdateFail() {

        Address address = new Address();
        address.setKelurahan("Kelurahan J");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("adam");
        customerEntity.setEmail("  ");
        customerEntity.setAddress(address);

        boolean update = customerService.update("7092", customerEntity);

        Assertions.assertFalse(update);
    }

    @Test
    void testFindSuccess() {

        CustomerEntity byEmailAndPassword = customerService
                .findByEmailAndPassword("hello@gmail.com", "hello_password");

        Assertions.assertNotNull(byEmailAndPassword);
    }

    @Test
    void testFindFail() {
        CustomerEntity byEmailAndPassword = customerService
                .findByEmailAndPassword("xhhsuhxusx", "hello_password");

        Assertions.assertNull(byEmailAndPassword);
    }

    @Test
    void testFindBlankPassword() {
        CustomerEntity byEmailAndPassword = customerService
                .findByEmailAndPassword("hello@gmail.com", "  ");

        Assertions.assertNull(byEmailAndPassword);
    }
}
