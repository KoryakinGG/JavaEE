package ru.koryaking.persist;

import ru.koryaking.service.ProductRepr;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table (name = "products")
@NamedQueries( {
        @NamedQuery(name = "findAllProduct", query = "from Product"),
        @NamedQuery(name = "countAllProduct", query = "select count(*) from Product"),
        @NamedQuery(name = "deleteByIdProduct", query = "delete from Product p where p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "description", length = 1024)
    private String description;

    @Column (name = "price")
    private BigDecimal price;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(ProductRepr productRepr, Category category) {
        this(productRepr.getId(),
             productRepr.getName(),
             productRepr.getDescription(),
             productRepr.getPrice());
        this.category = category;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {return category;}

    public void setCategory(Category category) {this.category = category;}
}
