package ua.com.petfood.pf.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.model.Role;
import ua.com.petfood.pf.model.UserStatus;

import java.util.ArrayList;
import java.util.List;

public final class JwtEmployeeFactory {

    public JwtEmployeeFactory() {
    }

    public static JwtEmployee create(User user) {
        return new JwtEmployee(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()),
                user.getUserStatus().equals(UserStatus.ACTIVE),
                user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role userRole) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(userRole.getName().toString()));
        return list;
    }
}
