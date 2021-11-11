package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ua.com.petfood.pf.service.OrderService;

@RestController
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/anon/order/{orderId}")
    public ResponseEntity<?> getSalesOrderById(@PathVariable(name = "orderId") String orderId) {
        return ResponseEntity.ok(orderService.getSalesOrderById(orderId));
    }
}
