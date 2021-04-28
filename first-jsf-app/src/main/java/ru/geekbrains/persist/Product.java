package ru.geekbrains.persist;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.service.ProductRepr;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@NamedQueries({@NamedQuery(name = "findAll", query = "from Product"),
        @NamedQuery(name = "countAll", query = "select count(*) from Product"),
        @NamedQuery(name = "deleteById", query = "delete from Product p where p.id = : id")})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @Column
    private BigDecimal price;

    @ManyToOne
    private Category category;

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(ProductRepr productRepr, Category category) {
        this(productRepr.getId(), productRepr.getName(), productRepr.getDescription(), productRepr.getPrice());
        this.category = category;
    }
}
