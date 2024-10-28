package ru.Ukhanov.rest.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.Ukhanov.rest.exception.NotFoundException;
import ru.Ukhanov.rest.model.cats.Cat;
import ru.Ukhanov.rest.model.owner.Owner;
import ru.Ukhanov.rest.dao.CatDAO;
import ru.Ukhanov.rest.dao.OwnerDAO;

import java.util.Collection;

@Service
@Slf4j
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDAO ownerDAO;
    private final CatDAO catDAO;
    private PasswordEncoder passwordEncoder;

    @Override
    public void addOwner(Owner owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        ownerDAO.addOwner(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        ownerDAO.deleteOwner(id);
    }

    @Override
    public Owner getOwnerById(Long id) {
        if (ownerDAO.getOwnerById(id) == null) {
            throw new NotFoundException("Нет такого хозяина");
        } else {
            return ownerDAO.getOwnerById(id);
        }
    }

    @Override
    public void updateOwner(Owner owner) {
        checkValidOwner(owner);
        ownerDAO.updateOwner(owner);
    }

    @Override
    public Collection<Owner> getAllOwners() {
        return ownerDAO.getAll();
    }

    public Owner addCatToOwner(Long owner_id, Long cat_id) {
        Owner owner = ownerDAO.getOwnerById(owner_id);
        Cat cat = catDAO.getCatById(cat_id);
        checkValidOwner(owner);
        checkValidCat(cat);

        owner.getCats().add(cat);
        log.info("owner = {} now have a new cat = {}",owner_id,cat_id);
        return owner;
    }

    private void checkValidOwner(Owner owner) {
        if (owner == null) {
            throw new NotFoundException("Хозяин не может быть пустым");
        }
    }

    private void checkValidCat(Cat cat) {
        if (cat == null) {
            throw new NotFoundException("Кот не может быть пустым");
        }
    }
}