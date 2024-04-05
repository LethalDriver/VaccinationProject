package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.service.VaccinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vaccination")
public class VaccinationController {
    private final VaccinationService vaccinationService;
    @PostMapping("/schedule")
    public ResponseEntity<Void> scheduleVaccination(@RequestBody ScheduledVaccinationPostRequest scheduledVaccinationPostRequest) {
        vaccinationService.scheduleVaccination(scheduledVaccinationPostRequest);
        return ResponseEntity.status(201).build();
    }
    @PutMapping("/schedule/{id}")
    public ResponseEntity<Void> rescheduleVaccination(@RequestBody ScheduledVaccinationPostRequest scheduledVaccinationPostRequest,
                                                      @PathVariable Long id) {
        vaccinationService.editScheduledVaccination(scheduledVaccinationPostRequest, id);
        return ResponseEntity.status(204).build();
    }
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Void> cancelVaccination(@PathVariable Long id) {
        vaccinationService.deleteScheduledVaccination(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/administered")
    public ResponseEntity<Void> administerVaccination(@RequestBody AdministeredVaccinationPostRequest administeredVaccinationPostRequest) {
        vaccinationService.addAdministeredVaccination(administeredVaccinationPostRequest);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/administered/{id}")
    public ResponseEntity<Void> editAdministeredVaccination(@RequestBody AdministeredVaccinationPostRequest administeredVaccinationPostRequest,
                                                            @PathVariable Long id) {
        vaccinationService.editAdministeredVaccination(administeredVaccinationPostRequest, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/administered/{id}")
    public ResponseEntity<Void> deleteAdministeredVaccination(@PathVariable Long id) {
        vaccinationService.deleteAdministeredVaccination(id);
        return ResponseEntity.status(204).build();
    }
}
