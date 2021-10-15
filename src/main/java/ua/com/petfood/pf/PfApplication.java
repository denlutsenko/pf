package ua.com.petfood.pf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PfApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfApplication.class, args);
    }

}
