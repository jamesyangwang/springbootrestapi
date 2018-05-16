package com.example.demo.validator;

import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.annotation.Alpha;

public class AlphaValidator extends GenericStringValidator<Alpha> {
    @Override
    public Function<String, Boolean> condition() {
        return StringUtils::isAlpha;
    }
}
