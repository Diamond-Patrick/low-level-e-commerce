package com.e_commerce_low_level.low_level_e_commerce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.e_commerce_low_level.low_level_e_commerce.Utilities.UtilityEntityManagerFactory;

import jakarta.persistence.EntityManagerFactory;

public class PersistenceTest {

    @Test
    void testPersistence() {

        EntityManagerFactory entityManagerFactory = UtilityEntityManagerFactory.getEntityManagerFactory();

        Assertions.assertNotNull(entityManagerFactory);
    }
}
