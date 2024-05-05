package org.mwdziak.vaccinationbackend.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.service.VaccinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vaccination/administered")
public class AdministeredVaccinationController {
    private final VaccinationService vaccinationService;

    @PostMapping
    public ResponseEntity<Void> administerVaccination(@RequestBody AdministeredVaccinationPostRequest administeredVaccinationPostRequest) {
        vaccinationService.addAdministeredVaccination(administeredVaccinationPostRequest);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> editAdministeredVaccination(@RequestBody AdministeredVaccinationPostRequest administeredVaccinationPostRequest,
                                                            @PathVariable Long id) {
        vaccinationService.editAdministeredVaccination(administeredVaccinationPostRequest, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministeredVaccination(@PathVariable Long id) {
        vaccinationService.deleteAdministeredVaccination(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<AdministeredVaccinationGetRequest>> getAdministeredVaccinationsForUser() {
        return ResponseEntity.ok(vaccinationService.getCurrentUsersAdministeredVaccinations());
    }

    @GetMapping("/user")
    public ResponseEntity<List<ScheduledVaccinationGetRequest>> getScheduledVaccinationsForUser() {
        return ResponseEntity.ok(vaccinationService.getCurrentUsersScheduledVaccinations());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<AdministeredVaccinationGetRequest>> getAdministeredVaccinations() {
        return ResponseEntity.ok(vaccinationService.getAllAdministeredVaccinations());
    }
}
