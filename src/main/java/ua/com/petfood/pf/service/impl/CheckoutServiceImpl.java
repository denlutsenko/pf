package ua.com.petfood.pf.service.impl;

import static ua.com.petfood.pf.helper.constants.Constants.ANIMAL_ID;
import static ua.com.petfood.pf.helper.constants.Constants.ORDER_ID;
import static ua.com.petfood.pf.helper.constants.Constants.ORDER_TOTAL_AMOUNT;
import static ua.com.petfood.pf.helper.constants.Constants.SKU_ITEMS;
import static ua.com.petfood.pf.helper.constants.Constants.USER_ID;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.Animal;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderSKUItemAmount;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.OrderDTO;
import ua.com.petfood.pf.service.AnimalService;
import ua.com.petfood.pf.service.CheckoutService;
import ua.com.petfood.pf.service.OrderSKUItemAmountService;
import ua.com.petfood.pf.service.OrderService;
import ua.com.petfood.pf.service.SKUItemService;
import ua.com.petfood.pf.service.SKUPriceService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final SKUPriceService priceService;
    private final OrderService orderService;
    private final OrderSKUItemAmountService skuItemAmountService;
    private final SKUItemService itemService;
    private final AnimalService animalService;

    @Autowired
    public CheckoutServiceImpl(SKUPriceService priceService, OrderService orderService,
            OrderSKUItemAmountService skuItemAmountService, SKUItemService itemService, AnimalService animalService) {
        this.priceService = priceService;
        this.orderService = orderService;
        this.skuItemAmountService = skuItemAmountService;
        this.itemService = itemService;
        this.animalService = animalService;
    }

    @Transactional
    public Map<String, Object> checkoutAndSaveOrder(final String bearerToken, final OrderDTO orderDTO) {
        try {
            Map<String, Object> result = new HashMap<>();
            BigDecimal totalOrderPrice = priceService.calculateTotalOrderPrice(orderDTO.getOrderSkuItems());
            Animal animal = animalService.findAnimalById(orderDTO.getAnimalId());

            Order order = orderService.saveOrder(animal, bearerToken, totalOrderPrice);

            List<SKUItem> skuItemList = itemService.getSkuItemListFromOrderSKUItemDtos(orderDTO.getOrderSkuItems());
            List<OrderSKUItemAmount> orderedSkuItems = skuItemAmountService
                    .createAndSaveOrderSKUItemsAmount(skuItemList, order, orderDTO.getOrderSkuItems());

            result.put(ORDER_ID, order.getId());
            result.put(ORDER_TOTAL_AMOUNT, totalOrderPrice);
            result.put(USER_ID, order.getUser().getId());
            result.put(ANIMAL_ID, order.getAnimal().getId());
            result.put(SKU_ITEMS, orderedSkuItems);

            return result;
        } catch(Exception e) {
            throw new NotFoundException("Can not save order");
        }
    }
}
