package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mwdziak.vaccinationbackend.domain.ScheduledVaccination;
import org.mwdziak.vaccinationbackend.dto.ScheduledVaccinationDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(uses = {VaccineMapper.class}, builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ScheduledVaccinationMapper extends VaccinationMapper {
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "dateTimeToString")
    @Mapping(source = "nextDoseDateTime", target = "nextDoseDateTime", qualifiedByName = "dateTimeToString")
    @Mapping(source = "reminderDateTime", target = "reminderDateTime", qualifiedByName = "dateTimeToString")
    ScheduledVaccinationDTO toDto(ScheduledVaccination scheduledVaccination);

    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "stringToDateTime")
    @Mapping(source = "nextDoseDateTime", target = "nextDoseDateTime", qualifiedByName = "stringToDateTime")
    @Mapping(source = "reminderDateTime", target = "reminderDateTime", qualifiedByName = "stringToDateTime")
    ScheduledVaccination toEntity(ScheduledVaccinationDTO scheduledVaccinationDTO);
}
