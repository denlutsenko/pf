package ua.com.petfood.pf.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ua.com.petfood.pf.security.jwt.JwtConfigurer;
import ua.com.petfood.pf.security.jwt.JwtTokenProvider;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    //Any request are permitted
    private static final String NO_TOKEN_ENDPOINT = "/";
    private static final String NO_TOKEN_ENDPOINT1 = "/user/initialUser/create";
    //Only ANONYMOUS role requests are permitted
    private static final String ANONYMOUS_USER_ENDPOINT = "/user/testCallAnonOnly";
    private static final String ANONYMOUS_ENDPOINT = "/anon/**";
    //Only USER role requests are permitted
    private static final String USER_ENDPOINT = "/api/**";
    //Only ADMIN role requests are permitted
    private static final String ADMIN_ENDPOINT = "/admin/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(NO_TOKEN_ENDPOINT).permitAll()
                .antMatchers(NO_TOKEN_ENDPOINT1).permitAll()
                .antMatchers(ANONYMOUS_USER_ENDPOINT).hasRole("ANONYMOUS")
                .antMatchers(ANONYMOUS_ENDPOINT).hasRole("ANONYMOUS")
                .antMatchers(USER_ENDPOINT).hasRole("USER")
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
