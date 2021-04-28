package ru.geekbrains.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.geekbrains.persist.Product;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {
    @Getter
    @Setter
    private List<Product> productsList = new ArrayList();

    public void addToCart(Product product) {
        productsList.add(product);
    }

    public void removeFromCart(Product product) {
        productsList.remove(product);
    }
}
