package org.mwdziak.vaccinationbackend.repository;

import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    @Query("SELECT r FROM Reminder r WHERE r.dateTime <= :time AND r.sent = false")
    List<Reminder> findToBeSendInNextMinutes(@Param("time") LocalDateTime time);
}
