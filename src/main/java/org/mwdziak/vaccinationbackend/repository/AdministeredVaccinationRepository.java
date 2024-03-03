package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministeredVaccinationRepository extends JpaRepository<AdministeredVaccination, Long>, VaccinationRepository {
}
