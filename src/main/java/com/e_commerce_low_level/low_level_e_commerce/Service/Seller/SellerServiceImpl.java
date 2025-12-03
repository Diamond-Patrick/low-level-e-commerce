package com.e_commerce_low_level.low_level_e_commerce.Service.Seller;

import java.util.Set;

import com.e_commerce_low_level.low_level_e_commerce.Entity.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Seller.SellerRepo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private SellerRepo sellerRepo;

    @Override
    public boolean insert(SellerEntity sellerEntity) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        SellerEntity seller = new SellerEntity();
        seller.setOwnerName(sellerEntity.getOwnerName());
        seller.setEmail(sellerEntity.getEmail());
        seller.setPassword(sellerEntity.getPassword());
        seller.setShopName(sellerEntity.getShopName());
        seller.setAddress(sellerEntity.getAddress());

        Set<ConstraintViolation<SellerEntity>> validate = validator.validate(seller);

        return validate.isEmpty() ? sellerRepo.insert(seller) : false;
    }

    @Override
    public boolean remove(String idSeller) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId(idSeller);

        Set<ConstraintViolation<SellerEntity>> validateProperty = validator
                .validateProperty(sellerEntity, "id");

        return validateProperty.isEmpty() ? sellerRepo.remove(sellerEntity) : false;
    }

    @Override
    public boolean update(String id, SellerEntity sellerEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public SellerEntity find(String idSeller) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

}
