package com.e_commerce_low_level.low_level_e_commerce.Service.Order;

import java.util.List;
import java.util.Set;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.OrderEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.PaymentMethod;
import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Interface.Essential;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Order.OrderRepo;
import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityValidator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepo orderRepo;

    @Override
    public boolean insert(OrderEntity order, String kodeProduct, String idCustomer) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct(kodeProduct);

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer(idCustomer);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderQuantities(order.getOrderQuantities());
        orderEntity.setPaymentMethod(order.getPaymentMethod());

        Validator validator = UtilityValidator.getValidator();

        Set<ConstraintViolation<ProductEntity>> productValid = validator
                .validateProperty(productEntity, "kodeProduct", Essential.class);

        Set<ConstraintViolation<CustomerEntity>> customerValid = validator
                .validateProperty(customerEntity, "idCustomer");

        Set<ConstraintViolation<OrderEntity>> orderValid = validator.validate(orderEntity);

        if (productValid.isEmpty() && customerValid.isEmpty() && orderValid.isEmpty()) {
            return orderRepo.insert(orderEntity, productEntity, customerEntity);
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(String idOrder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<OrderEntity> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<OrderEntity> findByPaymentMethod(PaymentMethod paymentMethod) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPaymentMethod'");
    }

}
