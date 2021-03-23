package ru.koryaking.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryService {

    List<CategoryRepr> findAll();
    CategoryRepr findById(Long id);
    Long countAll();
    void saveOrUpdate(CategoryRepr categoryRepr);
    void deleteById(Long id);
}
