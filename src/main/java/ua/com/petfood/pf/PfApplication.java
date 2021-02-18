package ua.com.petfood.pf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class PfApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfApplication.class, args);
    }

}
