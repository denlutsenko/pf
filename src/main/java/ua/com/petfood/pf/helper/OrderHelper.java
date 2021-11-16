package ua.com.petfood.pf.helper;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.com.petfood.pf.model.OrderPaymentInfo;
import ua.com.petfood.pf.model.dto.OrderPaymentInfoDTO;

@Component
public class OrderHelper {

    public OrderHelper() {
    }

    public OrderPaymentInfoDTO mapLiqPayOrderPaymentInfo(final String data) {
        String decodedString = decodeStringData(data);

        ObjectMapper objectMapper = new ObjectMapper();
        OrderPaymentInfoDTO orderPaymentInfoDTO = new OrderPaymentInfoDTO();

        try {
            orderPaymentInfoDTO = objectMapper.readValue(decodedString, OrderPaymentInfoDTO.class);
        } catch(IOException e) {
            e.printStackTrace();//TODO как-то обработать. Возможно в будущем написать свой кастомный конвертер
        }

        return orderPaymentInfoDTO;
    }

    private String decodeStringData(final String data) {
        return new String(Base64.getDecoder().decode(data));
    }
}
