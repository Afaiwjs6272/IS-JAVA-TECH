package ru.Ukhanov.rest.dao;

import ru.Ukhanov.rest.model.owner.Owner;

import java.util.Collection;
import java.util.Optional;

public interface OwnerDAO {
    void addOwner(Owner owner);

    void deleteOwner(Long id);

    Owner getOwnerById(Long id);

    void updateOwner(Owner owner);

    Collection<Owner> getAll();

    Optional<Owner> findOwner(String ownerName);
}
