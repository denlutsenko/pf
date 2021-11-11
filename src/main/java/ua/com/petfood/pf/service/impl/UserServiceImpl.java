package ua.com.petfood.pf.service.impl;

import static ua.com.petfood.pf.helper.constants.Constants.DEFAULT_TMP_PASSWORD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.helper.UserHelper;
import ua.com.petfood.pf.model.*;
import ua.com.petfood.pf.model.dto.UserDTO;
import ua.com.petfood.pf.repository.UserRepository;
import ua.com.petfood.pf.service.RoleService;
import ua.com.petfood.pf.service.UserService;

@Service
public class UserServiceImpl implements UserService {


    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserHelper userHelper;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository,
                           RoleService roleService, UserHelper userHelper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userHelper = userHelper;
    }

    public User createAnonUser() {
        String tempEmail = userHelper.createTmpEmailForAnonUser();
        Role role = roleService.getAnonRole();
        User anonUser = new User(role, tempEmail, passwordEncoder.encode(DEFAULT_TMP_PASSWORD));

        return userRepository.save(anonUser);
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));

    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = findByUsername(email).orElseThrow(() -> new NotFoundException("User not found"));
        user.setUserStatus(UserStatus.ACTIVE);
        user.setAuthorities(mapToGrantedAuthorities(user.getRole()));

        return user;
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(Role userRole) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(userRole.getName().toString()));
        return list;
    }

    public User createUser(UserDTO userDTO) {
        Role role = roleService.findRoleByName(RoleName.USER);
        User user = new User(role, userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }


    @Override
    public User updateUserFromDeliveryAddress(User userAnon, DeliveryAddress deliveryAddress) {
        userAnon.setEmail(deliveryAddress.getEmail());
        userAnon.setPhone(deliveryAddress.getPhoneNumber());
        userAnon.setFirstName(deliveryAddress.getFirstName());
        userAnon.setLastName(deliveryAddress.getLastName());
        userAnon.setEnabled(true);

        Role role = roleService.findRoleByName(RoleName.USER);
        userAnon.setRole(role);

       return userRepository.save(userAnon);
    }

    public Authentication getAuthentication(String email) {
        UserDetails userDetails = loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
