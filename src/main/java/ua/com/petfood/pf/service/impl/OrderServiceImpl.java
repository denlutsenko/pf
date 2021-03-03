package ua.com.petfood.pf.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.repository.OrderRepository;
import ua.com.petfood.pf.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(final User user) {
        Order order = new Order();
        order.setOrderAmount(new BigDecimal(0.00));
        order.setOrderTime(new Date());
        order.setUser(user);

        return orderRepository.save(order);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
