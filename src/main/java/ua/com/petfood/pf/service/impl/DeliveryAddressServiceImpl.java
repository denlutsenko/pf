package ua.com.petfood.pf.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.DeliveryAddress;
import ua.com.petfood.pf.model.RoleName;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.dto.DeliveryAddressDTO;
import ua.com.petfood.pf.repository.DeliveryAddressRepository;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;
import ua.com.petfood.pf.service.DeliveryAddressService;
import ua.com.petfood.pf.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ua.com.petfood.pf.helper.constants.Constants.TOKEN;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleServiceImpl roleService;
    private final DeliveryAddressRepository deliveryAddressRepository;


    @Autowired
    public DeliveryAddressServiceImpl(UserService userService, JwtTokenProvider jwtTokenProvider, RoleServiceImpl roleService, DeliveryAddressRepository deliveryAddressRepository) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleService = roleService;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    @Override
    public Map<String, Object> saveDeliveryAddress(String bearerToken, DeliveryAddressDTO deliveryAddressDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<User> user = userService.findByUsername(deliveryAddressDTO.getEmail());

        if (user.isPresent()) {
            //   1.1 Если он есть,
            // -перегенерить токен на ЮЗЕР (вместо АНОН).
            //   -сохраняем деливери адресс в БД
            //  -апдейтим ордер (pf_orders), добавляем delivery_address_id  в ордер

            String token = jwtTokenProvider.createToken(user.get().getUsername(), roleService.findRoleByName(RoleName.USER));
            DeliveryAddress deliveryAddress = mapDeliveryAddressDTOToDeliveryAddressEntity(deliveryAddressDTO);
            deliveryAddress.setUserId(user.get().getId());
            deliveryAddressRepository.save(deliveryAddress);

            responseMap.put(TOKEN,token);
            return responseMap;


        } else {
//           1.2 Если чувака нет (новый пользователь)
//            -берем существующего чувака
//           -обновляем ему емейл
//           -перегенерить токен на ЮЗЕР (вместо АНОН).
//           -сохраняем деливери адресс в БД
//           -апдейтим ордер (pf_orders), добавляем delivery_address_id  в ордер
                //TODO delivery address part2

        }


        return null;
    }


    @Override
    public List<DeliveryAddress> getDeliveryAddressesForUser(String email) {
        User user = userService.findByUsername(email).orElseThrow(() -> new NotFoundException("User not found"));

        return deliveryAddressRepository.findAllByUserId(user.getId());
    }

    private DeliveryAddress mapDeliveryAddressDTOToDeliveryAddressEntity(DeliveryAddressDTO deliveryAddressDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(deliveryAddressDTO, DeliveryAddress.class);
    }


}
