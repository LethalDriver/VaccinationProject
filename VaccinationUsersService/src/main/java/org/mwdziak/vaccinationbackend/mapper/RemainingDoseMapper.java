package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mwdziak.vaccinationbackend.domain.RemainingDose;
import org.mwdziak.vaccinationbackend.dto.RemainingDoseDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RemainingDoseMapper {
    RemainingDoseDTO toDto(RemainingDose remainingDose);
    RemainingDose toEntity(RemainingDoseDTO remainingDoseDTO);
}
