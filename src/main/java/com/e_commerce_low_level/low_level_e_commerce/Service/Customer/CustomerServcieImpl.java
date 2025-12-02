package com.e_commerce_low_level.low_level_e_commerce.Service.Customer;

import java.util.Set;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
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

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer(idCustmer);

        Set<ConstraintViolation<CustomerEntity>> validateProperty = validator
                .validateProperty(customerEntity, "idCustomer");

        if (validateProperty.isEmpty()) {
            boolean remove = customerRepo.remove(customerEntity);
            return remove;

        } else {
            return false;
        }
    }

    @Override
    public boolean update(String id, CustomerEntity customerEntity) {

        CustomerEntity customer = new CustomerEntity();
        customer.setIdCustomer(id);

        CustomerEntity result = customerRepo.find(customer);

        if (result != null) {

            Address customerAddress = result.getAddress();
            Address customerParam = customerEntity.getAddress();

            if (customerEntity.getName() != null)
                result.setName(customerEntity.getName());

            if (customerEntity.getEmail() != null)
                result.setEmail(customerEntity.getEmail());

            if (customerEntity.getPassword() != null)
                result.setPassword(customerEntity.getPassword());

            if (customerParam.getNoRumah() != null)
                customerAddress.setNoRumah(customerParam.getNoRumah());

            if (customerParam.getNamaJalan() != null)
                customerAddress.setNoRumah(customerParam.getNamaJalan());

            if (customerParam.getKelurahan() != null)
                customerAddress.setKelurahan(customerParam.getKelurahan());

            if (customerParam.getKota() != null)
                customerAddress.setKota(customerParam.getKota());

            if (customerParam.getProvinsi() != null)
                customerAddress.setProvinsi(customerParam.getProvinsi());

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<CustomerEntity>> validate = validator.validate(result);

            if (validate.isEmpty()) {
                boolean update = customerRepo.update(id, result);
                return update;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public CustomerEntity find(String idCustomer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

}
