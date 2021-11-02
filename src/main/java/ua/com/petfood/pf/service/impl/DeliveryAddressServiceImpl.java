package ua.com.petfood.pf.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.model.DeliveryAddress;
import ua.com.petfood.pf.model.RoleName;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.DeliveryAddressDTO;
import ua.com.petfood.pf.repository.DeliveryAddressRepository;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;
import ua.com.petfood.pf.service.DeliveryAddressService;
import ua.com.petfood.pf.service.OrderService;
import ua.com.petfood.pf.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ua.com.petfood.pf.helper.constants.Constants.EMAIL;
import static ua.com.petfood.pf.helper.constants.Constants.TOKEN;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleServiceImpl roleService;
    private final OrderService orderService;
    private final DeliveryAddressRepository deliveryAddressRepository;


    @Autowired
    public DeliveryAddressServiceImpl(UserService userService, JwtTokenProvider jwtTokenProvider, RoleServiceImpl roleService, OrderService orderService, DeliveryAddressRepository deliveryAddressRepository) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleService = roleService;
        this.orderService = orderService;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    //@Transactional ???
    @Override
    public Map<String, Object> saveDeliveryAddress(String bearerToken, DeliveryAddressDTO deliveryAddressDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<User> user = userService.findByUsername(deliveryAddressDTO.getEmail());
        DeliveryAddress deliveryAddress = mapDeliveryAddressDTOToDeliveryAddressEntity(deliveryAddressDTO);

        if (user.isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Optional<User> userAnon = userService.findByUsername(email);
            userAnon.ifPresent(value -> userService.updateUserFromDeliveryAddress(value, deliveryAddress));
        }
        String token = jwtTokenProvider.createToken(deliveryAddress.getEmail(), roleService.findRoleByName(RoleName.USER));
        deliveryAddressRepository.save(deliveryAddress);
        orderService.updateOrderDeliveryAddress(deliveryAddress);
        responseMap.put(EMAIL, deliveryAddress.getEmail());
        responseMap.put(TOKEN, token);
        return responseMap;
    }

    private DeliveryAddress mapDeliveryAddressDTOToDeliveryAddressEntity(DeliveryAddressDTO deliveryAddressDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(deliveryAddressDTO, DeliveryAddress.class);
    }


}
