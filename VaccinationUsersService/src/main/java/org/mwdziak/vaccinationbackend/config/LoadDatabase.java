package org.mwdziak.vaccinationbackend.config;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.mwdziak.vaccinationbackend.repository.VaccineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(VaccineRepository repository) {
        return args -> {
            repository.save(new Vaccine("Vaccine Name 1", 18, 2, 28));
            repository.save(new Vaccine("Vaccine Name 2", 21, 2, 30));
            repository.save(new Vaccine("Vaccine Name 3", 25, 1, 0));
        };
    }
}