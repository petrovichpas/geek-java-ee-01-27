package ru.geekbrains.persist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public List<Category> findAll() {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) entityManager.persist(category);
        entityManager.merge(category);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createQuery("DELETE FROM Category c WHERE c.id = :id").setParameter("id", id).executeUpdate();
    }

    public Category getReference(Long id) {
        return entityManager.getReference(Category.class, id);
    }

    public Long countAll() {
        return entityManager.createQuery("SELECT COUNT(c) FROM Category c", Long.class).getSingleResult();
    }
}
