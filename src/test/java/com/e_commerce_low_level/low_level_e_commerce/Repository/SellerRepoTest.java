package com.e_commerce_low_level.low_level_e_commerce.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entity.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Seller.SellerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Seller.SellerRepoImpl;

public class SellerRepoTest {

    private SellerRepo sellerRepo = new SellerRepoImpl();

    @Test
    void testInsert() {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setOwnerName("Rasya");
        sellerEntity.setShopName("Maju Mart");
        sellerEntity.setEmail("rasya@gmail.com");
        sellerEntity.setPassword("secret");
        sellerEntity.setAddress(new Address("no.13", "jln. Anggur", "Kelurahan S", "Bandung", "Jawa Barat"));

        boolean insert = sellerRepo.insert(sellerEntity);
        Assertions.assertTrue(insert);
    }

    @Test
    void testRemoveSuccess() {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId("5da14");

        boolean remove = sellerRepo.remove(sellerEntity);

        Assertions.assertTrue(remove);
    }

    @Test
    void testRemoveFail() {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId("ddddd");

        boolean remove = sellerRepo.remove(sellerEntity);

        Assertions.assertFalse(remove);
    }
}
