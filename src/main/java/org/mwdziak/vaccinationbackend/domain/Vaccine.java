package org.mwdziak.vaccinationbackend.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer recommendedAge;
    @Nonnull
    private Integer doses;
    private Integer daysBetweenDoses;

    public Vaccine(@Nonnull String name, Integer recommendedAge, @Nonnull Integer doses, Integer daysBetweenDoses) {
        this.name = name;
        this.recommendedAge = recommendedAge;
        this.doses = doses;
        this.daysBetweenDoses = daysBetweenDoses;
    }
}
