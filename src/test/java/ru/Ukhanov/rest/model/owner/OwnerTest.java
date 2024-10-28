package ru.Ukhanov.rest.model.owner;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OwnerTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void beforeAll() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        validatorFactory.close();
    }

    @Test
    public void shouldFailValidationOnEmptyOwner() {
        Owner owner = new Owner();
        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldNotCreateOwnerWithoutName() {
        Owner owner = new Owner();
        owner.setOwner_id(1L);
        owner.setOwnerBirthday(LocalDate.now());
        owner.setPassword("xxxxxx");
        owner.setRoles("ADMIN");

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertFalse(violations.isEmpty());

        assertEquals(1, violations.stream().filter(c -> c.getPropertyPath().toString().equals("ownerName")).count());
    }

    @Test
    public void shouldNotCreateOwnerWithoutPassword() {
        Owner owner = new Owner();
        owner.setOwner_id(2L);
        owner.setOwnerName("Oleg");
        owner.setOwnerBirthday(LocalDate.now());
        owner.setRoles("ADMIN");

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertFalse(violations.isEmpty());

        assertEquals(1, violations.stream().filter(c -> c.getPropertyPath().toString().equals("password")).count());
    }

    @Test
    public void shouldNotCreateOwnerWithoutRole() {
        Owner owner = new Owner();
        owner.setOwner_id(2L);
        owner.setOwnerBirthday(LocalDate.now());
        owner.setPassword("xxxxxx");

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertFalse(violations.isEmpty());

        assertEquals(1, violations.stream().filter(c -> c.getPropertyPath().toString().equals("roles")).count());
    }

    @Test
    public void shouldCreateOwnerWithNoMistakes() {
        Owner owner = new Owner();
        owner.setOwner_id(2L);
        owner.setOwnerName("lev");
        owner.setOwnerBirthday(LocalDate.now());
        owner.setPassword("xxxxxx");
        owner.setRoles("USER");

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertTrue(violations.isEmpty());
    }
}