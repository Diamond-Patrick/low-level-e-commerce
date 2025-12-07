package com.e_commerce_low_level.low_level_e_commerce.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.OrderEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.PaymentMethod;
import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepoImpl;

public class OrderRepoTest {

    private OrderRepo orderRepo = new OrderRepoImpl();

    @Test
    void testInsert() {

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer("af70");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("Af123");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderQuantities(35);
        orderEntity.setPaymentMethod(PaymentMethod.BANK);

        boolean insert = orderRepo.insert(orderEntity, productEntity, customerEntity);

        Assertions.assertTrue(insert);

    }
}