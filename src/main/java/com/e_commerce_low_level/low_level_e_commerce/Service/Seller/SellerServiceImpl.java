package com.e_commerce_low_level.low_level_e_commerce.Service.Seller;

import java.util.Set;

import com.e_commerce_low_level.low_level_e_commerce.Entities.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entities.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Interface.Login;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Seller.SellerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityValidator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private SellerRepo sellerRepo;

    @Override
    public boolean insert(SellerEntity sellerEntity) {

        Validator validator = UtilityValidator.getValidator();

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

        Validator validator = UtilityValidator.getValidator();

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId(idSeller);

        Set<ConstraintViolation<SellerEntity>> validateProperty = validator
                .validateProperty(sellerEntity, "id");

        return validateProperty.isEmpty() ? sellerRepo.remove(sellerEntity) : false;
    }

    @Override
    public boolean update(String id, SellerEntity sellerEntity) {

        SellerEntity sellerId = find(id);

        if (sellerId != null) {

            Address sellerAddress = sellerId.getAddress();
            Address sellerParam = sellerEntity.getAddress();

            if (!sellerEntity.getOwnerName().trim().isBlank())
                sellerId.setOwnerName(sellerEntity.getOwnerName());

            if (!sellerEntity.getEmail().trim().isBlank())
                sellerId.setEmail(sellerEntity.getEmail());

            if (!sellerEntity.getPassword().trim().isBlank())
                sellerId.setPassword(sellerEntity.getPassword());

            if (!sellerEntity.getShopName().trim().isBlank())
                sellerId.setShopName(sellerEntity.getShopName());

            if (!sellerParam.getNoRumah().trim().isBlank())
                sellerAddress.setNoRumah(sellerParam.getNoRumah());

            if (!sellerParam.getNamaJalan().trim().isBlank())
                sellerAddress.setNamaJalan(sellerParam.getNamaJalan());

            if (!sellerParam.getKelurahan().trim().isBlank())
                sellerAddress.setKelurahan(sellerParam.getKelurahan());

            if (!sellerParam.getKota().trim().isBlank())
                sellerAddress.setKota(sellerParam.getKota());

            if (!sellerParam.getProvinsi().trim().isBlank())
                sellerAddress.setProvinsi(sellerParam.getProvinsi());

            Validator validator = UtilityValidator.getValidator();

            Set<ConstraintViolation<SellerEntity>> validate = validator.validate(sellerId);

            return validate.isEmpty() ? sellerRepo.update(id, sellerId) : false;

        } else {
            return false;
        }

    }

    @Override
    public SellerEntity find(String idSeller) {

        Validator validator = UtilityValidator.getValidator();

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId(idSeller);

        Set<ConstraintViolation<SellerEntity>> validateProperty = validator
                .validateProperty(sellerEntity, "id");

        return validateProperty.isEmpty() ? sellerRepo.find(sellerEntity) : null;
    }

    @Override
    public SellerEntity findByEmailAndPassword(String email, String password) {

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setEmail(email);
        sellerEntity.setPassword(password);

        Validator validator = UtilityValidator.getValidator();

        Set<ConstraintViolation<SellerEntity>> validate = validator
                .validate(sellerEntity, Login.class);

        return validate.isEmpty() ? sellerRepo.findByEmailPassword(sellerEntity) : null;
    }

}
