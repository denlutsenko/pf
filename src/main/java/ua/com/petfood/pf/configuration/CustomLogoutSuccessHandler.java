package ua.com.petfood.pf.configuration;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import ua.com.petfood.pf.model.JwtBlacklist;
import ua.com.petfood.pf.repository.JwtBlackListRepository;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static ua.com.petfood.pf.helper.constants.Constants.TOKEN;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtBlackListRepository jwtBlackListRepository;

    @Autowired
    public CustomLogoutSuccessHandler(JwtTokenProvider jwtTokenProvider, JwtBlackListRepository jwtBlackListRepository) {

        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtBlackListRepository = jwtBlackListRepository;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication)
            throws IOException, ServletException {

        Optional<String> token = Optional.ofNullable(jwtTokenProvider.resolveToken(httpServletRequest));
        if (token.isPresent()) {
            JwtBlacklist jwtBlacklist = new JwtBlacklist();
            jwtBlacklist.setToken(token.get());
            jwtBlacklist.setExpired(jwtTokenProvider.getExpirationDate(token.get()));
            jwtBlackListRepository.save(jwtBlacklist);
        }
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        JSONObject json = new JSONObject();
        json.put(TOKEN, "");
        out.print(json);
    }
}
