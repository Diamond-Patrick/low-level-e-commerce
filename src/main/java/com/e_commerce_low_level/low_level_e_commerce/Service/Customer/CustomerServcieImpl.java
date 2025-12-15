package com.e_commerce_low_level.low_level_e_commerce.Service.Customer;

import java.util.Set;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Customer.CustomerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityValidator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CustomerServcieImpl implements CustomerService {

    private CustomerRepo customerRepo;

    @Override
    public boolean insert(CustomerEntity customerEntity) {

        Validator validator = UtilityValidator.getValidator();

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

            for (ConstraintViolation<CustomerEntity> constraintViolation : validate) {

                log.error(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
            }

            return false;
        }
    }

    @Override
    public boolean remove(String idCustmer) {

        Validator validator = UtilityValidator.getValidator();

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
        CustomerEntity result = find(id);

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

            Validator validator = UtilityValidator.getValidator();

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

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setIdCustomer(idCustomer);

        Validator validator = UtilityValidator.getValidator();

        Set<ConstraintViolation<CustomerEntity>> validateProperty = validator
                .validateProperty(customerEntity, "idCustomer");

        if (validateProperty.isEmpty()) {
            CustomerEntity find = customerRepo.find(customerEntity);
            return find;

        } else {
            return null;
        }

    }

}
