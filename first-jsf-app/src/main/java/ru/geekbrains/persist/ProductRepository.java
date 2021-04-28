package ru.geekbrains.persist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createNamedQuery("findAll", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) entityManager.persist(product);
        entityManager.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteById").setParameter("id", id).executeUpdate();
    }

    public Long countAll() {
        return entityManager.createNamedQuery("countAllProducts", Long.class).getSingleResult();
    }
}
