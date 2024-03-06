package org.mwdziak.vaccinationbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VaccinationBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaccinationBackendApplication.class, args);
    }

}
