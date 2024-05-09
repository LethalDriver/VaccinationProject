package org.mwdziak.vaccinationbackend.mapper;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mwdziak.vaccinationbackend.domain.User;
import org.mwdziak.vaccinationbackend.dto.UserDetailsDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {
    UserDetailsDTO toDto(User user);
    User toEntity(UserDetailsDTO userDetailsDTO);
}
