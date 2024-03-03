package org.mwdziak.vaccinationbackend.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Entity
@SuperBuilder
@NoArgsConstructor
public class ScheduledVaccination extends Vaccination {
    LocalDateTime reminderDateTime;
    @Nonnull
    private LocalDateTime nextDoseDate;
}

