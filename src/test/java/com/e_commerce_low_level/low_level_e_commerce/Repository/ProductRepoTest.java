package com.e_commerce_low_level.low_level_e_commerce.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Entity.CustomerEntity;
import com.e_commerce_low_level.low_level_e_commerce.Entity.ProductEntity;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepo;
import com.e_commerce_low_level.low_level_e_commerce.Repository.Product.ProductRepoImpl;

public class ProductRepoTest {

    private ProductRepo productRepo = new ProductRepoImpl();

    @Test
    void testInsert() throws IOException {

        Path path = Path.of("src/main/resources/Image/image.jpg");
        byte[] allBytes = Files.readAllBytes(path);

        ProductEntity product = new ProductEntity();
        product.setKodeProduct("Af123");
        product.setName("Mouse");
        product.setHarga(12.80);
        product.setStock(50);
        product.setDescription("ini mouse gaming");
        product.setGambar(allBytes);

        boolean insert = productRepo.insert(product);

        Assertions.assertTrue(insert);
    }
}
