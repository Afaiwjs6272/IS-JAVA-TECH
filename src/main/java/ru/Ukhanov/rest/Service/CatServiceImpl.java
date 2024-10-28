package ru.Ukhanov.rest.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.Ukhanov.rest.exception.NotFoundException;
import ru.Ukhanov.rest.model.cats.Cat;
import ru.Ukhanov.rest.dao.CatDAO;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatServiceImpl implements CatService {

    private final CatDAO catDAO;

    @Override
    public void addCat(Cat cat) {
        checkValidCat(cat);
        catDAO.addCat(cat);
    }

    @Override
    public void removeCat(Long id) {
        catDAO.removeCat(id);
    }

    @Override
    public Cat getCatById(Long id) {
        if (catDAO.getCatById(id) == null) {
            throw new NotFoundException("Кот не найден");
        }
        return catDAO.getCatById(id);
    }

    @Override
    public void updateCat(Cat cat) {
        checkValidCat(cat);
        catDAO.updateCat(cat);
    }

    @Override
    public Collection<Cat> getAllCats() {
        return catDAO.getAll();
    }

    public Cat addCatToFriend(Cat cat1, Cat cat2) {
        checkValidCat(cat1);
        checkValidCat(cat2);

        cat1.getFriendList().add(cat2);
        cat2.getFriendList().add(cat1);
        log.info("cat1 = {} add cat2 = {} to friend",cat1.getCat_id(),cat2.getCat_id());
        return cat1;
    }

    private void checkValidCat(Cat cat) {
        if (cat == null) {
            throw new NotFoundException("Кот не может быть пустым");
        }
    }
}