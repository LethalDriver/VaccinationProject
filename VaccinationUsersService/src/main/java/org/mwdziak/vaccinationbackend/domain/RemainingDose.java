package org.mwdziak.vaccinationbackend.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemainingDose {
    @Id
    @GeneratedValue
    private Long id;
    private Integer doseNumber;
    private Integer daysAfterPreviousDose;
    @ManyToOne(cascade = CascadeType.ALL)
    private Vaccine vaccine;

    public RemainingDose(Integer doseNumber, Integer daysAfterPreviousDose) {
        this.doseNumber = doseNumber;
        this.daysAfterPreviousDose = daysAfterPreviousDose;
    }
}