package ua.com.petfood.pf.helper;

import org.springframework.stereotype.Service;

import static ua.com.petfood.pf.helper.constants.Constants.MAIL_PATH;

@Service
public class UserHelper {



    public String createTmpEmailForAnonUser() {
        return String.valueOf(System.currentTimeMillis()).concat(MAIL_PATH);
    }
}
