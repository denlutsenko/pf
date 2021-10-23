package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.OrderPaymentInfo;

public interface OrderPaymentInfoService {
    void saveOrderPaymentInfoAndUpdatePaymentStatus(OrderPaymentInfo orderPaymentInfo);
}
