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

    @Test
    void testUpdateSuccess() {

        Address address = new Address();
        address.setProvinsi("Daerah Istimewa Yogyakarta");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setOwnerName("Man");
        sellerEntity.setShopName("Putra Mart");
        sellerEntity.setEmail("man@gmail.com");
        sellerEntity.setPassword("man's password");
        sellerEntity.setAddress(address);

        boolean update = sellerService.update("8ca3b", sellerEntity);
        Assertions.assertTrue(update);
    }

    @Test
    void testUpdateFail() {
        Address address = new Address();
        address.setProvinsi("  ");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setOwnerName("Man");
        sellerEntity.setShopName("Putra Mart");
        sellerEntity.setEmail("man.com");
        sellerEntity.setPassword("man's password");
        sellerEntity.setAddress(address);

        boolean update = sellerService.update("8ca3b", sellerEntity);
        Assertions.assertFalse(update);
    }

    @Test
    void testUpdateIdNotFound() {
        Address address = new Address();
        address.setProvinsi("  ");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setOwnerName("Man");
        sellerEntity.setShopName("Putra Mart");
        sellerEntity.setEmail("man.com");
        sellerEntity.setPassword("man's password");
        sellerEntity.setAddress(address);

        boolean update = sellerService.update("3333", sellerEntity);
        Assertions.assertFalse(update);
    }

    @Test
    void testFindByEmailAndPasswordSuccess() {

        SellerEntity byEmailAndPassword = sellerService
                .findByEmailAndPassword("rasya@gmail.com", "secret");

        Assertions.assertNotNull(byEmailAndPassword);
    }

    @Test
    void testFindByEmailAndPasswordFail() {

        SellerEntity byEmailAndPassword = sellerService
                .findByEmailAndPassword("fkfk@gmail.com", "secret");

        Assertions.assertNull(byEmailAndPassword);
    }

    @Test
    void testFindByEmailAndPasswordEmailFail() {

        SellerEntity byEmailAndPassword = sellerService
                .findByEmailAndPassword("rasya.com", "secret");

        Assertions.assertNull(byEmailAndPassword);
    }

    @Test
    void testFindByEmailAndPasswordPasswordBlank() {

        SellerEntity byEmailAndPassword = sellerService
                .findByEmailAndPassword("rasya@gmail.com", "    ");

        Assertions.assertNull(byEmailAndPassword);
    }

    @Test
    void testFindByEmailAndPasswordEmailBlank() {

        SellerEntity byEmailAndPassword = sellerService
                .findByEmailAndPassword("  ", "secret");

        Assertions.assertNull(byEmailAndPassword);
    }

    @Test
    void testFindByEmailAndPasswordBlank() {

        SellerEntity byEmailAndPassword = sellerService
                .findByEmailAndPassword("  ", "  ");

        Assertions.assertNull(byEmailAndPassword);
    }
}
