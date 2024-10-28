package ru.Ukhanov.rest.Service;

import ru.Ukhanov.rest.model.cats.Cat;

import java.util.Collection;

public interface CatService {
    void addCat(Cat cat);

    void removeCat(Long id);

    Cat getCatById(Long id);

    void updateCat(Cat cat);

    Collection<Cat> getAllCats();
}
