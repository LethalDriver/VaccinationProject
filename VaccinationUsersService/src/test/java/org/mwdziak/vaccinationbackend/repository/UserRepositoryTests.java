package org.mwdziak.vaccinationbackend.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mwdziak.vaccinationbackend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;



public class UserRepositoryTests extends RepositoryTests {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .password("test")
                .email("test")
                .firstName("test")
                .lastName("test")
                .dateOfBirth(LocalDate.now())
                .role(User.Role.USER)
                .build();
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void should_save_user() {
        var saved = userRepository.save(user);
        Assertions.assertNotNull(saved.getId());
    }

    @Test
    public void should_find_user_by_email() {
        userRepository.save(user);
        var found = userRepository.findByEmail(user.getEmail());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(user.getEmail(), found.get().getEmail());
    }
}