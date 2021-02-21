package ua.com.petfood.pf.web;

import com.liqpay.LiqPay;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.petfood.pf.model.dto.LiqPayDto;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/checkout")
public class CheckoutController {
    public static final String PUBLIC_KEY = "sandbox_i26650974940";
    public static final String PRIVATE_KEY = "sandbox_D7xQSF6DZbIrvj5suxPSUz32OWNTpIbFVqA80MIR";

    @PostMapping(value = "/subscribe")
    ResponseEntity createMonthlySubscription(@RequestBody LiqPayDto liqPayDto) throws Exception {
        LiqPay liqpay = new LiqPay(PUBLIC_KEY, PRIVATE_KEY);
        Map<String, String> params = new HashMap<>();
        params.put("action", liqPayDto.getAction());
        params.put("version", liqPayDto.getVersion());
        params.put("phone", liqPayDto.getPhone());
        params.put("amount", liqPayDto.getAmount());
        params.put("currency", liqPayDto.getCurrency());
        params.put("description", liqPayDto.getDescription());
        params.put("order_id", liqPayDto.getDescription());
        params.put("subscribe", liqPayDto.getSubscribe());
        params.put("subscribe_date_start", liqPayDto.getSubscribe_date_start());
        params.put("subscribe_periodicity", liqPayDto.getSubscribe_periodicity());
        params.put("card", liqPayDto.getCard());
        params.put("card_exp_month", liqPayDto.getCard_exp_month());
        params.put("card_exp_year", liqPayDto.getCard_exp_year());
        params.put("card_cvv", liqPayDto.getCard_cvv());
        HashMap<String, Object> res = (HashMap<String, Object>) liqpay.api("request", params);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    ResponseEntity createSinglePayment(@RequestBody LiqPayDto liqPayDto) {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", liqPayDto.getAction());
        params.put("amount", liqPayDto.getAmount());
        params.put("currency", liqPayDto.getCurrency());
        params.put("description", liqPayDto.getDescription());
        params.put("order_id", liqPayDto.getOrder_id());
        params.put("version", liqPayDto.getVersion());
        params.put("result_url", liqPayDto.getResult_url());
        LiqPay liqpay = new LiqPay(PUBLIC_KEY, PRIVATE_KEY);
        return ResponseEntity.ok(liqpay.cnb_form(params));
    }
}
