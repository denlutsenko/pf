package ua.com.petfood.pf.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ua.com.petfood.pf.helper.UserHelper;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.repository.OrderRepository;
import ua.com.petfood.pf.service.OrderService;
import ua.com.petfood.pf.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserHelper userHelper;

    @Autowired
    private OrderServiceImpl(OrderRepository orderRepository, UserService userService, UserHelper userHelper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.userHelper = userHelper;
    }

    @Override
    public Order createOrder(final User user) {
        Order order = getOrderWithoutPrice(user);
        order.setOrderAmount(new BigDecimal(0.00));
        return orderRepository.save(order);
    }

    @Override
    public Order saveOrder(String token, BigDecimal totalOrderPrice) {
        String email = userHelper.getUserEmailFromToken(token);
        User user = userService.findByUsername(email);
        Order order = getOrderWithoutPrice(user);
        order.setOrderAmount(totalOrderPrice);
        return orderRepository.save(order);
    }

    private Order getOrderWithoutPrice(User user) {
        Order order = new Order();
        order.setOrderTime(new Date());
        order.setUser(user);
        return order;
    }
}
