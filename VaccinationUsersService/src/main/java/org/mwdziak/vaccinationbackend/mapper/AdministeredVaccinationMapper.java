package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;
import org.mwdziak.vaccinationbackend.dto.AdministeredVaccinationDTO;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface AdministeredVaccinationMapper extends DateParser {
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "dateTimeToString")
    AdministeredVaccinationDTO toDto(AdministeredVaccination administeredVaccination);
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "stringToDateTime")
    AdministeredVaccination toEntity(AdministeredVaccinationDTO administeredVaccinationDTO);


}
