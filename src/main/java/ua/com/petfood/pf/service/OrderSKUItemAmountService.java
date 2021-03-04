package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderSKUItemAmount;
import ua.com.petfood.pf.model.SKUItem;

public interface OrderSKUItemAmountService {

    OrderSKUItemAmount save(OrderSKUItemAmount orderSKUItemAmount);

    OrderSKUItemAmount createAndSaveOrderSKUItemAmount(Order order, SKUItem skuItem, int quantity);
}
