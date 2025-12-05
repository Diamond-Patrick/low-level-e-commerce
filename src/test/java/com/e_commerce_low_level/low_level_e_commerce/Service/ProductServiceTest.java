package com.e_commerce_low_level.low_level_e_commerce.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
}
