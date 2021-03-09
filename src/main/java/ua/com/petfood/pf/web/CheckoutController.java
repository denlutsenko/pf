package ua.com.petfood.pf.web;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.petfood.pf.model.dto.OrderDTO;
import ua.com.petfood.pf.service.CheckoutService;

@RestController
@CrossOrigin
@RequestMapping(value = "/anon")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(final CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping(value = "/checkout/placeOrder")
    public ResponseEntity postCheckoutAndPlaceOrder(@RequestHeader(AUTHORIZATION) String bearerToken,
            @RequestBody OrderDTO orderDTO) {

        return ResponseEntity.ok(checkoutService.checkoutAndSaveOrder(bearerToken, orderDTO));
    }
}
