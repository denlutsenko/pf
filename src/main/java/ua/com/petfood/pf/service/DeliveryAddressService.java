package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.DeliveryAddress;
import ua.com.petfood.pf.model.dto.DeliveryAddressDTO;

import java.util.List;
import java.util.Map;


public interface DeliveryAddressService {
    Map<String, Object> saveDeliveryAddress(String bearerToken, DeliveryAddressDTO deliveryAddressDTO);

    List<DeliveryAddress> getDeliveryAddressesForUser(String userId);
}
