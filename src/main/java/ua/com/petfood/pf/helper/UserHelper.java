package ua.com.petfood.pf.helper;

import org.springframework.stereotype.Service;

@Service
public class UserHelper {

    private static final String MAIL_PATH = "@mail.com";

    public String createTmpEmailForAnonUser() {
        return String.valueOf(System.currentTimeMillis()).concat(MAIL_PATH);
    }
}
