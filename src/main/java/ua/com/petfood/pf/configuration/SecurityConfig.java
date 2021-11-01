package ua.com.petfood.pf.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ua.com.petfood.pf.repository.JwtBlackListRepository;
import ua.com.petfood.pf.security.jwt.JwtConfigurer;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;
import ua.com.petfood.pf.service.UserService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtBlackListRepository jwtBlackListRepository;
    private final UserService userService;
    //Any request are permitted
    private static final String NO_TOKEN_ENDPOINT_INDEX = "/";
    private static final String NO_TOKEN_ENDPOINT_API = "/api/**";
    private static final String NO_TOKEN_ENDPOINT_IMG = "/img/**";

    //Only ANONYMOUS role requests are permitted
    private static final String ANONYMOUS_ENDPOINT = "/anon/**";

    //Only USER role requests are permitted
    private static final String USER_ENDPOINT = "/user/**";

    //Only ADMIN role requests are permitted
    private static final String ADMIN_ENDPOINT = "/admin/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, JwtBlackListRepository jwtBlackListRepository, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtBlackListRepository = jwtBlackListRepository;
        this.userService = userService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler(jwtTokenProvider, jwtBlackListRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(NO_TOKEN_ENDPOINT_INDEX).permitAll()
                .antMatchers(NO_TOKEN_ENDPOINT_API).permitAll()
                .antMatchers(NO_TOKEN_ENDPOINT_IMG).permitAll()
                .antMatchers(ANONYMOUS_ENDPOINT).hasAuthority("ANONYMOUS")
                .antMatchers(USER_ENDPOINT).hasAuthority("USER")
                .antMatchers(ADMIN_ENDPOINT).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(userService, jwtTokenProvider))
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .invalidateHttpSession(true)
                .deleteCookies("YIICSRFTOKEN", "PHPSESSID")
                .logoutSuccessHandler(logoutSuccessHandler());
    }
}
