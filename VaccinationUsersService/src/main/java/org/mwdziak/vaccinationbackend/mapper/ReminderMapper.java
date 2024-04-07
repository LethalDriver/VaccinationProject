package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.*;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.dto.reminder.ReminderGetRequest;
import org.mwdziak.vaccinationbackend.dto.reminder.ReminderPostRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ReminderMapper {
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ")
    ReminderGetRequest toDto(Reminder reminder);
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ")
    Reminder toEntity(ReminderPostRequest reminderPostRequest);
}
