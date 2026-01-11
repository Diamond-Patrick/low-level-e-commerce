package com.e_commerce_low_level.low_level_e_commerce.Service.Customer;

import com.e_commerce_low_level.low_level_e_commerce.Entities.CustomerEntity;

public interface CustomerService {

    boolean insert(CustomerEntity customerEntity);

    boolean remove(String idCustmer);

    boolean update(String id, CustomerEntity customerEntity);

    CustomerEntity find(String idCustomer);

    CustomerEntity findByEmailAndPassword(String email, String password);
}
