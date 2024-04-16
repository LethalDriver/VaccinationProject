package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mwdziak.vaccinationbackend.domain.Doses;
import org.mwdziak.vaccinationbackend.dto.DosesDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DoseMapper {
    DosesDTO toDto(Doses doses);
    Doses toEntity(DosesDTO dosesDTO);
}
