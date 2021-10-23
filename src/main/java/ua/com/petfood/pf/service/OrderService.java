package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.model.User;

import java.math.BigDecimal;
import java.util.Date;

public interface OrderService {

    Order saveOrder(Animal animal, String token, BigDecimal totalOrderPrice);
    OrderPaymentInfo populateLiqPayOrderPaymentInfo(String data);
    void updateOrderPaymentStatus(String orderId, String paymentStatus, Date paymentDate);
}
