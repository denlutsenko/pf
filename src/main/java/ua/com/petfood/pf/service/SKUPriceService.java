package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.SKUPrice;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;

import java.math.BigDecimal;
import java.util.List;

public interface SKUPriceService {

    BigDecimal getPriceForSingleItem(Long id);

    List<SKUPrice> getPriceForMultipleItems(List<Long> ids);

    BigDecimal calculateTotalOrderPrice(List<OrderSKUItemDTO> dtos);
}
