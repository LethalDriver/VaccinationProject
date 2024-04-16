package org.mwdziak.vaccinationbackend.mapper;

import javax.annotation.processing.Generated;
import org.mwdziak.vaccinationbackend.domain.Doses;
import org.mwdziak.vaccinationbackend.dto.DosesDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T13:16:46+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class DoseMapperImpl implements DoseMapper {

    @Override
    public DosesDTO toDto(Doses doses) {
        if ( doses == null ) {
            return null;
        }

        Long id = null;
        Integer doseNumber = null;

        id = doses.getId();
        doseNumber = doses.getDoseNumber();

        Integer daysAfterPreviousDose = null;

        DosesDTO dosesDTO = new DosesDTO( id, doseNumber, daysAfterPreviousDose );

        return dosesDTO;
    }

    @Override
    public Doses toEntity(DosesDTO dosesDTO) {
        if ( dosesDTO == null ) {
            return null;
        }

        Doses doses = new Doses();

        doses.setId( dosesDTO.id() );
        doses.setDoseNumber( dosesDTO.doseNumber() );

        return doses;
    }
}
