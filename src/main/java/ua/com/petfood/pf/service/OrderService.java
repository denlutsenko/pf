package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.User;

import java.math.BigDecimal;

public interface OrderService {

    Order saveOrder(Animal animal, String token, BigDecimal totalOrderPrice);
}
