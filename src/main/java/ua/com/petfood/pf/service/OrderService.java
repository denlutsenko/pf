package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.User;

import java.math.BigDecimal;

public interface OrderService {

    Order createOrder(User user);

    Order saveOrder(String token, BigDecimal totalOrderPrice);
}
