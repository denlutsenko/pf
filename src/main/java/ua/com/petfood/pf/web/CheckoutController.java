package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.OrderSKUItemAmount;
import ua.com.petfood.pf.model.SKUItem;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;
import ua.com.petfood.pf.service.OrderSKUItemAmountService;
import ua.com.petfood.pf.service.OrderService;
import ua.com.petfood.pf.service.SKUItemService;
import ua.com.petfood.pf.service.SKUPriceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@CrossOrigin
@RequestMapping(value = "/anon")
public class CheckoutController {

    private final SKUPriceService priceService;
    private final OrderService orderService;
    private final OrderSKUItemAmountService skuItemAmountService;
    private final SKUItemService itemService;

    @Autowired
    public CheckoutController(SKUPriceService priceService, OrderService orderService, OrderSKUItemAmountService skuItemAmountService, SKUItemService itemService) {
        this.priceService = priceService;
        this.orderService = orderService;
        this.skuItemAmountService = skuItemAmountService;
        this.itemService = itemService;
    }

    @PostMapping(value = "/checkout/placeOrder")
    public ResponseEntity postCheckoutAndPlaceOrder(@RequestHeader(AUTHORIZATION) String token, @RequestBody List<OrderSKUItemDTO> orderSKUItemDTOList) {
        BigDecimal totalOrderPrice = priceService.calculateTotalOrderPrice(orderSKUItemDTOList);
        Order order = orderService.saveOrder(token, totalOrderPrice);

        List<SKUItem> skuItemList = orderSKUItemDTOList.stream()
                .map(OrderSKUItemDTO::getSkuItemId)
                .map(itemService::findSKUItemById)
                .collect(Collectors.toList());

        List<OrderSKUItemAmount> itemAmounts = skuItemList.stream()
                .map(i -> skuItemAmountService
                        .createAndSaveOrderSKUItemAmount(order, i, orderSKUItemDTOList
                                .stream()
                                .filter(d -> d.getSkuItemId().equals(i.getId()))
                                .map(OrderSKUItemDTO::getQuantity)
                                .findFirst().orElseThrow())).collect(Collectors.toList());

        return ResponseEntity.ok(Map.of(totalOrderPrice, orderSKUItemDTOList));
    }
}
