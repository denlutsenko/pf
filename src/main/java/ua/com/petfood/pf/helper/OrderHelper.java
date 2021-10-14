package ua.com.petfood.pf.helper;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.com.petfood.pf.model.OrderPaymentInfo;

@Component
public class OrderHelper {

    public OrderHelper(){}

    public OrderPaymentInfo getLiqPayOrderResultStatus(final String data) {
        String decodedString = decodeStringData(data);

        ObjectMapper objectMapper = new ObjectMapper();
        OrderPaymentInfo orderPaymentInfo = new OrderPaymentInfo();

        try {
            orderPaymentInfo = objectMapper.readValue(decodedString, OrderPaymentInfo.class);
        } catch(IOException e) {
            e.printStackTrace();//TODO как-то обработать. Возможно в будущем написать свой кастомный конвертер
        }
        return orderPaymentInfo;
    }

    private String decodeStringData(final String data) {
        return new String(Base64.getDecoder().decode(data));
    }
}
