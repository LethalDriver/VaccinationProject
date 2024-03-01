package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.ScheduledVaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledVaccinationRepository extends JpaRepository<ScheduledVaccination, Long> {
}
