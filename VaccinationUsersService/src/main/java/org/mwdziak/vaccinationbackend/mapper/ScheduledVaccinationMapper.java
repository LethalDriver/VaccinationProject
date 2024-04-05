package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.*;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.domain.ScheduledVaccination;
import org.mwdziak.vaccinationbackend.domain.Vaccination;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.mwdziak.vaccinationbackend.dto.ScheduledVaccinationDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(uses = {ReminderMapper.class}, builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ScheduledVaccinationMapper {
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd HH:mm")
    ScheduledVaccinationDTO toDto(ScheduledVaccination scheduledVaccination);

    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd HH:mm")
    ScheduledVaccination toEntity(ScheduledVaccinationDTO scheduledVaccinationDTO);

    @AfterMapping
    default void setVaccination(@MappingTarget ScheduledVaccination scheduledVaccination) {
        scheduledVaccination.getReminders().forEach(reminder -> reminder.setScheduledVaccination(scheduledVaccination));
    }
}
