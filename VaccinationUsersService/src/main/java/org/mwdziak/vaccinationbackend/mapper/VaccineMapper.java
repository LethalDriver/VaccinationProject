package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mwdziak.vaccinationbackend.domain.Vaccine;
import org.mwdziak.vaccinationbackend.dto.VaccineDTO;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring", uses = {DoseMapper.class})
public interface VaccineMapper {
    VaccineDTO toDto(Vaccine vaccine);
    Vaccine toEntity(VaccineDTO vaccineDTO);
}
