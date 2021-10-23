package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.repository.OrderPaymentInfoRepository;
import ua.com.petfood.pf.service.OrderPaymentInfoService;
import ua.com.petfood.pf.service.OrderService;

@Service
public class OrderPaymentInfoServiceImpl implements OrderPaymentInfoService {

    private final OrderPaymentInfoRepository orderPaymentInfoRepository;
    private final OrderService orderService;

    @Autowired
    public OrderPaymentInfoServiceImpl(final OrderPaymentInfoRepository orderPaymentInfoRepository,
            final OrderService orderService) {
        this.orderPaymentInfoRepository = orderPaymentInfoRepository;
        this.orderService = orderService;
    }

    @Override
    public void saveOrderPaymentInfoAndUpdatePaymentStatus(final OrderPaymentInfo orderPaymentInfo) {
        if(orderPaymentInfo != null) {
            orderPaymentInfoRepository.save(orderPaymentInfo);
            orderService.updateOrderPaymentStatus(orderPaymentInfo.getOrder_id(), orderPaymentInfo.getStatus(),
                    orderPaymentInfo.getCreate_date());
        }
    }
}
