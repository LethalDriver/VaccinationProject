package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;
import org.mwdziak.vaccinationbackend.dto.AdministeredVaccinationDTO;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {VaccineMapper.class})
public interface AdministeredVaccinationMapper {
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd HH:mm")
    AdministeredVaccinationDTO toDto(AdministeredVaccination administeredVaccination);
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd HH:mm")
    AdministeredVaccination toEntity(AdministeredVaccinationDTO administeredVaccinationDTO);


}
