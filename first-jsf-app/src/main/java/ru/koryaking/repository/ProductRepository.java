package ru.koryaking.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.koryaking.persist.Product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;

import java.util.List;


@Named
@ApplicationScoped
public class ProductRepository {

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
                saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", new BigDecimal(100)));
                saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", new BigDecimal(200)));
                saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", new BigDecimal(200)));
                userTransaction.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                userTransaction.rollback();
            }
        }
    }

    public List<Product> findAll() {return entityManager.createNamedQuery("findAllProduct", Product.class).getResultList();}

    public Long countAll() {return entityManager.createNamedQuery("countAllProduct", Long.class).getSingleResult();}

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
       entityManager.createNamedQuery("deleteByIdProduct")
               .setParameter("id", id)
               .executeUpdate();
    }
}