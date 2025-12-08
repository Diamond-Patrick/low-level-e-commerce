package com.e_commerce_low_level.low_level_e_commerce.Service.Order;

import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Entity.OrderEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.PaymentMethod;

public interface OrderService {

    boolean insert(OrderEntity order, String kodeProduct, String idCustomer);

    boolean remove(int idOrder);

    List<OrderEntity> findAll();

    List<OrderEntity> findByPaymentMethod(PaymentMethod paymentMethod);

}
