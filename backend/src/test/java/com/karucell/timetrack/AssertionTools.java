package com.karucell.timetrack;

import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class AssertionTools {

    /**
     * Assert that violations contain violation with given message.
     *
     * @param violations violations to assert
     * @param message message to search in violations
     */
    public static void assertViolationsContainMessage(Set<? extends ConstraintViolation<?>> violations, String message) {
        Optional<?> result
                = violations.stream()
                          .filter(constraintViolation -> constraintViolation.getMessage().equals(message))
                          .findFirst();
        assertTrue(result.isPresent());
    }

}
