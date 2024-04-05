package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.*;
import org.mwdziak.vaccinationbackend.domain.ScheduledVaccination;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationPostRequest;

@Mapper(uses = {ReminderMapper.class}, builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ScheduledVaccinationMapper {
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd HH:mm")
    ScheduledVaccinationPostRequest toDto(ScheduledVaccination scheduledVaccination);

    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd HH:mm")
    ScheduledVaccination toEntity(ScheduledVaccinationPostRequest scheduledVaccinationPostRequest);

    @AfterMapping
    default void setVaccination(@MappingTarget ScheduledVaccination scheduledVaccination) {
        scheduledVaccination.getReminders().forEach(reminder -> reminder.setScheduledVaccination(scheduledVaccination));
    }
}
