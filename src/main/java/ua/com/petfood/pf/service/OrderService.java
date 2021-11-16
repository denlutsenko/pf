package ua.com.petfood.pf.service;

import java.math.BigDecimal;
import java.util.List;

import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.DeliveryAddress;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.model.dto.OrderPaymentInfoDTO;

public interface OrderService {

    Order saveOrder(Animal animal, String token, BigDecimal totalOrderPrice);

    OrderPaymentInfoDTO populateLiqPayOrderPaymentInfo(String data);

    void updateOrderPaymentStatus(OrderPaymentInfo orderPaymentInfo);

    void updateOrderDeliveryAddress(DeliveryAddress deliveryAddress);

    Order getSalesOrderById(String orderId);

    List<Order> getAllOneTimeOrders();

    List<Order> getAllSubscriptions();
}
