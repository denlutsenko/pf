package ua.com.petfood.pf.service;

import java.util.List;

import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderSKUItemAmount;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.OrderSKUItems;

public interface OrderSKUItemAmountService {

    OrderSKUItemAmount save(OrderSKUItemAmount orderSKUItemAmount);

    List<OrderSKUItemAmount> createAndSaveOrderSKUItemsAmount(List<SKUItem> skuItemList, Order order,
            List<OrderSKUItems> orderSKUItemDTOList);
}
