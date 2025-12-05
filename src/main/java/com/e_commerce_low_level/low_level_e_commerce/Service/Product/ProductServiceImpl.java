package com.e_commerce_low_level.low_level_e_commerce.Service.Product;

import java.util.List;
import java.util.Set;

import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityValidator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    @Override
    public boolean insert(ProductEntity productEntity) {

        Validator validator = UtilityValidator.getValidator();

        Set<ConstraintViolation<ProductEntity>> validate = validator.validate(productEntity);

        return validate.isEmpty() ? productRepo.insert(productEntity) : false;

    }

    @Override
    public boolean remove(String idProduct) {

        Validator validator = UtilityValidator.getValidator();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct(idProduct);

        Set<ConstraintViolation<ProductEntity>> validateProperty = validator
                .validateProperty(productEntity, "kodeProduct");

        return validateProperty.isEmpty() ? productRepo.remove(productEntity) : false;

    }

    @Override
    public boolean update(String id, ProductEntity productEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<ProductEntity> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
