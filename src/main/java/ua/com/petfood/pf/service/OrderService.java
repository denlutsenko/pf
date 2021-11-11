package ua.com.petfood.pf.service;

import java.math.BigDecimal;

import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.DeliveryAddress;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderPaymentInfo;

public interface OrderService {

    Order saveOrder(Animal animal, String token, BigDecimal totalOrderPrice);
    OrderPaymentInfo populateLiqPayOrderPaymentInfo(String data);
    void updateOrderPaymentStatus(OrderPaymentInfo orderPaymentInfo);
    void updateOrderDeliveryAddress(DeliveryAddress deliveryAddress);
    Order getSalesOrderById(String orderId);
}
