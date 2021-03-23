package ru.koryaking.service;

import ru.koryaking.persist.Category;
import ru.koryaking.persist.Product;
import ru.koryaking.repository.CategoryRepository;
import ru.koryaking.repository.ProductRepository;
import ru.koryaking.rest.CategoryServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@WebService(endpointInterface = "ru.koryaking.service.CategoryServiceWs", serviceName = "CategoryService")
@Remote(CategoryServiceRemote.class)
public class CategoryServiceImpl implements CategoryService, CategoryServiceRest, CategoryServiceRemote {

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryRepr> findAll() { // преобразовали коллекцию категорий в коллекцию представлений
        return categoryRepository.findAll().stream().map(this::buildCategoryRepr).collect(Collectors.toList());
    }

    private CategoryRepr buildCategoryRepr(Category category) {
        CategoryRepr repr = new CategoryRepr();
        repr.setId(category.getId());
        repr.setName(category.getName());
        repr.setDescription(category.getDescription());
        return repr;
    }

    @Override
    public CategoryRepr findById(Long id) {
        Category category = categoryRepository.findById(id);
        if(category != null) {
            return buildCategoryRepr(category);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return categoryRepository.countAll();
    }

    @Override
    public void insert(CategoryRepr category) throws IllegalAccessException {
        if (category.getId() != null) {
            throw new IllegalAccessException();
        }
        saveOrUpdate(category);
    }

    @Override
    public void update(CategoryRepr category) throws IllegalAccessException {
        if (category.getId() == null) {
            throw new IllegalAccessException();
        }
        saveOrUpdate(category);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(CategoryRepr categoryRepr) {
        Category category = null;
        if (categoryRepr.getId() != null) {
            category = categoryRepository.getReference(categoryRepr.getId());
        }
        categoryRepository.saveOrUpdate(new Category(categoryRepr));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
