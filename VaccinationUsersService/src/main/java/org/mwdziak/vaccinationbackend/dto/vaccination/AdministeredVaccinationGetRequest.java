package org.mwdziak.vaccinationbackend.dto.vaccination;

import jakarta.annotation.Nonnull;
import org.mwdziak.vaccinationbackend.dto.VaccineDTO;

public record AdministeredVaccinationGetRequest(
        Long id,
        @Nonnull VaccineDTO vaccine,
        @Nonnull String dateTime,
        Integer doseNumber
) {
}
