package org.mwdziak.vaccinationbackend.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class ScheduledVaccination extends Vaccination {
    LocalDateTime reminderDateTime;
    @Nonnull
    private LocalDateTime nextDoseDateTime;
}

