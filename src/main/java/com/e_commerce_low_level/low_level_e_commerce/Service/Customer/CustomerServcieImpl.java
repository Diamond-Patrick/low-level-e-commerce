package com.e_commerce_low_level.low_level_e_commerce.Service.Customer;

import java.util.Set;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerServcieImpl implements CustomerService {

    private CustomerRepo customerRepo;

    @Override
    public boolean insert(CustomerEntity customerEntity) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        CustomerEntity customer = new CustomerEntity();
        customer.setName(customerEntity.getName());
        customer.setEmail(customerEntity.getEmail());
        customer.setPassword(customerEntity.getPassword());
        customer.setAddress(customerEntity.getAddress());

        Set<ConstraintViolation<CustomerEntity>> validate = validator.validate(customer);

        if (validate.isEmpty()) {
            boolean insert = customerRepo.insert(customer);
            return insert;

        } else {
            return false;
        }

    }

    @Override
    public boolean remove(String idCustmer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean update(String id, CustomerEntity customerEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CustomerEntity find(String idCustomer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

}
