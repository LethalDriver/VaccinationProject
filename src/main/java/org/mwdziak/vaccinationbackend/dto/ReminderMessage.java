package org.mwdziak.vaccinationbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ReminderMessage {
    private Long id;
    private String dateTime;
    private String vaccinationDateTime;
}
