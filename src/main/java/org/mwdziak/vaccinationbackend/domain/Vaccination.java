package org.mwdziak.vaccinationbackend.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Vaccination {
    @Id
    @GeneratedValue
    private Long id;
    @Nonnull
    private String name;
    private String recommendedAge;
}
