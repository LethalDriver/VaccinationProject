package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.*;
import org.mwdziak.vaccinationbackend.domain.ScheduledVaccination;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.ScheduledVaccinationPostRequest;

@Mapper(uses = {ReminderMapper.class}, builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ScheduledVaccinationMapper {
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ")
    ScheduledVaccinationGetRequest toDto(ScheduledVaccination scheduledVaccination);

    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ")
    ScheduledVaccination toEntity(ScheduledVaccinationPostRequest scheduledVaccinationPostRequest);

    @AfterMapping
    default void setVaccination(@MappingTarget ScheduledVaccination scheduledVaccination) {
        scheduledVaccination.getReminders().forEach(reminder -> reminder.setScheduledVaccination(scheduledVaccination));
    }
}
