package org.mwdziak.vaccinationbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.*;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationPostRequest;
import org.mwdziak.vaccinationbackend.exception.NotificationTokenNotSetException;
import org.mwdziak.vaccinationbackend.mapper.AdministeredVaccinationMapper;
import org.mwdziak.vaccinationbackend.mapper.ScheduledVaccinationMapper;
import org.mwdziak.vaccinationbackend.repository.AdministeredVaccinationRepository;
import org.mwdziak.vaccinationbackend.repository.ScheduledVaccinationRepository;
import org.mwdziak.vaccinationbackend.repository.VaccineRepository;
import org.springframework.stereotype.Service;

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
    public void editScheduledVaccination(ScheduledVaccinationPostRequest scheduledVaccinationPostRequest) {
        throwIfNoNotificationToken(scheduledVaccinationPostRequest);

        ScheduledVaccination updatedVaccination = scheduledVaccinationMapper.toEntity(scheduledVaccinationPostRequest);

        ScheduledVaccination existingVaccination = scheduledVaccinationRepository.findById(scheduledVaccinationPostRequest.id())
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
        findAndSetVaccine(administeredVaccinationPostRequest.id(), administeredVaccination);
        administeredVaccinationRepository.save(administeredVaccination);
    }
    public void deleteAdministeredVaccination(Long id) {
        administeredVaccinationRepository.deleteById(id);
    }

    public void editAdministeredVaccination(AdministeredVaccinationPostRequest administeredVaccinationPostRequest) {
        AdministeredVaccination updatedVaccination = administeredVaccinationMapper.toEntity(administeredVaccinationPostRequest);

        AdministeredVaccination existingVaccination = administeredVaccinationRepository.findById(administeredVaccinationPostRequest.id())
                .orElseThrow(() -> new EntityNotFoundException("AdministeredVaccination not found"));

        existingVaccination.setDateTime(updatedVaccination.getDateTime());
        findAndSetVaccine(administeredVaccinationPostRequest.id(), existingVaccination);

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
}
