package com.e_commerce_low_level.low_level_e_commerce.Service.Seller;

import com.e_commerce_low_level.low_level_e_commerce.Entity.SellerEntity;

public interface SellerService {

    boolean insert(SellerEntity sellerEntity);

    boolean remove(String idSeller);

    boolean update(String id, SellerEntity sellerEntity);

    SellerEntity find(String idSeller);

}
