package ua.com.petfood.pf.service.impl;

import static ua.com.petfood.pf.helper.constants.Constants.ORDER_PREFIX;
import static ua.com.petfood.pf.helper.constants.Constants.SUBSCRIBED_STATUS;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.helper.OrderHelper;
import ua.com.petfood.pf.helper.UserHelper;
import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.repository.OrderRepository;
import ua.com.petfood.pf.service.OrderService;
import ua.com.petfood.pf.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserHelper userHelper;
    private final OrderHelper orderHelper;

    @Autowired
    private OrderServiceImpl(OrderRepository orderRepository, UserService userService, UserHelper userHelper, OrderHelper orderHelper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.userHelper = userHelper;
        this.orderHelper = orderHelper;
    }

    @Override
    public Order saveOrder(Animal animal, String token, BigDecimal totalOrderPrice) {
        String email = userHelper.getUserEmailFromToken(token);
        User user = userService.findByUsername(email).orElseThrow(() -> new NotFoundException("User not found"));

        Order order = orderRepository.save(createEmptyOrder());
        Long externalOrderId = order.getId();

        order.setOrder_id(createOrderId(externalOrderId));
        order.setUser(user);
        order.setAnimal(animal);
        order.setOrderAmount(totalOrderPrice);

        return orderRepository.save(order);
    }

    @Override
    public OrderPaymentInfo populateLiqPayOrderPaymentInfo(final String data){
        return orderHelper.mapLiqPayOrderPaymentInfo(data);
    }

    @Override
    public void updateOrderPaymentStatus(final OrderPaymentInfo orderPaymentInfo){
        String paymentStatus = orderPaymentInfo.getStatus();
        Order order = orderRepository.getOrderByOrderId(orderPaymentInfo.getOrder_id());

        if(order != null){
            String status = paymentStatus.toUpperCase();
            order.setPaymentStatus(status);
            order.setOrderPaymentDate(orderPaymentInfo.getCreate_date());
            updateSubscriptionStatusForOrder(order, status);

            orderRepository.save(order);
        }
    }

    private void updateSubscriptionStatusForOrder(final Order order, final String status){
        if(SUBSCRIBED_STATUS.equalsIgnoreCase(status)){
            order.setSubscription(true);
            order.setSubscriptionStatus(status);
        }
    }

    private String createOrderId(final Long id){
        Long dateMillis = new Date().getTime();
        String subId = String.valueOf(dateMillis).substring(10);

        return ORDER_PREFIX.concat(subId).concat(String.valueOf(id));
    }

    private Order createEmptyOrder() {
        Order order = new Order();
        order.setOrderCreationDate(new Date());
        order.setOrderAmount(new BigDecimal(0.00));

        return order;
    }
}
