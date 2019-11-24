package com.karucell.timetrack.support;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class ValidationSupport {

    private Validator validator;

    public ValidationSupport(Validator validator) {
        this.validator = validator;
    }

    /**
     * Support method for validation, can be mapped into stream or optional
     * @param object object to validate
     * @param <T> type of object
     * @return valid object
     */
    public <T> T validOrThrow(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return object;
    }
}
