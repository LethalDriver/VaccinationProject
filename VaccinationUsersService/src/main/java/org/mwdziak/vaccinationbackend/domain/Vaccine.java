package org.mwdziak.vaccinationbackend.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vaccine {
    @Id
    @GeneratedValue
    private Long id;
    @Nonnull
    private String name;
    private Integer recommendedAgeMonthsLowerBound;
    private Integer recommendedAgeMonthsUpperBound;
    @OneToMany(mappedBy = "vaccine", cascade = CascadeType.ALL)
    private List<RemainingDose> remainingDose;

    public Vaccine(@Nonnull String name, Integer recommendedAgeMonthsLowerBound, List<RemainingDose> remainingDoses) {
        this.name = name;
        this.recommendedAgeMonthsLowerBound = recommendedAgeMonthsLowerBound;
        this.remainingDose = remainingDoses;
    }
}
