package org.example.sparata_calender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SparataCalenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SparataCalenderApplication.class, args);
    }

}
