package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationGetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministeredVaccinationRepository extends JpaRepository<AdministeredVaccination, Long> {
    List<AdministeredVaccination> findAllByUser_Id(Long userId);

    List<AdministeredVaccination> findAllByUserId(Long currentUserId);
}
