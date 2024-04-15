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
    private List<Doses> doses;

    public Vaccine(@Nonnull String name, Integer recommendedAgeMonthsLowerBound, List<Doses> doses) {
        this.name = name;
        this.recommendedAgeMonthsLowerBound = recommendedAgeMonthsLowerBound;
        this.doses = doses;
    }
}
