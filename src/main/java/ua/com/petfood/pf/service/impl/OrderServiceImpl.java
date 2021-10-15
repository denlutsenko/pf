package ua.com.petfood.pf.service.impl;

import static ua.com.petfood.pf.helper.constants.Constants.ORDER_PREFIX;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.helper.UserHelper;
import ua.com.petfood.pf.model.Animal;
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
    public Order saveOrder(Animal animal, String token, BigDecimal totalOrderPrice) {
        String email = userHelper.getUserEmailFromToken(token);
        User user = userService.findByUsername(email);

        Order order = orderRepository.save(createEmptyOrder());
        Long externalOrderId = order.getId();

        order.setOrder_id(createOrderId(externalOrderId));
        order.setUser(user);
        order.setAnimal(animal);
        order.setOrderAmount(totalOrderPrice);

        return orderRepository.save(order);
    }

    private String createOrderId(final Long id){
        Long dateMillis = new Date().getTime();
        String subId = String.valueOf(dateMillis).substring(10);

        return ORDER_PREFIX.concat(subId).concat(String.valueOf(id));
    }

    private Order createEmptyOrder() {
        Order order = new Order();
        order.setOrderTime(new Date());
        order.setOrderAmount(new BigDecimal(0.00));

        return order;
    }
}
