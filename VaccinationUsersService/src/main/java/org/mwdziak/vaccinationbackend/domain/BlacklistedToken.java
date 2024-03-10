package org.mwdziak.vaccinationbackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="_blacklisted_tokens")
@NoArgsConstructor
public class BlacklistedToken {
    @GeneratedValue
    @Id
    private Integer id;
    private String token;

    public BlacklistedToken(String token) {
        this.token = token;
    }

}
