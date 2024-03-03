package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

public record AdministeredVaccinationDTO(@Nonnull VaccineDTO vaccine, @Nonnull String dateTime) {
}
