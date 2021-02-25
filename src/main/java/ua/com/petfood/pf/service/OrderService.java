package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.User;

public interface OrderService {

    Order createOrder(User user);
}
