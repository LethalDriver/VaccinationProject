package org.mwdziak.vaccinationbackend.domain;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@SuperBuilder
@NoArgsConstructor
public class AdministeredVaccination extends Vaccination {
}
