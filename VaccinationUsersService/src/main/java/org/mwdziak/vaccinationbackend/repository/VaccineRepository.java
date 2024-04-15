package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    public Optional<Vaccine> findByName(String name);
    @Query("SELECT v FROM Vaccine v WHERE v.recommendedAgeMonthsLowerBound BETWEEN ?1 AND ?1 + 1 OR v.recommendedAgeMonthsLowerBound = NULL")
    public List<Vaccine> findRecommendedVaccinesByAge(Integer age);
}
