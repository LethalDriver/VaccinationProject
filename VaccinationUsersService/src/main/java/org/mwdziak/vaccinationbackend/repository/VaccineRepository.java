package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    public Optional<Vaccine> findByName(String name);
    @Query("SELECT v FROM Vaccine v WHERE ?1 BETWEEN v.recommendedAgeMonthsLowerBound AND v.recommendedAgeMonthsUpperBound OR v.recommendedAgeMonthsUpperBound IS NULL")
    public List<Vaccine> findRecommendedVaccinesByAge(Integer userAgeMonths);
}
