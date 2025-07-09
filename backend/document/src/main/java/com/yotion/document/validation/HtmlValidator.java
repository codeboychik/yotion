package com.yotion.document.validation;

/**
 * File is created by andreychernenko at 09.07.2025
 */
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

public class HtmlValidator implements ConstraintValidator<Html, String> {

    @Override
    public void initialize(Html constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Jsoup.parse(value, "", Parser.xmlParser());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
