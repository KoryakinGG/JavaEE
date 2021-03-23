package ru.koryaking.persist;

import javax.persistence.*;

@Entity
@Table(name = "order")
@NamedQueries( {
        @NamedQuery(name = "findAllOrder", query = "from Order"),
        @NamedQuery(name = "countAllOrder", query = "select count(*) from Order"),
        @NamedQuery(name = "deleteByIdOrder", query = "delete from Order p where p.id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    public Order() {
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
}
