package ua.com.petfood.pf.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceHandlerConfig implements WebMvcConfigurer {

    private String myExternalFilePathWindows = "file:C:/images/";
    private String myExternalFilePathLinux = "file:/images/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations(myExternalFilePathWindows);
    }
}
