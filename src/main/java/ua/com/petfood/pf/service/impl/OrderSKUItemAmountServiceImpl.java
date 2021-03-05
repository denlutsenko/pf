package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderSKUItemAmount;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;
import ua.com.petfood.pf.repository.OrderSKUItemAmountRepository;
import ua.com.petfood.pf.service.OrderSKUItemAmountService;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<OrderSKUItemAmount> createAndSaveOrderSKUItemsAmount(List<SKUItem> skuItemList, Order order, List<OrderSKUItemDTO> orderSKUItemDTOList) {
        return skuItemList.stream()
                .map(i -> createAndSaveOrderSKUItemAmount(order, i, orderSKUItemDTOList
                        .stream()
                        .filter(d -> d.getSkuItemId().equals(i.getId()))
                        .map(OrderSKUItemDTO::getQuantity)
                        .findFirst().orElseThrow())).collect(Collectors.toList());
    }

    private OrderSKUItemAmount createAndSaveOrderSKUItemAmount(Order order, SKUItem skuItem, int quantity) {
        OrderSKUItemAmount orderSKUItemAmount = new OrderSKUItemAmount();
        orderSKUItemAmount.setOrder(order);
        orderSKUItemAmount.setQuantity(quantity);
        orderSKUItemAmount.setSkuItem(skuItem);
        save(orderSKUItemAmount);
        return orderSKUItemAmount;
    }
}
