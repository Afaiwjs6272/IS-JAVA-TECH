package ru.Ukhanov.rest.Service;

import ru.Ukhanov.rest.model.owner.Owner;

import java.util.Collection;

public interface OwnerService {
    void addOwner(Owner owner);

    void deleteOwner(Long id);

    Owner getOwnerById(Long id);

    void updateOwner(Owner owner);

    Collection<Owner> getAllOwners();
}
