package ru.koryaking.persist;

import ru.koryaking.service.CategoryRepr;
import ru.koryaking.service.ProductRepr;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@NamedQueries( {
        @NamedQuery(name = "findAllCategory", query = "from Category"),
        @NamedQuery(name = "countAllCategory", query = "select count(*) from Category"),
        @NamedQuery(name = "deleteByIdCategory", query = "delete from Category c where c.id = :id"),
        @NamedQuery(name = "allProductsByCategoryId",
                query = "select p " +
                        "from Product p " +
                        "inner join Category c on p.category.id = c.id " +
                        "where c.id = :id")
})
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany (mappedBy = "category")
    private List <Product> products;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(CategoryRepr categoryRepr) {
        this.id = categoryRepr.getId();
        this.name = categoryRepr.getName();
        this.description = categoryRepr.getDescription();
    }

    public Category() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {return products;}

    public void setProducts(List<Product> products) {this.products = products;}
}
