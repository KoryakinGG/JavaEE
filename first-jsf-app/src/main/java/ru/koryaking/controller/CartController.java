package ru.koryaking.controller;

import ru.koryaking.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@SessionScoped
public class CartController implements Serializable {
    private final AtomicLong identity = new AtomicLong(0);
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    public String addToCart(Product product) {
        if (product.getId() == null) {
            Long id = identity.incrementAndGet();
            product.setId(id);
        }
        productMap.put(product.getId(), product);
        return "/cart.xhtml?faces-redirect-true";
    }

    public void removeFromCart(Product product) {
        productMap.remove(product.getId());
    }

    public Object getAllProducts() {
        return new ArrayList<>(productMap.values());
    }
}
