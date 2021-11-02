package ua.com.petfood.pf.web;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.petfood.pf.helper.constants.Constants.DATA;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.model.dto.OrderDTO;
import ua.com.petfood.pf.service.CheckoutService;
import ua.com.petfood.pf.service.OrderPaymentInfoService;
import ua.com.petfood.pf.service.OrderService;

@RestController
@CrossOrigin
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final OrderPaymentInfoService orderPaymentInfoService;
    private final OrderService orderService;

    @Autowired
    public CheckoutController(final CheckoutService checkoutService,
            final OrderPaymentInfoService orderPaymentInfoService, final OrderService orderService) {
        this.checkoutService = checkoutService;
        this.orderPaymentInfoService = orderPaymentInfoService;
        this.orderService = orderService;
    }

    @PostMapping(value = "/anon/checkout/placeOrder")
    public ResponseEntity<?> postCheckoutAndPlaceOrder(@RequestHeader(AUTHORIZATION) String bearerToken,
            @RequestBody OrderDTO orderDTO) {

        return ResponseEntity.ok(checkoutService.checkoutAndSaveOrder(bearerToken, orderDTO));
    }

    @PostMapping(value = "/api/liqpay/payment/result", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public void receiveLiqPayPaymentResponse(@RequestParam Map<String, String> body) {
        OrderPaymentInfo orderPaymentInfo = orderService.populateLiqPayOrderPaymentInfo(body.get(DATA));
        orderPaymentInfoService.saveOrderPaymentInfoAndUpdatePaymentStatus(orderPaymentInfo);
    }





    /*
    * DO NOT DELETE THIS !!!!
    * TEST CALL TO RECEIVE PAYMENT STATUS !!!!

    @PostMapping(value = "/api/liqpay/payment/status", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity receiveLiqpayPaymentResponse(@RequestParam Map<String, String> body) throws Exception {
        System.out.println("==============================================");
        String data = body.get("data");
        System.out.println(data);

        byte[] decodedBytes = Base64.getDecoder().decode(data);
        String decodedString = new String(decodedBytes);
        System.out.println("decodedString:   "+decodedString);

        System.out.println(body.get("signature"));
        System.out.println("==============================================");

        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "status");
        params.put("version", "3");
        params.put("order_id", "000009");
        params.put("public_key", "sandbox_i95284259315");



        LiqPay liqpay = new LiqPay("sandbox_i95284259315", "sandbox_a3fKl4MFuwBEptNyt4IVRGWPz46lu9HIZa1ihX5m");


        Map<String, Object> res = liqpay.api("request", params);
        System.out.println("==============================================");
        System.out.println("\n");
        System.out.println(res.get("status"));
        System.out.println("==============================================");
        System.out.println("\n");
        System.out.println(res);


        return ResponseEntity.ok().build();
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "status");
        params.put("version", "3");
        params.put("order_id", "000009");
        params.put("public_key", "sandbox_i95284259315");



        LiqPay liqpay = new LiqPay("sandbox_i95284259315", "sandbox_a3fKl4MFuwBEptNyt4IVRGWPz46lu9HIZa1ihX5m");


        Map<String, Object> res = liqpay.api("request", params);
        System.out.println(res);
    }
     */
}
