package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

public record VaccinationDTO(@Nonnull VaccineDTO vaccine, @Nonnull String date, String nextDoseDate){}
