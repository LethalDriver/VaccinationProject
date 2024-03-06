package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.ScheduledVaccinationDTO;
import org.mwdziak.vaccinationbackend.service.VaccinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vaccination")
public class VaccinationController {
    private final VaccinationService vaccinationService;
    @PostMapping("/schedule")
    public ResponseEntity<Void> scheduleVaccination(@RequestBody ScheduledVaccinationDTO scheduledVaccinationDTO) {
        vaccinationService.scheduleVaccination(scheduledVaccinationDTO);
        return ResponseEntity.status(201).build();
    }
    @PatchMapping("/schedule")
    public ResponseEntity<Void> rescheduleVaccination(@RequestBody ScheduledVaccinationDTO scheduledVaccinationDTO) {
        vaccinationService.editScheduledVaccination(scheduledVaccinationDTO);
        return ResponseEntity.status(204).build();
    }
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Void> cancelVaccination(@PathVariable Long id) {
        vaccinationService.deleteScheduledVaccination(id);
        return ResponseEntity.status(204).build();
    }
}
