package com.e_commerce_low_level.low_level_e_commerce.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.Address;
import com.e_commerce_low_level.low_level_e_commerce.Entity.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Seller.SellerRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Seller.SellerRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Seller.SellerService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Seller.SellerServiceImpl;

public class SellerServiceTest {

    private SellerRepo sellerRepo = new SellerRepoImpl();
    private SellerService sellerService = new SellerServiceImpl(sellerRepo);

    @Test
    void testInsertSuccess() {

        Address address = new Address();
        address.setNoRumah("J67");
        address.setNamaJalan("Jalan Timun");
        address.setKelurahan("Kelurahan C");
        address.setKota("Yogyakarta");
        address.setProvinsi("DIY");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setOwnerName("Luqman");
        sellerEntity.setEmail("luqman@gmail.com");
        sellerEntity.setPassword("secret luqman");
        sellerEntity.setShopName("qwerty shop");
        sellerEntity.setAddress(address);

        boolean insert = sellerService.insert(sellerEntity);

        Assertions.assertTrue(insert);

    }

    @Test
    void testInsertFail() {

        Address address = new Address();
        address.setNoRumah("J67");
        address.setNamaJalan("Jalan Timun");
        address.setKelurahan("Kelurahan C");
        address.setKota("   ");
        address.setProvinsi("DIY");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setOwnerName("Luqman");
        sellerEntity.setEmail("luqman@gmail.com");
        sellerEntity.setPassword("secret luqman");
        sellerEntity.setShopName(" ");
        sellerEntity.setAddress(address);

        boolean insert = sellerService.insert(sellerEntity);

        Assertions.assertFalse(insert);
    }

    @Test
    void testRemoveSuccess() {
        Assertions.assertTrue(sellerService.remove("40b62"));
    }

    @Test
    void testRemoveBlankId() {
        Assertions.assertFalse(sellerService.remove("  "));
    }

    @Test
    void testRemoveFail() {
        Assertions.assertFalse(sellerService.remove("40b62"));
    }

    @Test
    void testFindSuccess() {
        SellerEntity sellerEntity = sellerService.find("38a84");

        Assertions.assertNotNull(sellerEntity);
        Assertions.assertEquals("rasya@gmail.com", sellerEntity.getEmail());
    }

    @Test
    void testFindBlankId() {
        SellerEntity sellerEntity = sellerService.find("  ");

        Assertions.assertNull(sellerEntity);
    }

    @Test
    void testFindFail() {
        SellerEntity sellerEntity = sellerService.find("7777");

        Assertions.assertNull(sellerEntity);
    }
}
