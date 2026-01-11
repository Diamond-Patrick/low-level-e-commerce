package com.e_commerce_low_level.low_level_e_commerce.Repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entities.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entities.OrderEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entities.PaymentMethod;
import com.e_commerce_low_level.low_level_e_commerce.Entities.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepoImpl;

public class OrderRepoTest {

    private OrderRepo orderRepo = new OrderRepoImpl();

    @Test
    void testInsert() {

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer("1cb9");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("tr300");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderQuantities(35);
        orderEntity.setPaymentMethod(PaymentMethod.BANK);

        boolean insert = orderRepo.insert(orderEntity, productEntity, customerEntity);

        Assertions.assertTrue(insert);
    }

    @Test
    void testRemoveSuccess() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(2);

        boolean remove = orderRepo.remove(orderEntity);

        Assertions.assertTrue(remove);
    }

    @Test
    void testRemoveFail() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(2);

        boolean remove = orderRepo.remove(orderEntity);

        Assertions.assertFalse(remove);
    }

    @Test
    void testFIndAll() {
        List<OrderEntity> all = orderRepo.findAll();

        Assertions.assertNotNull(all);

        for (OrderEntity orderEntity : all) {
            System.out.println(orderEntity.getIdCustomer().getIdCustomer());
            System.out.println(orderEntity.getKodeProduct().getName());
        }
    }

    @Test
    void testFIndByPaymentMethod() {
        List<OrderEntity> byPaymentMethod = orderRepo
                .findByPaymentMethod(PaymentMethod.BANK);

        Assertions.assertNotNull(byPaymentMethod);

        for (OrderEntity orderEntity : byPaymentMethod) {
            System.out.println(orderEntity.getIdCustomer().getName());
            System.out.println(orderEntity.getKodeProduct().getName());
        }
    }
}