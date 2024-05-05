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
public class Doses {
    @Id
    @GeneratedValue
    private Long id;
    private Integer doseNumber;
    private Integer monthsAfterPreviousDose;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Vaccine vaccine;

    public Doses(Integer doseNumber, Integer monthsAfterPreviousDose) {
        this.doseNumber = doseNumber;
        this.monthsAfterPreviousDose = monthsAfterPreviousDose;
    }
}
