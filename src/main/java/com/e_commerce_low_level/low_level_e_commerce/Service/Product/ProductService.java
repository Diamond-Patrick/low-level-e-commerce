package com.e_commerce_low_level.low_level_e_commerce.Service.Product;

import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;

public interface ProductService {

    boolean insert(ProductEntity productEntity);

    boolean remove(String idProduct);

    boolean update(String id, ProductEntity productEntity);

    List<ProductEntity> findAll();

    List<ProductEntity> findByName(String name);

    boolean insertOmset(String kodeProduct, String idSeller);

    boolean deleteOmset(String kodeProduct, String idSeller);

}
