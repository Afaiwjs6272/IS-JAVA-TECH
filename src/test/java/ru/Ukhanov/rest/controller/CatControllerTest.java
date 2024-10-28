package ru.Ukhanov.rest.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Ukhanov.rest.model.cats.Cat;
import ru.Ukhanov.rest.model.cats.enums.Breed;
import ru.Ukhanov.rest.model.cats.enums.Color;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CatControllerTest {

    @Autowired
    private Validator validator;

    @Test
    public void shouldNotCreateCatWithoutName() {
        Cat cat = new Cat();
        cat.setBreed(Breed.SFINKS);
        cat.setColor(Color.BLACK);

        Set<ConstraintViolation<Cat>> violations = validator.validate(cat);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("catName"))
                .count());
    }

    @Test
    public void shouldCreateCatWithNoFailures() {
        Cat cat = new Cat();
        cat.setCatName("Sema");
        cat.setBirthday(LocalDate.of(2019,10,14));
        cat.setColor(Color.WHITE);
        cat.setBreed(Breed.SHORTHAIR);

        Set<ConstraintViolation<Cat>> violations = validator.validate(cat);
        assertTrue(violations.isEmpty());
    }
}