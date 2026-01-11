package com.e_commerce_low_level.low_level_e_commerce.Repository.Customer;

import com.e_commerce_low_level.low_level_e_commerce.Entities.CustomerEntity;

public interface CustomerRepo {

    boolean insert(CustomerEntity customerEntity);

    boolean remove(CustomerEntity customerEntity);

    boolean update(String id, CustomerEntity customerEntity);

    CustomerEntity find(CustomerEntity customerEntity); // find by email & password

    CustomerEntity findById(CustomerEntity customerEntity);
}
