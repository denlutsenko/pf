package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ua.com.petfood.pf.service.OrderPaymentInfoService;
import ua.com.petfood.pf.service.OrderService;

@RestController
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    private final OrderPaymentInfoService orderPaymentInfoService;

    @Autowired
    public OrderController(final OrderService orderService, final OrderPaymentInfoService orderPaymentInfoService) {
        this.orderService = orderService;
        this.orderPaymentInfoService = orderPaymentInfoService;
    }

    @GetMapping(value = "/anon/order/{orderId}")
    public ResponseEntity<?> getSalesOrderById(@PathVariable(name = "orderId") String orderId) {
        return ResponseEntity.ok(orderService.getSalesOrderById(orderId));
    }

    @GetMapping(value = "/admin/orders/oneTime/all")
    public ResponseEntity<?> getAllSingleOrders() {
        return ResponseEntity.ok(orderService.getAllOneTimeOrders());
    }

    @GetMapping(value = "/admin/orders/subscription/all")
    public ResponseEntity<?> getAllSubscriptionOrders() {
        return ResponseEntity.ok(orderService.getAllSubscriptions());
    }

    @GetMapping(value = "/admin/oneTime/{orderId}/payment")
    public ResponseEntity<?> getPaymentInfoForOneTimePayment(@PathVariable(name = "orderId") String orderId) {
        return ResponseEntity.ok(orderPaymentInfoService.getPaymentDetailsForOneTimeOrder(orderId));
    }

    @GetMapping(value = "/admin/subscription/{orderId}/payments/all")
    public ResponseEntity<?> getAllPaymentsInfoForOrderBySubscription(@PathVariable(name = "orderId") String orderId) {
        return ResponseEntity.ok(orderPaymentInfoService.getAllSubscriptionPaymentsForOrder(orderId));
    }
}
