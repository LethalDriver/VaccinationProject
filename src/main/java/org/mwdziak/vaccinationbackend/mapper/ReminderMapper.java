package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.dto.ReminderDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ReminderMapper {
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "reminderDateToString")
    ReminderDTO toDto(Reminder reminder);
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "reminderStringToDate")
    Reminder toEntity(ReminderDTO reminderDTO);

    @Named("reminderDateToString")
    default String reminderDateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    @Named("reminderStringToDate")
    default LocalDateTime reminderStringToDate(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
