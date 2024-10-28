package ru.Ukhanov.rest.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Ukhanov.rest.model.owner.Owner;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @Autowired
    private Validator validator;

    @Test
    public void shouldNotCreateOwnerWithoutName() {
        Owner owner = new Owner();
        owner.setOwnerBirthday(LocalDate.now());
        owner.setPassword("12234");
        owner.setRoles("USER");

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("ownerName"))
                .count());
    }

    @Test
    public void shouldNotCreateOwnerWithoutPassword() {
        Owner owner = new Owner();
        owner.setOwnerName("Ivan");
        owner.setOwnerBirthday(LocalDate.now());
        owner.setRoles("USER");

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("password"))
                .count());
    }

    @Test
    public void shouldNotCreateOwnerWithoutRole() {
        Owner owner = new Owner();
        owner.setOwnerName("Ivan");
        owner.setOwnerBirthday(LocalDate.now());
        owner.setPassword("1234");

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("roles"))
                .count());
    }

    @Test
    public void shouldCreateOwnerWithNoFailures() {
        Owner owner = new Owner();
        owner.setOwnerName("Ivan");
        owner.setOwnerBirthday(LocalDate.now());
        owner.setPassword("1234");
        owner.setRoles("USER");

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner);
        assertTrue(violations.isEmpty());
    }
}