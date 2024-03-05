package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.dto.ReminderDTO;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ReminderMapper extends DateParser {
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "dateTimeToString")
    ReminderDTO toDto(Reminder reminder);
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "stringToDateTime")
    Reminder toEntity(ReminderDTO reminderDTO);
}
