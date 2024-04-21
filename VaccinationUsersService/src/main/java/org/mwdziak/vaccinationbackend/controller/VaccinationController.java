package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.service.VaccinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
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

    @GetMapping("/administered/user")
    public ResponseEntity<List<AdministeredVaccinationGetRequest>> getAdministeredVaccinationsForUser() {
        return ResponseEntity.ok(vaccinationService.getCurrentUsersAdministeredVaccinations());
    }

    @GetMapping("/schedule/user")
    public ResponseEntity<List<ScheduledVaccinationGetRequest>> getScheduledVaccinationsForUser() {
        return ResponseEntity.ok(vaccinationService.getCurrentUsersScheduledVaccinations());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/administered")
    public ResponseEntity<List<AdministeredVaccinationGetRequest>> getAdministeredVaccinations() {
        return ResponseEntity.ok(vaccinationService.getAllAdministeredVaccinations());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduledVaccinationGetRequest>> getScheduledVaccinations() {
        return ResponseEntity.ok(vaccinationService.getAllScheduledVaccinations());
    }
}
