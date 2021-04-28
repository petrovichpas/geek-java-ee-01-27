package ru.geekbrains.service;


import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService {

    private final Map<Long, ProductRepr> productMap = new HashMap<>();

    @Override
    public void addToCart(ProductRepr product) {

    }
}
