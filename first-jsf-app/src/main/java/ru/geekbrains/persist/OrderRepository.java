package ru.geekbrains.persist;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Named
@ApplicationScoped
public class OrderRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Transactional
    public void saveOrUpdate(Order order) {
        if (order.getId() == null) entityManager.persist(order);
        entityManager.merge(order);
    }
}
