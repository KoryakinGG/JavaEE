package ru.koryaking.service;

import ru.koryaking.service.ProductRepr;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService{

    private final Map<Long, ProductRepr> productMap = new HashMap<>();

    @Override
    public void addToCart(ProductRepr product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public void deleteAtCardById(Long id) {
        productMap.remove(id);
    }


    @Override
    public List<ProductRepr> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public ProductRepr getProductById(Long id) {
        return productMap.get(id);
    }
}
