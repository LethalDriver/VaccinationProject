package org.mwdziak.vaccinationbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Reminder {
    @Id
    @GeneratedValue
    private Long id;
    private ZonedDateTime dateTime;
    @ManyToOne
    private ScheduledVaccination scheduledVaccination;
    private boolean sent;
}
