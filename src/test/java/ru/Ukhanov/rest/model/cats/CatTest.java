package ru.Ukhanov.rest.model.cats;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Ukhanov.rest.model.cats.enums.Breed;
import ru.Ukhanov.rest.model.cats.enums.Color;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatTest {

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
    public void shouldFailValidationOnBlankCat() {
        Cat cat = new Cat();
        Set<ConstraintViolation<Cat>> violations = validator.validate(cat);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldNotCreateCatWithoutName() {
        Cat cat = new Cat();
        cat.setCat_id(1L);
        cat.setBreed(Breed.SFINKS);
        cat.setColor(Color.BLACK);

        Set<ConstraintViolation<Cat>> violations = validator.validate(cat);
        assertFalse(violations.isEmpty());

        assertEquals(1, violations.stream().filter(c -> c.getPropertyPath().toString().equals("catName")).count());
    }

    @Test
    public void shouldCreateCatWithNoMistakes() {
        Cat cat = new Cat();
        cat.setCat_id(1L);
        cat.setCatName("Sema");
        cat.setBreed(Breed.SFINKS);
        cat.setColor(Color.BLACK);

        Set<ConstraintViolation<Cat>> violations = validator.validate(cat);
        assertTrue(violations.isEmpty());
    }
}