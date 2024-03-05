package org.mwdziak.vaccinationbackend.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class ScheduledVaccination extends Vaccination {
    @OneToMany(mappedBy = "scheduledVaccination", cascade = CascadeType.ALL)
    List<Reminder> reminders;
    @Nonnull
    private LocalDateTime nextDoseDateTime;
}

