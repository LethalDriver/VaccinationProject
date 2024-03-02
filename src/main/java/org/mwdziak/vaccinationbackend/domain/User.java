package org.mwdziak.vaccinationbackend.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    @Nonnull
    private String email;
    @Nonnull
    private String password;
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    @Nonnull
    private LocalDate dateOfBirth;
    @Nonnull
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
