package ua.com.petfood.pf.service;

import java.util.Map;

import ua.com.petfood.pf.model.dto.OrderDTO;

public interface CheckoutService {

    Map<String, Object> checkoutAndSaveOrder(final String bearerToken, final OrderDTO orderDTO);

}
