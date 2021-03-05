package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;
import ua.com.petfood.pf.service.CheckoutService;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@CrossOrigin
@RequestMapping(value = "/anon")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping(value = "/checkout/placeOrder")
    public ResponseEntity postCheckoutAndPlaceOrder(@RequestHeader(AUTHORIZATION) String token, @RequestBody List<OrderSKUItemDTO> orderSKUItemDTOList) {
        return ResponseEntity.ok(Map.of(checkoutService.calculatePriceAndPlaceOrder(token, orderSKUItemDTOList), orderSKUItemDTOList));
    }

}
