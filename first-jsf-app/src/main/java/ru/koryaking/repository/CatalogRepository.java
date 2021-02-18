package ru.koryaking.repository;

import ru.koryaking.persist.Catalog;
import ru.koryaking.persist.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Named
@ApplicationScoped
public class CatalogRepository {
    private final Map<Long, Catalog> catalogMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new Catalog(null, "Catalog  1",
                "Description of catalog 1"));
        this.saveOrUpdate(new Catalog(null, "Catalog  2",
                "Description of catalog 2"));
        this.saveOrUpdate(new Catalog(null, "Catalog  3",
                "Description of catalog 3"));
    }

    public List<Catalog> findAll() {
        return new ArrayList<>(catalogMap.values());
    }

    public Catalog findById(Long id) {
        return catalogMap.get(id);
    }

    public void saveOrUpdate(Catalog catalog) {
        if (catalog.getId() == null) {
            Long id = identity.incrementAndGet();
            catalog.setId(id);
        }
        catalogMap.put(catalog.getId(), catalog);
    }

    public void deleteById(Long id) {
        catalogMap.remove(id);
    }

}
