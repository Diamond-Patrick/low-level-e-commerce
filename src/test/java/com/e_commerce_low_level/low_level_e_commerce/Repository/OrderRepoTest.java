package com.e_commerce_low_level.low_level_e_commerce.Repository;

import java.util.List;

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
    void testPurchase() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer("af70");

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("Af123");

        boolean purchase = orderRepo.purchase(customerEntity, productEntity, PaymentMethod.EWALLET);

        Assertions.assertTrue(purchase);
    }

    @Test
    void testPaymentMethodSort() {
        List<OrderEntity> paymentMethodSort = orderRepo.paymentMethodSort(PaymentMethod.EWALLET);

        Assertions.assertNotNull(paymentMethodSort);

        for (OrderEntity orderEntity : paymentMethodSort) {
            System.out.println(orderEntity.getIdCustomer().getName());
            System.out.println(orderEntity.getKodeProduct().getName());
            System.out.println(orderEntity.getKodeProduct().getHarga());
        }
    }

    @Test
    void testPaymentMethodSortFail() {
        List<OrderEntity> paymentMethodSort = orderRepo.paymentMethodSort(PaymentMethod.BANK);

        Assertions.assertNull(paymentMethodSort);
        ;
    }

}
