package com.e_commerce_low_level.low_level_e_commerce.Repository.Order;

import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.OrderEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.PaymentMethod;
import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;

public interface OrderRepo {

    boolean purchase(CustomerEntity customer, ProductEntity productEntity, PaymentMethod paymentMethod);

    List<OrderEntity> paymentMethodSort(PaymentMethod paymentMethod);

}
