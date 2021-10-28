package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.*;

import java.math.BigDecimal;
import java.util.Date;

public interface OrderService {

    Order saveOrder(Animal animal, String token, BigDecimal totalOrderPrice);
    OrderPaymentInfo populateLiqPayOrderPaymentInfo(String data);
    void updateOrderPaymentStatus(OrderPaymentInfo orderPaymentInfo);
    void updateOrderDeliveryAddress(DeliveryAddress deliveryAddress);
}
