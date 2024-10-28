package ru.Ukhanov.rest.dao;

import ru.Ukhanov.rest.model.cats.Cat;

import java.util.Collection;

public interface CatDAO {
    void addCat(Cat cat);

    void removeCat(Long id);

    Cat getCatById(Long id);

    void updateCat(Cat cat);

    Collection<Cat> getAll();
}
