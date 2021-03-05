package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderSKUItemAmount;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;

import java.util.List;

public interface OrderSKUItemAmountService {

    OrderSKUItemAmount save(OrderSKUItemAmount orderSKUItemAmount);

    List<OrderSKUItemAmount> createAndSaveOrderSKUItemsAmount(List<SKUItem> skuItemList, Order order, List<OrderSKUItemDTO> orderSKUItemDTOList);
}
