package org.mwdziak.vaccinationbackend.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Integer recommendedAge;
    @Nonnull
    private Integer doses;
    @OneToMany(mappedBy = "vaccine")
    private List<RemainingDose> remainingDose;
}
