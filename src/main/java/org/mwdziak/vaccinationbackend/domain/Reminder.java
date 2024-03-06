package org.mwdziak.vaccinationbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Reminder {
    @Id
    @GeneratedValue
    private Long id;
    private String dateTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private ScheduledVaccination scheduledVaccination;
    private boolean sent;
}
