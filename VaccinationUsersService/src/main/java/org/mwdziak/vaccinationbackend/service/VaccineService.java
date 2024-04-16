package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.mwdziak.vaccinationbackend.dto.VaccineDTO;
import org.mwdziak.vaccinationbackend.mapper.VaccineMapper;
import org.mwdziak.vaccinationbackend.repository.VaccineRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaccineService {
    private final VaccineRepository vaccineRepository;
    private final UserService userService;
    private final VaccineMapper vaccineMapper;
    private final Logger log = Logger.getLogger(VaccineService.class.getName());

    public List<VaccineDTO> findVaccinationsRecommendedForUser() {
        LocalDate today = LocalDate.now();
        Integer userAge = Math.toIntExact(ChronoUnit.MONTHS.between(userService.getCurrentUser().getDateOfBirth(), today));        log.info("User dob: " + userService.getCurrentUser().getDateOfBirth());
        log.info("User age: " + userAge);
        return vaccineRepository.findRecommendedVaccinesByAge(userAge).stream().map(vaccineMapper::toDto).toList();
    }

    public List<VaccineDTO> findAllVaccines() {
        return vaccineRepository.findAll().stream().map(vaccineMapper::toDto).toList();
    }
}
