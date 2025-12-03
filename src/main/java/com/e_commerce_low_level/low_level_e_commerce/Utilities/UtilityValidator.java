package com.e_commerce_low_level.low_level_e_commerce.Utilities;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UtilityValidator {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    public static Validator getValidator() {
        return validator;
    }
}
