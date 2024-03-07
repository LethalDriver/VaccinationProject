package org.mwdziak.vaccinationbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Reminder {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime dateTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private ScheduledVaccination scheduledVaccination;
    private boolean sent;
}
