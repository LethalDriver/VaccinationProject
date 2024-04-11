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
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@email.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@email.com");
                admin.setPassword(passwordEncoder.encode("admin"));
                userRepository.save(admin);
            }
        };
    }
}