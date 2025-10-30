package com.e_commerce_low_level.low_level_e_commerce.Utilities;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UtilityEntityManagerFactory {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {

        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("low-level-ecommerce");
            return entityManagerFactory;
        }
        return entityManagerFactory;
    }

}
