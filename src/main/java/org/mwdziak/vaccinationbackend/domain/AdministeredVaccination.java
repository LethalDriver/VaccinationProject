package org.mwdziak.vaccinationbackend.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Entity
@SuperBuilder
@RequiredArgsConstructor
public class AdministeredVaccination extends Vaccination {
}
