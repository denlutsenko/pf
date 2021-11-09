package ua.com.petfood.pf.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceHandlerConfig implements WebMvcConfigurer {

//    private final String WINDOWS_FILE_PATH = "file:C:/img/";
    private final String UNIX_FILE_PATH = "file:/content/img/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**").addResourceLocations(WINDOWS_FILE_PATH);
                registry.addResourceHandler("/img/**").addResourceLocations(UNIX_FILE_PATH);
    }
}
