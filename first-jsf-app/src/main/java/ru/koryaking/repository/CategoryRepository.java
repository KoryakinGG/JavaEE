package ru.koryaking.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.koryaking.persist.Category;
import ru.koryaking.persist.Product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Named
@ApplicationScoped
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                userTransaction.begin();
                saveOrUpdate(new Category(null, "Catalog  1",
                        "Description of catalog 1"));
                saveOrUpdate(new Category(null, "Catalog  2",
                        "Description of catalog 2"));
                saveOrUpdate(new Category(null, "Catalog  3",
                        "Description of catalog 3"));
                userTransaction.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                userTransaction.rollback();
            }
        }
    }

    public List<Category> findAll() {return entityManager.createNamedQuery("findAllCategory", Category.class).getResultList();}

    public Long countAll() {return entityManager.createNamedQuery("countAllCategory", Long.class).getSingleResult();}

    public Category findById(Long id) {return entityManager.find(Category.class, id);}

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
        }
        entityManager.merge(category);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("deleteByIdCategory")
                .setParameter("id", id)
                .executeUpdate();
    }
}