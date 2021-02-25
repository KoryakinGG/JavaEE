package ru.koryaking.controller;

import ru.koryaking.persist.Catalog;
import ru.koryaking.persist.Product;
import ru.koryaking.repository.CatalogRepository;
import ru.koryaking.repository.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@SessionScoped
public class CatalogController implements Serializable {
    @Inject
    private CatalogRepository catalogRepository;

    private Catalog catalog;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String createCatalog() {
        this.catalog = new Catalog();
        return "/catalog_form.xhtml?faces-redirect-true";
    }

    public List<Catalog> getAllCatalogs() {
        return catalogRepository.findAll();
    }

    public String editCatalog(Catalog catalog) {
        this.catalog = catalog;
        return "/catalog_form.xhtml?faces-redirect-true";
    }

    public void deleteCatalog(Catalog catalog) {
        catalogRepository.deleteById(catalog.getId());
    }

    public String saveCatalog() {
        catalogRepository.saveOrUpdate(catalog);
        return "/catalog.xhtml?faces-redirect-true";
    }

}
