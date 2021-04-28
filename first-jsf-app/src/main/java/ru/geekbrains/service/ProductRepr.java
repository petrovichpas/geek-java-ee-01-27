package ru.geekbrains.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.Product;

import java.io.Serializable;
import java.math.BigDecimal;

// DTO
@Data
@NoArgsConstructor
public class ProductRepr implements Serializable {

    private Long id, categoryId;
    private String name, description, categoryName;
    private BigDecimal price;

    public ProductRepr(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        Category category = product.getCategory();
        categoryId = category != null ? category.getId() : null;
        categoryName = category != null ? category.getName() : null;
    }
}
