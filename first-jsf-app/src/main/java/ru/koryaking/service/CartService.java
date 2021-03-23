package ru.koryaking.service;

import ru.koryaking.service.ProductRepr;

import javax.ejb.Local;
import java.util.List;

@Local // к интерфейсу можно обращаться локально
public interface CartService {

    void addToCart(ProductRepr product);
    void deleteAtCardById (Long id);
    List<ProductRepr> getAllProducts();
    ProductRepr getProductById (Long id);
}
