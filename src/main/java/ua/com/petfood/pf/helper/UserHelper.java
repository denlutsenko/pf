package ua.com.petfood.pf.helper;

import static ua.com.petfood.pf.helper.constants.Constants.MAIL_PATH;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.petfood.pf.exception.BadRequestException;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;

@Component
public class UserHelper {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserHelper(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String getUserEmailFromToken(String bearerToken) {
        String token = jwtTokenProvider.resolveToken(bearerToken);

        return Optional.ofNullable(jwtTokenProvider.getUsername(token))
                .orElseThrow(() -> new NotFoundException("user email not found"));
    }

    public void validateEmailOwner(final String token, final String emailFromRequest){
        String emailFromToken = getUserEmailFromToken(token);
        if(!emailFromToken.equalsIgnoreCase(emailFromRequest)){
            throw new BadRequestException("User email corrupted");
        }
    }

    public String createTmpEmailForAnonUser() {
        return String.valueOf(System.currentTimeMillis()).concat(MAIL_PATH);
    }
}
