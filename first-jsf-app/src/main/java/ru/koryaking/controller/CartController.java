package ru.koryaking.controller;

import ru.koryaking.service.ProductRepr;
import ru.koryaking.service.CartService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<ProductRepr> getAllProducts (){
        return cartService.getAllProducts();
    }

    public ProductRepr getProductById(Long id) {
        return cartService.getProductById(id);
    }

    public void removeFromCard(ProductRepr productRepr) {
        cartService.deleteAtCardById(productRepr.getId());
    }

    public void addToCart(ProductRepr productRepr) {
        cartService.addToCart(productRepr);
    }



}
