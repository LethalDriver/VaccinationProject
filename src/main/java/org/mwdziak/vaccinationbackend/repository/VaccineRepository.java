package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    @Query("SELECT v FROM Vaccine v WHERE v.recommendedAge BETWEEN ?1 AND ?1 + 1")
    public List<Vaccine> findRecommendedVaccinesByAge(Integer age);
}
