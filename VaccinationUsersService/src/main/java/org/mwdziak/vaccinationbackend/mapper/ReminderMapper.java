package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.*;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.domain.ScheduledVaccination;
import org.mwdziak.vaccinationbackend.dto.ReminderRequest;
import org.mwdziak.vaccinationbackend.dto.ReminderMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ReminderMapper {
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "reminderDateToString")
    ReminderRequest toDto(Reminder reminder);
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "reminderStringToDate")
    Reminder toEntity(ReminderRequest reminderRequest);
    @Mapping(source = "scheduledVaccination", target = "vaccinationDateTime", qualifiedByName = "scheduledVaccinationToScheduledVaccinationDateTimeString")
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "reminderDateToString")
    ReminderMessage toMessage(Reminder reminder);

    @Named("reminderDateToString")
    default String reminderDateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    @Named("reminderStringToDate")
    default LocalDateTime reminderStringToDate(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Named("scheduledVaccinationToScheduledVaccinationDateTimeString")
    default String scheduledVaccinationToScheduledVaccinationDateTimeString(ScheduledVaccination scheduledVaccination) {
        LocalDateTime dateTime = scheduledVaccination.getDateTime();
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @AfterMapping
    default void setUserId(Reminder reminder, @MappingTarget ReminderMessage reminderMessage) {
        reminderMessage.setUserId(reminder.getScheduledVaccination().getUser().getId());
    }
}
