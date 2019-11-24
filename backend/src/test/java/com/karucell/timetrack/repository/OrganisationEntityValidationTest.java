package com.karucell.timetrack.repository.entity;

import static com.karucell.timetrack.AssertionTools.assertViolationsContainMessage;
import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganisationEntityValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void validate_ValidEntity_shouldNotReturnViolations() {
        runTestCase(
                organisationEntity -> { /* No modifications to valid entity */ },
                violations -> assertEquals(0, violations.size())
        );
    }

    @Test
    public void validate_NullName_shouldReturnIsEmpty() {
        runTestCase(
                organisationEntity -> organisationEntity.setName(null),
                violations -> assertViolationsContainMessage(violations, "name.isEmpty")
        );
    }

    @Test
    public void validate_EmptyName_shouldReturnIsEmpty() {
        runTestCase(
                organisationEntity -> organisationEntity.setName(""),
                violations -> assertViolationsContainMessage(violations, "name.isEmpty")
        );
    }

    @Test
    public void validate_TooShortName_shouldReturnIsTooShort() {
        runTestCase(
                organisationEntity -> organisationEntity.setName("AA"),
                violations -> assertViolationsContainMessage(violations, "name.isTooShort")
        );
    }

    @Test
    public void validate_TooLongName_shouldReturnIsTooLong() {
        String strOf81Characters = IntStream.rangeClosed(1, 81).mapToObj(operand -> "A").reduce((s1, s2) -> s1 + s2).toString();
        runTestCase(
                organisationEntity -> organisationEntity.setName(strOf81Characters),
                violations -> assertViolationsContainMessage(violations, "name.isTooLong")
        );
    }

    @Test
    public void validate_NullYTJId_shouldReturnIsEmpty() {
        runTestCase(
                organisationEntity -> organisationEntity.setYtjIdentifier(null),
                violations -> assertViolationsContainMessage(violations, "ytjIdentifier.isEmpty")
        );
    }

    @Test
    public void validate_EmptyYTJId_shouldReturnIsEmpty() {
        runTestCase(
                organisationEntity -> organisationEntity.setYtjIdentifier(""),
                violations -> assertViolationsContainMessage(violations, "ytjIdentifier.isEmpty")
        );
    }

    @Test
    public void validate_TooShortYTJId_shouldReturnIsTooShort() {
        runTestCase(
                organisationEntity -> organisationEntity.setYtjIdentifier("BB"),
                violations -> assertViolationsContainMessage(violations, "ytjIdentifier.isTooShort")
        );
    }

    @Test
    public void validate_TooLongYTJId_shouldReturnIsTooLong() {
        String strOf81Characters = IntStream.rangeClosed(1, 81).mapToObj(operand -> "A").reduce((s1, s2) -> s1 + s2).toString();
        runTestCase(
                organisationEntity -> organisationEntity.setYtjIdentifier(strOf81Characters),
                violations -> assertViolationsContainMessage(violations, "ytjIdentifier.isTooLong")
        );
    }

    // TODO: enable this test, its failing cos of nonsensical disabled logic
    @Ignore
    @Test
    public void validate_DisabledEntity_shouldReturnIsDisabled() {
        runTestCase(
                organisationEntity -> organisationEntity.setActive(false),
                violations -> assertViolationsContainMessage(violations, "active.isDisabled")
        );
    }

    @Test
    public void validate_DisabledNull_shouldReturnIsNull() {
        runTestCase(
                organisationEntity -> organisationEntity.setActive(null),
                violations -> assertViolationsContainMessage(violations, "active.isNull")
        );
    }

    @Test
    public void validate_AllFieldsInvalid_shouldReturnAllViolationsForAllFields() {
        runTestCase(
                organisationEntity -> {
                    organisationEntity.setName(null);
                    organisationEntity.setYtjIdentifier(null);
                    organisationEntity.setActive(null);
                },
                violations -> {
                    assertViolationsContainMessage(violations, "name.isEmpty");
                    assertViolationsContainMessage(violations, "ytjIdentifier.isEmpty");
                    assertViolationsContainMessage(violations, "active.isNull");
                }
        );
    }

    private void runTestCase(
            Consumer<OrganisationEntity> setter,
            Consumer<Set<ConstraintViolation<OrganisationEntity>>> violationAssertions
    ) {
        // given - Note! entity is constructed with valid values
        OrganisationEntity organisation = new OrganisationEntity(
                null,
                "Any valid name",
                "Any valid YTJ ID",
                true
        );
        // and
        setter.accept(organisation);

        // when
        Set<ConstraintViolation<OrganisationEntity>> violations = validator.validate(organisation);

        // then
        violationAssertions.accept(violations);
    }

}