package ru.koryaking.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    List<ProductRepr> findAll();
    ProductRepr findById(Long id);
    Long countAll();
    void saveOrUpdate(ProductRepr productRepr);
    void deleteById(Long id);


}
