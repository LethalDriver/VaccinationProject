package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.VaccineDTO;
import org.mwdziak.vaccinationbackend.service.VaccineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaccine")
@RequiredArgsConstructor
public class VaccineController {
    private final VaccineService vaccineService;
    @GetMapping
    public ResponseEntity<List<VaccineDTO>> getRecommendedVaccines() {
        return ResponseEntity.of(Optional.ofNullable(vaccineService.findVaccinationsRecommendedForUser()));
    }
}
