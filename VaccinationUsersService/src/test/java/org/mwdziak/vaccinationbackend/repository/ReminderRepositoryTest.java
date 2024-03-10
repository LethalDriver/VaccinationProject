package org.mwdziak.vaccinationbackend.repository;

import org.junit.jupiter.api.Test;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReminderRepositoryTest extends RepositoryTests {
    @Autowired
    private ReminderRepository reminderRepository;

    @Test
    public void findToBeSendInNextMinutes_returnsRemindersWithinTimeRange() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes(30);

        Reminder reminder = new Reminder();
        reminder.setDateTime(start.plusMinutes(15));
        reminder.setSent(false);
        reminderRepository.save(reminder);

        List<Reminder> reminders = reminderRepository.findToBeSendInNextMinutes(start, end);

        assertEquals(1, reminders.size());
        assertEquals(reminder.getId(), reminders.get(0).getId());
    }

    @Test
    public void findToBeSendInNextMinutes_returnsEmptyListWhenNoRemindersWithinTimeRange() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes(30);

        List<Reminder> reminders = reminderRepository.findToBeSendInNextMinutes(start, end);

        assertEquals(0, reminders.size());
    }
}
