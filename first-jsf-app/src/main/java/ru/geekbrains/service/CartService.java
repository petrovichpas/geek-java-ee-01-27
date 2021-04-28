package ru.geekbrains.service;

import javax.ejb.Local;

@Local
public interface CartService {

    // TODO
    void addToCart(ProductRepr product);
}
