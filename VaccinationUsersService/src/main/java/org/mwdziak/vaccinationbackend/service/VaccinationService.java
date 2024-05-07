package org.mwdziak.vaccinationbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.*;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.exception.NotificationTokenNotSetException;
import org.mwdziak.vaccinationbackend.mapper.AdministeredVaccinationMapper;
import org.mwdziak.vaccinationbackend.mapper.ScheduledVaccinationMapper;
import org.mwdziak.vaccinationbackend.repository.AdministeredVaccinationRepository;
import org.mwdziak.vaccinationbackend.repository.ScheduledVaccinationRepository;
import org.mwdziak.vaccinationbackend.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationService {
    private final ScheduledVaccinationMapper scheduledVaccinationMapper;
    private final AdministeredVaccinationMapper administeredVaccinationMapper;
    private final UserService userService;
    private final ScheduledVaccinationRepository scheduledVaccinationRepository;
    private final AdministeredVaccinationRepository administeredVaccinationRepository;
    private final VaccineRepository vaccineRepository;
    public void scheduleVaccination(ScheduledVaccinationPostRequest scheduledVaccinationPostRequest) {
        throwIfNoNotificationToken(scheduledVaccinationPostRequest);

        ScheduledVaccination scheduledVaccination = scheduledVaccinationMapper.toEntity(scheduledVaccinationPostRequest);

        User currentUser = userService.getCurrentUser();
        scheduledVaccination.setUser(currentUser);
        findAndSetVaccine(scheduledVaccinationPostRequest.vaccineId(), scheduledVaccination);

        scheduledVaccinationRepository.save(scheduledVaccination);
    }
    public void deleteScheduledVaccination(Long id) {
        scheduledVaccinationRepository.deleteById(id);
    }
    public void editScheduledVaccination(ScheduledVaccinationPostRequest scheduledVaccinationPostRequest, Long id) {
        throwIfNoNotificationToken(scheduledVaccinationPostRequest);

        ScheduledVaccination updatedVaccination = scheduledVaccinationMapper.toEntity(scheduledVaccinationPostRequest);

        ScheduledVaccination existingVaccination = scheduledVaccinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduledVaccination not found"));


        existingVaccination.setDateTime(updatedVaccination.getDateTime());
        existingVaccination.setDoseNumber(updatedVaccination.getDoseNumber());
        existingVaccination.setReminders(updatedVaccination.getReminders());
        findAndSetVaccine(scheduledVaccinationPostRequest.vaccineId(), existingVaccination);

        scheduledVaccinationRepository.save(existingVaccination);
    }
    public void addAdministeredVaccination(AdministeredVaccinationPostRequest administeredVaccinationPostRequest) {
        AdministeredVaccination administeredVaccination = administeredVaccinationMapper.toEntity(administeredVaccinationPostRequest);
        User currentUser = userService.getCurrentUser();
        administeredVaccination.setUser(currentUser);
        findAndSetVaccine(administeredVaccinationPostRequest.vaccineId(), administeredVaccination);

        administeredVaccinationRepository.save(administeredVaccination);
    }
    public void deleteAdministeredVaccination(Long id) {
        administeredVaccinationRepository.deleteById(id);
    }

    public void editAdministeredVaccination(AdministeredVaccinationPostRequest administeredVaccinationPostRequest, Long id) {
        AdministeredVaccination updatedVaccination = administeredVaccinationMapper.toEntity(administeredVaccinationPostRequest);

        AdministeredVaccination existingVaccination = administeredVaccinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AdministeredVaccination not found"));

        existingVaccination.setDateTime(updatedVaccination.getDateTime());
        existingVaccination.setDoseNumber(updatedVaccination.getDoseNumber());
        findAndSetVaccine(administeredVaccinationPostRequest.vaccineId(), existingVaccination);

        administeredVaccinationRepository.save(existingVaccination);
    }

    public <T extends Vaccination> void findAndSetVaccine(Long vaccineId, T vaccination) {
        var vaccine = vaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));
        vaccination.setVaccine(vaccine);
    }

    private void throwIfNoNotificationToken(ScheduledVaccinationPostRequest scheduledVaccinationPostRequest) {
        if (!scheduledVaccinationPostRequest.reminders().isEmpty()) {
            if (userService.getCurrentUser().getNotificationToken() == null) {
                throw new NotificationTokenNotSetException("User has no notification token");
            }
        }
    }

    public List<AdministeredVaccinationGetRequest> getCurrentUsersAdministeredVaccinations() {
        var currentUserId = userService.getCurrentUser().getId();
        return administeredVaccinationRepository.findAllByUserId(currentUserId).stream().map(administeredVaccinationMapper::toDto).toList();
    }

    public List<ScheduledVaccinationGetRequest> getCurrentUsersScheduledVaccinations() {
        var currentUserId = userService.getCurrentUser().getId();
        return scheduledVaccinationRepository.findAllByUserId(currentUserId).stream().map(scheduledVaccinationMapper::toDto).toList();
    }

    public List<AdministeredVaccinationGetRequest> getAllAdministeredVaccinations() {
        return administeredVaccinationRepository.findAll().stream().map(administeredVaccinationMapper::toDto).toList();
    }

    public List<ScheduledVaccinationGetRequest> getAllScheduledVaccinations() {
        return scheduledVaccinationRepository.findAll().stream().map(scheduledVaccinationMapper::toDto).toList();
    }

    public List<ScheduledVaccinationGetRequest> getAllScheduledVaccinationsForConfirmationForCurrentUser() {
        var vaccinations = scheduledVaccinationRepository.findAllByDateTimeBeforeAndUserId(ZonedDateTime.now(), userService.getCurrentUser().getId());
        return vaccinations.stream().map(scheduledVaccinationMapper::toDto).toList();
    }

    public AdministeredVaccinationGetRequest confirmScheduledVaccination(Long id) {
        ScheduledVaccination scheduledVaccination = scheduledVaccinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduledVaccination not found"));
        AdministeredVaccination administeredVaccination = AdministeredVaccination.builder()
                .dateTime(scheduledVaccination.getDateTime())
                .doseNumber(scheduledVaccination.getDoseNumber())
                .user(scheduledVaccination.getUser())
                .vaccine(scheduledVaccination.getVaccine())
                .build();
        administeredVaccinationRepository.save(administeredVaccination);
        scheduledVaccinationRepository.deleteById(id);
        return administeredVaccinationMapper.toDto(administeredVaccination);
    }

    public List<ScheduledVaccinationGetRequest> getScheduledVaccinationsForUser(Long id) {
        return scheduledVaccinationRepository.findAllByUserId(id).stream().map(scheduledVaccinationMapper::toDto).toList();
    }
}
