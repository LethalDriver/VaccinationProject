package org.mwdziak.vaccinationbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@SuperBuilder
@RequiredArgsConstructor
public class ScheduledVaccination extends Vaccination {
}

