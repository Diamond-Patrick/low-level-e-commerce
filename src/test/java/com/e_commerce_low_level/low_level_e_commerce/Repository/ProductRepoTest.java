package com.e_commerce_low_level.low_level_e_commerce.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        product.setKodeProduct("tr300");
        product.setName("Mouse");
        product.setHarga(12.80);
        product.setStock(50);
        product.setDescription("ini mouse gaming");
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
        productEntity.setName("Monitor");
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
    void testFindAll() {
        List<ProductEntity> all = productRepo.findAll();

        Assertions.assertNotNull(all);

        for (ProductEntity productEntity : all) {
            System.out.println(productEntity.getName());
        }
    }
}
