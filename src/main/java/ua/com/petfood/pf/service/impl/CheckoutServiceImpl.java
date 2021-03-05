package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;
import ua.com.petfood.pf.service.*;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final SKUPriceService priceService;
    private final OrderService orderService;
    private final OrderSKUItemAmountService skuItemAmountService;
    private final SKUItemService itemService;

    @Autowired
    public CheckoutServiceImpl(SKUPriceService priceService, OrderService orderService, OrderSKUItemAmountService skuItemAmountService, SKUItemService itemService) {
        this.priceService = priceService;
        this.orderService = orderService;
        this.skuItemAmountService = skuItemAmountService;
        this.itemService = itemService;
    }

    @Transactional
    public BigDecimal calculatePriceAndPlaceOrder(String token, List<OrderSKUItemDTO> orderSKUItemDTOList) {
        BigDecimal totalOrderPrice = priceService.calculateTotalOrderPrice(orderSKUItemDTOList);
        createAndSaveOrderSKUItemsAmount(token, orderSKUItemDTOList, totalOrderPrice, getSkuItemListFromOrderSKUItemDtos(orderSKUItemDTOList));
        return totalOrderPrice;
    }

    private List<SKUItem> getSkuItemListFromOrderSKUItemDtos(List<OrderSKUItemDTO> orderSKUItemDTOList) {
        return itemService.getSkuItemListFromOrderSKUItemDtos(orderSKUItemDTOList);
    }

    private void createAndSaveOrderSKUItemsAmount(String token, List<OrderSKUItemDTO> orderSKUItemDTOList, BigDecimal totalOrderPrice, List<SKUItem> skuItemList) {
        skuItemAmountService.createAndSaveOrderSKUItemsAmount(skuItemList, saveOrder(token, totalOrderPrice), orderSKUItemDTOList);
    }

    private Order saveOrder(String token, BigDecimal totalOrderPrice) {
        return orderService.saveOrder(token, totalOrderPrice);
    }
}
