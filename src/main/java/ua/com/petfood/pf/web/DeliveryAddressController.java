package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.petfood.pf.model.dto.DeliveryAddressDTO;
import ua.com.petfood.pf.service.DeliveryAddressService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@CrossOrigin
public class DeliveryAddressController {
    private final DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }


    @PostMapping(value = "/api/checkout/delivery_address")
    public ResponseEntity postDeliveryAddress(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody DeliveryAddressDTO deliveryAddressDTO) {
        return ResponseEntity.ok(deliveryAddressService.saveDeliveryAddress(bearerToken, deliveryAddressDTO));
    }
//    @GetMapping(value = "/api/checkout/get_delivery_address/{email}")
//    public ResponseEntity getDeliveryAddress(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable String email) {
//        return ResponseEntity.ok(deliveryAddressService.getDeliveryAddressesForUser(email));
//    }

}
