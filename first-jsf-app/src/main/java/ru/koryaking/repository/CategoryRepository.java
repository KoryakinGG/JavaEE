package ru.koryaking.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.koryaking.persist.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public List<Category> findAll() {return entityManager.createNamedQuery("findAllCategory", Category.class).getResultList();}

    public Category getReference(Long id) {return entityManager.getReference(Category.class, id);}

    public Long countAll() {return entityManager.createNamedQuery("countAllCategory", Long.class).getSingleResult();}

    public Category findById(Long id) {return entityManager.find(Category.class, id);}

    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
        }
        entityManager.merge(category);
    }

    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteByIdCategory")
                .setParameter("id", id)
                .executeUpdate();
    }
}