package ru.koryaking.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CategoryServiceWs {

    @WebMethod
    List<ProductRepr> findAll();

    @WebMethod
    ProductRepr findById(Long id);
}