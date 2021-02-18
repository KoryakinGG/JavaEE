package ru.koryaking.listner;

import ru.koryaking.category.Category;
import ru.koryaking.persist.Product;
import ru.koryaking.repository.CategoryRepository;
import ru.koryaking.repository.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    /**
     * В этом классе создаем репозиторий и сохраняем его в Аттрибуте, чтобы в любом месте где используется сервлет контекст
     * можно было использовать репозиторий
     * @param sce - This is the event class for notifications about changes to the servlet context of a web application.
     */

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));

        sce.getServletContext().setAttribute("productRepository", productRepository);


        categoryRepository.saveOrUpdate(new Category(null, "Category 1", "Description of category 1"));
        categoryRepository.saveOrUpdate(new Category(null, "Category 2", "Description of category 2"));

        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
    }
}
