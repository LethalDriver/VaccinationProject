package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mwdziak.vaccinationbackend.domain.AdministeredVaccination;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationGetRequest;
import org.mwdziak.vaccinationbackend.dto.vaccination.AdministeredVaccinationPostRequest;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface AdministeredVaccinationMapper {
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ")
    AdministeredVaccinationGetRequest toDto(AdministeredVaccination administeredVaccination);
    @Mapping(source = "dateTime", target = "dateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ")
    AdministeredVaccination toEntity(AdministeredVaccinationPostRequest administeredVaccinationPostRequest);


}
