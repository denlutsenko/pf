package ua.com.petfood.pf.configuration;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import ua.com.petfood.pf.model.User;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ua.com.petfood.pf.helper.constants.Constants.EMAIL;
import static ua.com.petfood.pf.helper.constants.Constants.TOKEN;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public CustomLogoutSuccessHandler(JwtTokenProvider jwtTokenProvider) {

        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication)
            throws IOException, ServletException {

        User user = jwtTokenProvider.getUserService().createAnonUser();
        String email = user.getEmail();
        String token = jwtTokenProvider.createToken(email, user.getRole());

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        JSONObject json = new JSONObject();
        json.put(EMAIL, email);
        json.put(TOKEN, token);
        out.print(json);
    }
}
