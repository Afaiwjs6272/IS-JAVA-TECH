package ru.Ukhanov.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.*;
import ru.Ukhanov.rest.Service.OwnerServiceImpl;
import ru.Ukhanov.rest.model.owner.Owner;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerServiceImpl ownerService;

    @GetMapping
    public Collection<Owner> getAll() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    @PostFilter("hasAuthority('ADMIN')")
    public Owner getOwner(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping
    public String addOwner(@RequestBody Owner owner) {
        ownerService.addOwner(owner);
        return "owner is saved";
    }

    @PutMapping
    public void updateOwner(@RequestBody Owner owner) {
        ownerService.updateOwner(owner);
    }

    @PutMapping("{id}/cats/{cat_id}")
    public Owner addCatToOwner(@PathVariable Long id,@PathVariable Long cat_id) {
       return ownerService.addCatToOwner(id,cat_id);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }
}
