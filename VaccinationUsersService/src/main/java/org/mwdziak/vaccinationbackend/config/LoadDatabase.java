package org.mwdziak.vaccinationbackend.config;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.RemainingDose;
import org.mwdziak.vaccinationbackend.domain.User;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.mwdziak.vaccinationbackend.repository.UserRepository;
import org.mwdziak.vaccinationbackend.repository.VaccineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(VaccineRepository repository, UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            repository.save(new Vaccine("Pfizer", 12,
                    List.of(new RemainingDose(1, 2), new RemainingDose(2,4))));
            repository.save(new Vaccine("Moderna", 12,
                    List.of(new RemainingDose(1, 2), new RemainingDose(2,4))));
            userRepository.save(User.builder()
                    .email("admin@email.com")
                    .password(passwordEncoder.encode("password"))
                    .build());
        };
    }
}