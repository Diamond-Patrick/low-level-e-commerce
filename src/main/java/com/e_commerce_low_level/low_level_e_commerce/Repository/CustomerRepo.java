package com.e_commerce_low_level.low_level_e_commerce.Repository;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;

public interface CustomerRepo {

    boolean insert(CustomerEntity customerEntity);

    void remove(CustomerEntity customerEntity);

    void update(String id, CustomerEntity customerEntity);

    CustomerEntity find(CustomerEntity customerEntity);
}
