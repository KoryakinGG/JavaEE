package ru.koryaking.service;

import java.util.List;

public interface CategoryServiceRemote {

    List<CategoryRepr> findAll();
    CategoryRepr findById(Long id);
    Long countAll();
}
