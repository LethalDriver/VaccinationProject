package org.mwdziak.vaccinationbackend.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AdministeredVaccinationMapperTests {
    @Autowired
    private AdministeredVaccinationMapper administeredVaccinationMapper;
    @Autowired
    private VaccineMapper vaccineMapper;

    private static final String DATE_TIME_STRING = "2021-01-01 12:00";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private AdministeredVaccination administeredVaccination;
    private AdministeredVaccinationPostRequest administeredVaccinationPostRequest;

    @BeforeEach
    public void setUp() {
        administeredVaccination = new AdministeredVaccination();
        administeredVaccination.setVaccine(new Vaccine());
        administeredVaccination.setDateTime(LocalDateTime.parse(DATE_TIME_STRING, FORMATTER));

        administeredVaccinationPostRequest = administeredVaccinationMapper.toDto(administeredVaccination);
    }

    @Test
    public void shouldMapAdministeredVaccinationToAdministeredVaccinationDTO() {
        assertAll(
                () -> assertEquals(administeredVaccination.getVaccine().getId(), administeredVaccinationPostRequest.vaccineId()),
                () -> assertEquals(administeredVaccination.getDateTime(), LocalDateTime.parse(administeredVaccinationPostRequest.dateTime(), FORMATTER))
        );
    }

    @Test
    public void shouldMapAdministeredVaccinationDTOToAdministeredVaccination() {
        AdministeredVaccination mappedAdministeredVaccination = administeredVaccinationMapper.toEntity(administeredVaccinationPostRequest);

        assertAll(
                () -> assertEquals(administeredVaccinationPostRequest.vaccineId(), mappedAdministeredVaccination.getVaccine().getId()),
                () -> assertEquals(administeredVaccinationPostRequest.dateTime(), mappedAdministeredVaccination.getDateTime().format(FORMATTER))
        );
    }
}