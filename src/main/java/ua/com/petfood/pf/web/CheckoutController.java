package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.model.Order;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;
import ua.com.petfood.pf.service.OrderService;
import ua.com.petfood.pf.service.SKUPriceService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/anon")
public class CheckoutController {

    private SKUPriceService priceService;
    private OrderService orderService;

    @Autowired
    public CheckoutController(SKUPriceService priceService, OrderService orderService) {
        this.priceService = priceService;
        this.orderService = orderService;
    }

    @PostMapping(value = "/checkout/placeOrder")
    ResponseEntity postCheckoutAndPlaceOrder(@RequestBody List<OrderSKUItemDTO> orderSKUItemDTOList) {
        BigDecimal totalOrderPrice = priceService.calculateTotalOrderPrice(orderSKUItemDTOList);
        Order order = new Order();
        order.setOrderTime(new Date());
        order.setOrderAmount(totalOrderPrice);
        order.setUser(null);
        orderService.saveOrder(order);
        //OrderSKUItemAmount should be implemented here
        return ResponseEntity.ok(Map.of(totalOrderPrice, orderSKUItemDTOList));
    }
}
