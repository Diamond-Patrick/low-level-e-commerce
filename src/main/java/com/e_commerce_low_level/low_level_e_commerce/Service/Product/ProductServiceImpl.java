package com.e_commerce_low_level.low_level_e_commerce.Service.Product;

import java.util.List;
import java.util.Set;

import com.e_commerce_low_level.low_level_e_commerce.Entities.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entities.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Interface.Essential;
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

        ProductEntity product = new ProductEntity();

        if (!productEntity.getName().trim().isBlank())
            product.setName(productEntity.getName());

        if (productEntity.getHarga() != null)
            product.setHarga(productEntity.getHarga());

        if (productEntity.getGambar() != null)
            product.setGambar(productEntity.getGambar());

        if (productEntity.getStock() != null)
            product.setStock(productEntity.getStock());

        if (!productEntity.getDescription().trim().isBlank())
            product.setDescription(productEntity.getDescription());

        return productRepo.update(id, product);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepo.findAll();

    }

    @Override
    public List<ProductEntity> findByName(String name) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);

        Validator validator = UtilityValidator.getValidator();
        Set<ConstraintViolation<ProductEntity>> validateProperty = validator
                .validateProperty(productEntity, "name", Essential.class);

        return validateProperty.isEmpty() ? productRepo.findByName(productEntity) : null;
    }

    @Override
    public boolean insertOmset(String kodeProduct, String idSeller) {

        if (kodeProduct.trim().isBlank() || idSeller.trim().isBlank()) {

            return false;
        } else {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setKodeProduct(kodeProduct);

            SellerEntity sellerEntity = new SellerEntity();
            sellerEntity.setId(idSeller);

            return productRepo.insertOmset(productEntity, sellerEntity);
        }

    }

    @Override
    public boolean deleteOmset(String kodeProduct, String idSeller) {

        if (kodeProduct.trim().isBlank() || idSeller.trim().isBlank()) {
            return false;
        } else {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setKodeProduct(kodeProduct);

            SellerEntity sellerEntity = new SellerEntity();
            sellerEntity.setId(idSeller);

            return productRepo.deleteOmset(productEntity, sellerEntity);
        }
    }

}
