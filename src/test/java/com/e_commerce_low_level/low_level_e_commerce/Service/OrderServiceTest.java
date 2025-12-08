package com.e_commerce_low_level.low_level_e_commerce.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.OrderEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.PaymentMethod;
import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Order.OrderService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Order.OrderServiceImpl;

public class OrderServiceTest {

    private OrderRepo orderRepo = new OrderRepoImpl();
    private OrderService orderService = new OrderServiceImpl(orderRepo);

    @Test
    void testInsertSuccess() {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderQuantities(55);
        orderEntity.setPaymentMethod(PaymentMethod.EWALLET);

        boolean insert = orderService
                .insert(orderEntity, "tr300", "7092");

        Assertions.assertTrue(insert);
    }

    @Test
    void testInsertFail() {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderQuantities(55);
        orderEntity.setPaymentMethod(null);

        boolean insert = orderService
                .insert(orderEntity, "tr3", "7092");

        Assertions.assertFalse(insert);
    }
}
