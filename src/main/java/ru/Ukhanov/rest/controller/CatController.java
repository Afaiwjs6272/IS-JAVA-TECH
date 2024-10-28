package ru.Ukhanov.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.*;
import ru.Ukhanov.rest.Service.CatServiceImpl;
import ru.Ukhanov.rest.model.cats.Cat;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatController {
    private final CatServiceImpl catService;

    @GetMapping
    public Collection<Cat> getAll() {
        return catService.getAllCats();
    }

    @GetMapping("/{id}")
    @PostFilter("hasAuthority('ADMIN') or authentication.name == filterObject.ownerId")
    public Cat getCat(@PathVariable Long id) {
        return catService.getCatById(id);
    }

    @PostMapping
    public void addCat(@RequestBody Cat cat) {
        catService.addCat(cat);
    }

    @PutMapping
    public void updateCat(@RequestBody Cat cat) {
        catService.updateCat(cat);
    }

    @PutMapping("{id}/friends/{cat_id}")
    public Cat addCatToFriend(@PathVariable Cat id,@PathVariable Cat cat_id) {
        return catService.addCatToFriend(id,cat_id);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id) {
        catService.removeCat(id);
    }

    @PostMapping("/new-user")
    public void addUser() {

    }
}