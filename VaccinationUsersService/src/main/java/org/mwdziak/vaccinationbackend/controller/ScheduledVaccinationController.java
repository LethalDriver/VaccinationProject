package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.service.VaccinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vaccination/scheduled")
public class ScheduledVaccinationController {
    private final VaccinationService vaccinationService;
    @PostMapping
    public ResponseEntity<Void> scheduleVaccination(@RequestBody ScheduledVaccinationPostRequest scheduledVaccinationPostRequest) {
        vaccinationService.scheduleVaccination(scheduledVaccinationPostRequest);
        return ResponseEntity.status(201).build();
    }
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> rescheduleVaccination(@RequestBody ScheduledVaccinationPostRequest scheduledVaccinationPostRequest,
                                                      @PathVariable Long id) {
        vaccinationService.editScheduledVaccination(scheduledVaccinationPostRequest, id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledVaccinationGetRequest> getScheduledVaccination(@PathVariable Long id) {
        return ResponseEntity.ok(vaccinationService.getScheduledVaccination(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelVaccination(@PathVariable Long id) {
        vaccinationService.deleteScheduledVaccination(id);
        return ResponseEntity.status(204).build();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<ScheduledVaccinationGetRequest>> getScheduledVaccinations() {
        return ResponseEntity.ok(vaccinationService.getAllScheduledVaccinations());
    }

    @GetMapping("/user")
    public ResponseEntity<List<ScheduledVaccinationGetRequest>> getScheduledVaccinationsForUser() {
        return ResponseEntity.ok(vaccinationService.getCurrentUsersScheduledVaccinations());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ScheduledVaccinationGetRequest>> getScheduledVaccinationsForUser(@PathVariable Long id) {
        return ResponseEntity.ok(vaccinationService.getScheduledVaccinationsForUser(id));
    }

    @GetMapping("/confirmation")
    public ResponseEntity<List<ScheduledVaccinationGetRequest>> confirmScheduledVaccination() {
        return ResponseEntity.ok(vaccinationService.getAllScheduledVaccinationsForConfirmationForCurrentUser());
    }

    @PatchMapping("/confirmation/{id}")
    public ResponseEntity<AdministeredVaccinationGetRequest> confirmScheduledVaccination(@PathVariable Long id) {
        return ResponseEntity.ok(vaccinationService.confirmScheduledVaccination(id));
    }
}
