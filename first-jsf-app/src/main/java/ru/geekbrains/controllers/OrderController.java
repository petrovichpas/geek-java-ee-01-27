package ru.geekbrains.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.OrderRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {
    @Inject
    private OrderRepository orderRepository;

    @Getter
    @Setter
    private Order order;

    public String createOrder() {
        order = new Order();
        return "";
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String editOrder(Order order) {
        this.order = order;
        return "";
    }

    public String saveOrder() {
        orderRepository.saveOrUpdate(order);
        return "/order.xhtml?faces-redirect=true";
    }
}
