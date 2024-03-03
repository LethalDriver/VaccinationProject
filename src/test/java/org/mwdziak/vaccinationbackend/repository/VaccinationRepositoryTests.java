package org.mwdziak.vaccinationbackend.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;
import org.mwdziak.vaccinationbackend.domain.User;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VaccinationRepositoryTests extends RepositoryTests{
    @Autowired
    private AdministeredVaccinationRepository administeredVaccinationRepository;
    @Autowired
    private ScheduledVaccinationRepository scheduledVaccinationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VaccineRepository vaccineRepository;
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

        userRepository.save(user);

        Vaccine vaccine1 = Vaccine.builder()
                .name("testVaccine")
                .doses(1)
                .build();

        vaccineRepository.save(vaccine1);

        Vaccine vaccine2 = Vaccine.builder()
                .name("testVaccine2")
                .doses(2)
                .build();

        vaccineRepository.save(vaccine2);
    }

    @Test
    public void administeredRepositoryShouldReturnAllVaccinationsForUser() {
        var administeredVaccination1 = AdministeredVaccination.builder()
                .user(user)
                .vaccine(vaccineRepository.findByName("testVaccine").get())
                .date(LocalDateTime.now().minusDays(5))
                .nextDoseDate(LocalDateTime.now().plusDays(30))
                .build();

        var administeredVaccination2 = AdministeredVaccination.builder()
                .user(user)
                .vaccine(vaccineRepository.findByName("testVaccine2").get())
                .date(LocalDateTime.now().minusDays(20))
                .nextDoseDate(LocalDateTime.now().plusDays(10))
                .build();

        administeredVaccinationRepository.save(administeredVaccination1);
        administeredVaccinationRepository.save(administeredVaccination2);

        List<AdministeredVaccination> administeredVaccinations = administeredVaccinationRepository.findAllByUser_Id(user.getId());

        assertEquals(2, administeredVaccinations.size());
    }
    @Test
    public void scheduledRepositoryShouldReturnAllVaccinationsForUser() {

    }
}
