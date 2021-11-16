package ua.com.petfood.pf.service;

import java.util.List;

import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.model.dto.OrderPaymentInfoDTO;

public interface OrderPaymentInfoService {
    void saveOrderPaymentInfoAndUpdatePaymentStatus(OrderPaymentInfoDTO orderPaymentInfoDTO);

    List<OrderPaymentInfo> getAllSubscriptionPaymentsForOrder(String orderId);
    OrderPaymentInfo getPaymentDetailsForOneTimeOrder(String orderId);
}
