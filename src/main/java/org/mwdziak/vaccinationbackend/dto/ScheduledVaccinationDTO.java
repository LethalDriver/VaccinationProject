package org.mwdziak.vaccinationbackend.dto;

import jakarta.annotation.Nonnull;

public record ScheduledVaccinationDTO(@Nonnull VaccineDTO vaccine, @Nonnull String dateTime, String nextDoseDateTime, String remainderDateTime){}
