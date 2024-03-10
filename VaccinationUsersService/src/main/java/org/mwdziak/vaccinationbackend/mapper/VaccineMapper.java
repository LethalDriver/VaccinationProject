package org.mwdziak.vaccinationbackend.mapper;

import jakarta.annotation.Nonnull;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.mwdziak.vaccinationbackend.dto.VaccineDTO;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface VaccineMapper {
    VaccineDTO toDto(Vaccine vaccine);
    Vaccine toEntity(VaccineDTO vaccineDTO);
}
