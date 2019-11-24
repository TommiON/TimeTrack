package com.karucell.timetrack.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidationSupportTest {

    private Validator validatorMock;

    private ValidationSupport validationSupport;

    @Before
    public void setup() {
        validatorMock = mock(Validator.class);

        validationSupport = new ValidationSupport(validatorMock);
    }

    @Test
    public void validOrThrow_withValidObject_shouldReturnGivenObject() {
        // given
        Object validObject = new Object();
        //and
        Set<ConstraintViolation<Object>> violations = Collections.emptySet();
        // and
        when(validatorMock.validate(validObject)).thenReturn(violations);

        // when
        Object result = validationSupport.validOrThrow(validObject);

        // then
        assertSame(validObject, result);
    }

    @Test
    public void validOrThrow_withInvalidObject_shouldThrowException() {
        // given
        Object invalidObject = new Object();
        //and
        ConstraintViolation<Object> violation = mock(ConstraintViolation.class);
        Set<ConstraintViolation<Object>> violations = Collections.singleton(violation);
        // and
        when(validatorMock.validate(invalidObject)).thenReturn(violations);

        // when
        try {
            validationSupport.validOrThrow(invalidObject);
            fail("Should throw ConstraintViolationException");
        } catch (ConstraintViolationException e) {
            // then
            assertEquals(violations, e.getConstraintViolations());
        } catch (Exception e) {
            fail("Should throw ConstraintViolationException");
        }
    }

}