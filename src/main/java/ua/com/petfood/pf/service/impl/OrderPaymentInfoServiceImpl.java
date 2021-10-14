package ua.com.petfood.pf.service.impl;

import org.springframework.stereotype.Service;

import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.repository.OrderPaymentInfoRepository;
import ua.com.petfood.pf.service.OrderPaymentInfoService;

@Service
public class OrderPaymentInfoServiceImpl implements OrderPaymentInfoService {

    private final OrderPaymentInfoRepository orderPaymentInfoRepository;

    public OrderPaymentInfoServiceImpl(final OrderPaymentInfoRepository orderPaymentInfoRepository) {
        this.orderPaymentInfoRepository = orderPaymentInfoRepository;
    }

    @Override
    public void saveOrderPaymentInfo(final OrderPaymentInfo orderPaymentInfo) {
        if (orderPaymentInfo != null){
            orderPaymentInfoRepository.save(orderPaymentInfo);
        }
    }
}
