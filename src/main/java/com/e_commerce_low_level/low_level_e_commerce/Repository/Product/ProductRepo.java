package com.e_commerce_low_level.low_level_e_commerce.Repository.Product;

import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;

public interface ProductRepo {

    boolean insert(ProductEntity productEntity);

    void remove(ProductEntity productEntity);

    void update(String id, ProductEntity productEntity);

    List<ProductEntity> findAll();

}
