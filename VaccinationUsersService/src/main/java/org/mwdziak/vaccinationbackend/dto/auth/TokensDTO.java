package org.mwdziak.vaccinationbackend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensDTO {
    private String token;
    private String refreshToken;
}
