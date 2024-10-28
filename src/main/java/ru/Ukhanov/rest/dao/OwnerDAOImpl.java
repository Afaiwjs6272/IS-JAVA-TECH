package ru.Ukhanov.rest.dao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.Ukhanov.rest.model.owner.Owner;


import java.util.Collection;
import java.util.Optional;

@Slf4j
    @Component
    public class OwnerDAOImpl implements OwnerDAO {
        @Override
        public void addOwner(Owner owner) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(owner);
            tx1.commit();
            session.close();
        }

        @Override
        public void deleteOwner(Long id) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(getOwnerById(id));
            tx1.commit();
            session.close();
        }

        @Override
        public Owner getOwnerById(Long id) {
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
        }

        @Override
        public void updateOwner(Owner owner) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(owner);
            tx1.commit();
            session.close();
        }

        @Override
        public Collection<Owner> getAll() {
            Collection<Owner> owners = (Collection<Owner>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Owner ").list();
            return owners;
        }

    @Override
    public Optional<Owner> findOwner(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Owner owner = null;

        try {
            owner = (Owner) session.createQuery("FROM Owner o WHERE o.ownerName = :name")
                    .setParameter("name", name)
                    .uniqueResult();
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) {
                tx1.rollback();
            }
            log.error("Error finding owner with name: {}", name, e);
        } finally {
            session.close();
        }

        return Optional.ofNullable(owner);
    }
}