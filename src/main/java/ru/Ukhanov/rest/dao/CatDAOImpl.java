package ru.Ukhanov.rest.dao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.Ukhanov.rest.model.cats.Cat;


import java.util.Collection;

@Slf4j
@Component
public class CatDAOImpl implements CatDAO {
    @Override
    public void addCat(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void removeCat(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(getCatById(id));
        tx1.commit();
        session.close();
    }

    @Override
    public Cat getCatById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cat.class, id);
    }

    @Override
    public void updateCat(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public Collection<Cat> getAll() {
        Collection<Cat> cats = (Collection<Cat>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Cat").list();
        return cats;
    }
}