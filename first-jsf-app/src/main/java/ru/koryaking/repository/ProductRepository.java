package ru.koryaking.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.koryaking.persist.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public List<Product> findAll() {return entityManager.createNamedQuery("findAllProduct", Product.class).getResultList();}

    public Long countAll() {return entityManager.createNamedQuery("countAllProduct", Long.class).getSingleResult();}

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }


    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }


    public void deleteById(Long id) {
       entityManager.createNamedQuery("deleteByIdProduct")
               .setParameter("id", id)
               .executeUpdate();
    }
}