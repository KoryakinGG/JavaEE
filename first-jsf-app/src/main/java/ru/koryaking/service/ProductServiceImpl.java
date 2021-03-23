package ru.koryaking.service;

import ru.koryaking.persist.Category;
import ru.koryaking.persist.Product;
import ru.koryaking.repository.CategoryRepository;
import ru.koryaking.repository.ProductRepository;
import ru.koryaking.rest.ProductServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@WebService(endpointInterface = "ru.koryaking.service.ProductServiceWs", serviceName = "ProductService")
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepr> findAll() { // преобразовали коллекцию продуктов в коллекцию представлений
        return productRepository.findAll().stream().map(this::buildProductRepr).collect(Collectors.toList());
    }

    private ProductRepr buildProductRepr(Product product) {
        ProductRepr repr = new ProductRepr();

        repr.setId(product.getId());
        repr.setName(product.getName());
        repr.setDescription(product.getDescription());
        repr.setPrice(product.getPrice());
        Category category = product.getCategory();
        repr.setCategoryId(category != null ? category.getId() : null);
        repr.setCategoryName(category != null ? category.getName() : null);

        return repr;
    }

    @Override
    public ProductRepr findById(Long id) {
        Product product = productRepository.findById(id);
        if(product != null) {
            return buildProductRepr(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void insert(ProductRepr product) throws IllegalAccessException {
        if (product.getId() != null) {
            throw new IllegalAccessException();
        }
        saveOrUpdate(product);
    }

    @Override
    public void update(ProductRepr product) throws IllegalAccessException {
        if (product.getId() == null) {
            throw new IllegalAccessException();
        }
        saveOrUpdate(product);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductRepr productRepr) {
        Category category = null;
        if (productRepr.getCategoryId() != null) {
            category = categoryRepository.getReference(productRepr.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(productRepr, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
