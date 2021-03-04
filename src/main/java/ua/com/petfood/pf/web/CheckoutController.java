package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;
import ua.com.petfood.pf.service.OrderService;
import ua.com.petfood.pf.service.SKUPriceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@CrossOrigin
@RequestMapping(value = "/anon")
public class CheckoutController {

    private final SKUPriceService priceService;
    private final OrderService orderService;

    @Autowired
    public CheckoutController(SKUPriceService priceService, OrderService orderService) {
        this.priceService = priceService;
        this.orderService = orderService;
    }

    @PostMapping(value = "/checkout/placeOrder")
    public ResponseEntity postCheckoutAndPlaceOrder(@RequestHeader(AUTHORIZATION) String token, @RequestBody List<OrderSKUItemDTO> orderSKUItemDTOList) {
        BigDecimal totalOrderPrice = priceService.calculateTotalOrderPrice(orderSKUItemDTOList);
        Order order = orderService.saveOrder(token, totalOrderPrice);



        return ResponseEntity.ok(Map.of(totalOrderPrice, orderSKUItemDTOList));
    }
}
