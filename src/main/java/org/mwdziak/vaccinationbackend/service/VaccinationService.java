package org.mwdziak.vaccinationbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.*;
import org.mwdziak.vaccinationbackend.dto.AdministeredVaccinationDTO;
import org.mwdziak.vaccinationbackend.dto.ScheduledVaccinationDTO;
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
    public void scheduleVaccination(ScheduledVaccinationDTO scheduledVaccinationDTO) {
        ScheduledVaccination scheduledVaccination = scheduledVaccinationMapper.toEntity(scheduledVaccinationDTO);
        User currentUser = userService.getCurrentUser();
        scheduledVaccination.setUser(currentUser);
        findAndSetVaccine(scheduledVaccinationDTO.vaccineId(), scheduledVaccination);
        scheduledVaccinationRepository.save(scheduledVaccination);
    }
    public void deleteScheduledVaccination(Long id) {
        scheduledVaccinationRepository.deleteById(id);
    }
    public void editScheduledVaccination(ScheduledVaccinationDTO scheduledVaccinationDTO) {
        ScheduledVaccination updatedVaccination = scheduledVaccinationMapper.toEntity(scheduledVaccinationDTO);

        ScheduledVaccination existingVaccination = scheduledVaccinationRepository.findById(scheduledVaccinationDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("ScheduledVaccination not found"));


        existingVaccination.setDateTime(updatedVaccination.getDateTime());
        existingVaccination.setNextDoseDateTime(updatedVaccination.getNextDoseDateTime());
        existingVaccination.setReminders(updatedVaccination.getReminders());
        findAndSetVaccine(scheduledVaccinationDTO.vaccineId(), existingVaccination);

        scheduledVaccinationRepository.save(existingVaccination);
    }
    public AdministeredVaccination addAdministeredVaccination(AdministeredVaccinationDTO administeredVaccinationDTO) {
        AdministeredVaccination administeredVaccination = administeredVaccinationMapper.toEntity(administeredVaccinationDTO);
        User currentUser = userService.getCurrentUser();
        administeredVaccination.setUser(currentUser);
        findAndSetVaccine(administeredVaccinationDTO.id(), administeredVaccination);
        return administeredVaccinationRepository.save(administeredVaccination);
    }
    public void deleteAdministeredVaccination(Long id) {
        administeredVaccinationRepository.deleteById(id);
    }

    public AdministeredVaccination editAdministeredVaccination(AdministeredVaccinationDTO administeredVaccinationDTO) {
        AdministeredVaccination updatedVaccination = administeredVaccinationMapper.toEntity(administeredVaccinationDTO);

        AdministeredVaccination existingVaccination = administeredVaccinationRepository.findById(administeredVaccinationDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("AdministeredVaccination not found"));

        existingVaccination.setDateTime(updatedVaccination.getDateTime());
        findAndSetVaccine(administeredVaccinationDTO.id(), existingVaccination);

        return administeredVaccinationRepository.save(existingVaccination);
    }

    public <T extends Vaccination> void findAndSetVaccine(Long vaccineId, T vaccination) {
        var vaccine = vaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new EntityNotFoundException("Vaccine not found"));
        vaccination.setVaccine(vaccine);
    }
}
