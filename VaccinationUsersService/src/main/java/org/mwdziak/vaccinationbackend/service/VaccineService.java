package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.mwdziak.vaccinationbackend.dto.VaccineDTO;
import org.mwdziak.vaccinationbackend.mapper.VaccineMapper;
import org.mwdziak.vaccinationbackend.repository.VaccineRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineService {
    private final VaccineRepository vaccineRepository;
    private final UserService userService;
    private final VaccineMapper vaccineMapper;

    public List<VaccineDTO> findVaccinationsRecommendedForUser() {
        LocalDate today = LocalDate.now();
        Integer userAge = Period.between(userService.getCurrentUser().getDateOfBirth(), today).getYears();
        return vaccineRepository.findRecommendedVaccinesByAge(userAge).stream().map(vaccineMapper::toDto).toList();
    }

    public List<VaccineDTO> findAllVaccines() {
        return vaccineRepository.findAll().stream().map(vaccineMapper::toDto).toList();
    }
}
