package com.e_commerce_low_level.low_level_e_commerce.Repository.Seller;

import com.e_commerce_low_level.low_level_e_commerce.Entity.SellerEntity;

public interface SellerRepo {

    boolean insert(SellerEntity sellerEntity);

    boolean remove(SellerEntity sellerEntity);

    boolean update(String id, SellerEntity sellerEntity);

    SellerEntity find(SellerEntity sellerEntity);
}
