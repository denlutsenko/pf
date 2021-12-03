package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.SKUPrice;
import ua.com.petfood.pf.model.dto.OrderSKUItems;

import java.math.BigDecimal;
import java.util.List;

public interface SKUPriceService {

    BigDecimal getPriceForSingleItem(Long id);

    List<SKUPrice> getPriceForMultipleItems(List<Long> ids);

    BigDecimal calculateTotalOrderPrice(List<OrderSKUItems> dtos);

    List<SKUPrice> findSKUItemsWithPricesByAnimalCategory(Long animalCategoryId);

    SKUPrice getSkuPriceItemBySkuId(final Long id);
}
