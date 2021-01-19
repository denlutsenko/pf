package ua.com.petfood.pf.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.helper.UserHelper;
import ua.com.petfood.pf.model.Role;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.repository.UserRepository;
import ua.com.petfood.pf.service.RoleService;
import ua.com.petfood.pf.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_TMP_PASSWORD = "PASSWORD";

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserHelper userHelper;

    @Autowired
    public UserServiceImpl(
            BCryptPasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleService roleService,
            UserHelper userHelper) {
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
    public User findByUsername(String email) {
     User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new NotFoundException("User with username: " + email + " not found");
        }

        return user;
    }
}
