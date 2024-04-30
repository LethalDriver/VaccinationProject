package org.mwdziak.vaccinationbackend.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mwdziak.vaccinationbackend.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
                .build();

        vaccineRepository.save(vaccine1);

        Vaccine vaccine2 = Vaccine.builder()
                .name("testVaccine2")
                .build();

        vaccineRepository.save(vaccine2);
    }

    @Test
    public void administeredRepositoryShouldReturnAllVaccinationsForUser() {
        AdministeredVaccination administeredVaccination1 = AdministeredVaccination.builder()
                .user(user)
                .vaccine(vaccineRepository.findByName("testVaccine").get())
                .dateTime(ZonedDateTime.now().minusDays(10))
                .build();

        AdministeredVaccination administeredVaccination2 = AdministeredVaccination.builder()
                .user(user)
                .vaccine(vaccineRepository.findByName("testVaccine2").get())
                .dateTime(ZonedDateTime.now().minusDays(5))
                .build();

        administeredVaccinationRepository.save(administeredVaccination1);
        administeredVaccinationRepository.save(administeredVaccination2);

        List<AdministeredVaccination> administeredVaccinations = administeredVaccinationRepository.findAllByUser_Id(user.getId());
        assertEquals(2, administeredVaccinations.size());
    }
    @Test
    public void scheduledRepositoryShouldReturnAllOverdueVaccinationsForUser() {
        ScheduledVaccination scheduledVaccination1 = ScheduledVaccination.builder()
                .user(user)
                .vaccine(vaccineRepository.findByName("testVaccine").get())
                .dateTime(ZonedDateTime.now().plusDays(10))
                .build();

        ScheduledVaccination scheduledVaccination2 = ScheduledVaccination.builder()
                .user(user)
                .vaccine(vaccineRepository.findByName("testVaccine2").get())
                .dateTime(ZonedDateTime.now().minusDays(5))
                .build();

        scheduledVaccinationRepository.save(scheduledVaccination1);
        scheduledVaccinationRepository.save(scheduledVaccination2);

        List<ScheduledVaccination> scheduledVaccinations = scheduledVaccinationRepository.findAllByDateTimeAfterAndUserId(
                ZonedDateTime.now(), user.getId());
        assertEquals(1, scheduledVaccinations.size());
    }
}
