package ua.com.petfood.pf.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.model.dto.OrderPaymentInfoDTO;
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
    public void saveOrderPaymentInfoAndUpdatePaymentStatus(final OrderPaymentInfoDTO orderPaymentInfoDTO) {
        if(orderPaymentInfoDTO != null && orderPaymentInfoDTO.getOrder_id() != null) {
            OrderPaymentInfo orderPaymentInfo = mapDeliveryAddressDTOToDeliveryAddressEntity(orderPaymentInfoDTO);

            orderPaymentInfoRepository.save(orderPaymentInfo);
            orderService.updateOrderPaymentStatus(orderPaymentInfo);
        }
    }

    @Override
    public List<OrderPaymentInfo> getAllSubscriptionPaymentsForOrder(String orderId){
       return orderPaymentInfoRepository.findAllSubscriptionPaymentsForOrder(orderId);
    }

    @Override
    public OrderPaymentInfo getPaymentDetailsForOneTimeOrder(String orderId){
        return orderPaymentInfoRepository.findPaymentDetailsForOrder(orderId);
    }

    private OrderPaymentInfo mapDeliveryAddressDTOToDeliveryAddressEntity(
            final OrderPaymentInfoDTO orderPaymentInfoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(orderPaymentInfoDTO, OrderPaymentInfo.class);
    }
}
