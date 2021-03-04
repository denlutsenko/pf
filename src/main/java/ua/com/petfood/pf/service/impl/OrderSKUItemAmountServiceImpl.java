package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderSKUItemAmount;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.repository.OrderSKUItemAmountRepository;
import ua.com.petfood.pf.service.OrderSKUItemAmountService;

@Service
public class OrderSKUItemAmountServiceImpl implements OrderSKUItemAmountService {

    private final OrderSKUItemAmountRepository repository;

    @Autowired
    public OrderSKUItemAmountServiceImpl(OrderSKUItemAmountRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderSKUItemAmount save(OrderSKUItemAmount orderSKUItemAmount) {
        return repository.save(orderSKUItemAmount);
    }

    public OrderSKUItemAmount createAndSaveOrderSKUItemAmount(Order order, SKUItem skuItem, int quantity) {
        OrderSKUItemAmount orderSKUItemAmount = new OrderSKUItemAmount();
        orderSKUItemAmount.setOrder(order);
        orderSKUItemAmount.setQuantity(quantity);
        orderSKUItemAmount.setSkuItem(skuItem);
        save(orderSKUItemAmount);
        return orderSKUItemAmount;
    }
}
