package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;

import java.util.List;

public interface VaccinationRepository {
    List<AdministeredVaccination> findAllByUser_Id(Long userId);
}
