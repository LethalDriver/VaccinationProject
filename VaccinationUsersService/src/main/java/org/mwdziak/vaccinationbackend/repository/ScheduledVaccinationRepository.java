package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;
import org.mwdziak.vaccinationbackend.domain.ScheduledVaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledVaccinationRepository extends JpaRepository<ScheduledVaccination, Long> {
    List<ScheduledVaccination> findAllByUser_Id(Long userId);
}
