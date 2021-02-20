package com.springboot.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Integer> {

    private Integer min;
    private Integer max;

    @Override
    public void initialize(Age age) {
        this.min = age.min();
        this.max = age.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value < max && min < value;
    }
}
