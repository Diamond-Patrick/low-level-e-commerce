package com.e_commerce_low_level.low_level_e_commerce.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepoImpl;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductService;
import com.e_commerce_low_level.low_level_e_commerce.Service.Product.ProductServiceImpl;

public class ProductServiceTest {

    private ProductRepo productRepo = new ProductRepoImpl();
    private ProductService productService = new ProductServiceImpl(productRepo);

    @Test
    void testInsertSuccess() throws IOException {

        Path of = Path.of("src/main/resources/Image/image.jpg");
        byte[] allBytes = Files.readAllBytes(of);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("AS234");
        productEntity.setName("bean coffee");
        productEntity.setHarga(12.30);
        productEntity.setStock(75);
        productEntity.setGambar(allBytes);
        productEntity.setDescription("Ini biji-bijian kopi dari Indonesia");

        boolean insert = productService.insert(productEntity);

        Assertions.assertTrue(insert);
    }

    @Test
    void testInsertFail() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setKodeProduct("AS234");
        productEntity.setName("bean coffee");
        productEntity.setHarga(12.30);
        productEntity.setStock(75);
        productEntity.setGambar(null);
        productEntity.setDescription("  ");

        boolean insert = productService.insert(productEntity);

        Assertions.assertFalse(insert);

    }

    @Test
    void testRemoveSuccess() {
        boolean remove = productService.remove("AS234");

        Assertions.assertTrue(remove);
    }

    @Test
    void testRemoveBlankKode() {
        Assertions.assertFalse(productService.remove("   "));
    }

    @Test
    void testRemoveFail() {
        boolean remove = productService.remove("AS234");

        Assertions.assertFalse(remove);
    }

    @Test
    void testFindAll() {
        List<ProductEntity> all = productService.findAll();

        Assertions.assertNotNull(all);

        for (ProductEntity productEntity : all) {
            System.out.println(productEntity.getName());
            System.out.println(productEntity.getHarga());
            System.out.println(productEntity.getDescription());
            System.out.println(productEntity.getGambar());
        }
    }

    @Test
    void testUpadateSuccess() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription("ini mouse gaming murah");
        productEntity.setHarga(24.00);

        Assertions.assertTrue(productService.update("Af123", productEntity));
    }

    @Test
    void testUpadateFail() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription("    ");
        productEntity.setHarga(14.00);

        Assertions.assertFalse(productService.update("Af123", productEntity));
    }

    @Test
    void testFIndByNameSuccess() {
        List<ProductEntity> byName = productService.findByName("Mouse");

        Assertions.assertNotNull(byName);

        for (ProductEntity productEntity : byName) {
            System.out.println(productEntity.getName());
            System.out.println(productEntity.getHarga());
            System.out.println(productEntity.getStock());
        }
    }

    @Test
    void testFIndByNameNotFound() {
        List<ProductEntity> byName = productService.findByName("MousePad");

        Assertions.assertTrue(byName.isEmpty());

    }

    @Test
    void testFIndByNameNameBlank() {
        List<ProductEntity> byName = productService.findByName("  ");
        Assertions.assertNull(byName);

    }

    @Test
    void testInsertOmsetSuccess() {
        boolean insertOmset = productService
                .insertOmset("JK125", "38a84");

        Assertions.assertTrue(insertOmset);
    }

    @Test
    void testInsertOmsetFail1() {
        boolean insertOmset = productService
                .insertOmset("hishdo", "38a84");

        Assertions.assertFalse(insertOmset);
    }

    @Test
    void testInsertOmsetFail2() {
        boolean insertOmset = productService
                .insertOmset("JK125", "jhsxjhjsxk");

        Assertions.assertFalse(insertOmset);
    }

    @Test
    void testInsertOmsetBlankKodeProduct() {
        boolean insertOmset = productService
                .insertOmset("   ", "jhsxjhjsxk");

        Assertions.assertFalse(insertOmset);
    }

    @Test
    void testInsertOmsetBlankIdSeller() {
        boolean insertOmset = productService
                .insertOmset("JK125", "   ");

        Assertions.assertFalse(insertOmset);
    }

    @Test
    void testInsertOmsetBlankBoth() {
        boolean insertOmset = productService
                .insertOmset("", "   ");

        Assertions.assertFalse(insertOmset);
    }
}
