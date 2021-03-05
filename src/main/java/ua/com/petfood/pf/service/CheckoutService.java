package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CheckoutService {

    BigDecimal calculatePriceAndPlaceOrder(String token, List<OrderSKUItemDTO> orderSKUItemDTOList);

}
