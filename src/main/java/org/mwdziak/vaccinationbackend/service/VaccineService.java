package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
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

    public List<Vaccine> findVaccinationsRecommendedForUser() {
        LocalDate today = LocalDate.now();
        Integer userAge = Period.between(userService.getCurrentUser().getDateOfBirth(), today).getYears();
        return vaccineRepository.findRecommendedVaccinesByAge(userAge);
    }
}
