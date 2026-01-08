package com.e_commerce_low_level.low_level_e_commerce.Repository.Product;

import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.SellerEntity;

public interface ProductRepo {

    boolean insert(ProductEntity productEntity);

    boolean remove(ProductEntity productEntity);

    boolean update(String id, ProductEntity productEntity);

    List<ProductEntity> findAll();

    List<ProductEntity> findByName(ProductEntity productEntity);

    boolean insertOmset(ProductEntity kodeProduct, SellerEntity idSeller);

    boolean deleteOmset(ProductEntity kodeProduct, SellerEntity idSeller);

}
