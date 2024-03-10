package org.mwdziak.vaccinationbackend.mapper;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateParser {
    @Named("dateTimeToString")
    default String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    @Named("stringToDateTime")
    default LocalDateTime stringToDate(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
