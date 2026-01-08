package com.e_commerce_low_level.low_level_e_commerce.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.SellerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepoImpl;

public class ProductRepoTest {

    private ProductRepo productRepo = new ProductRepoImpl();

    @Test
    void testInsert() throws IOException {

        Path path = Path.of("src/main/resources/Image/image.jpg");
        byte[] allBytes = Files.readAllBytes(path);

        ProductEntity product = new ProductEntity();
        product.setKodeProduct("JK122");
        product.setName("Laptop Asus");
        product.setHarga(56.00);
        product.setStock(45);
        product.setDescription("ini Laptop");
        product.setGambar(allBytes);

        boolean insert = productRepo.insert(product);

        Assertions.assertTrue(insert);
    }

    @Test
    void testRemove() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("er765");

        boolean remove = productRepo.remove(productEntity);

        Assertions.assertTrue(remove);
    }

    @Test
    void testRemoveFail() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("tdr30");

        boolean remove = productRepo.remove(productEntity);

        Assertions.assertFalse(remove);
    }

    @Test
    void testUpdateSuccess() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Monitor xiaomi");
        productEntity.setStock(35);

        boolean update = productRepo.update("tr300", productEntity);

        Assertions.assertTrue(update);
    }

    @Test
    void testUpdateFail() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Monitor");
        productEntity.setStock(35);

        boolean update = productRepo.update("tr30", productEntity);

        Assertions.assertFalse(update);
    }

    @Test
    void testUpdateStock() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setStock(10);

        boolean update = productRepo.update("tr300", productEntity);

        Assertions.assertTrue(update);
    }

    @Test
    void testFindAll() {
        List<ProductEntity> all = productRepo.findAll();

        Assertions.assertNotNull(all);

        for (ProductEntity productEntity : all) {
            System.out.println(productEntity.getName());
        }
    }

    @Test
    void testFindByNameSuccess() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Mouse");

        List<ProductEntity> byName = productRepo.findByName(productEntity);

        Assertions.assertNotNull(byName);

        for (ProductEntity productEntity2 : byName) {
            System.out.println(productEntity2.getHarga());
            System.out.println(productEntity2.getDescription());
        }

    }

    @Test
    void testInsertOmsetSuccess() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("JK124");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId("8ca3b");

        boolean insertOmset = productRepo.insertOmset(productEntity, sellerEntity);

        Assertions.assertTrue(insertOmset);
    }

    @Test
    void testInsertOmsetFail() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("11111");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId("8ca3b");

        boolean insertOmset = productRepo
                .insertOmset(productEntity, sellerEntity);

        Assertions.assertFalse(insertOmset);
    }

    @Test
    void testInsertOmsetFail2() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("AS002");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId("yyyy");

        boolean insertOmset = productRepo
                .insertOmset(productEntity, sellerEntity);

        Assertions.assertFalse(insertOmset);
    }

    @Test
    void testRemoveOmsetSuccess() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("JK124");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId("8ca3b");

        boolean deleteOmset = productRepo.deleteOmset(productEntity, sellerEntity);

        Assertions.assertTrue(deleteOmset);
    }

    @Test
    void testRemoveOmsetFail() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("J4");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId("8ca3b");

        boolean deleteOmset = productRepo.deleteOmset(productEntity, sellerEntity);

        Assertions.assertFalse(deleteOmset);
    }

    @Test
    void testRemoveOmsetFail2() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("JK124");

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setId("jkllj");

        boolean deleteOmset = productRepo.deleteOmset(productEntity, sellerEntity);

        Assertions.assertFalse(deleteOmset);
    }
}
