package org.mwdziak.vaccinationbackend.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.domain.ScheduledVaccination;
import org.mwdziak.vaccinationbackend.dto.ReminderDTO;
import org.mwdziak.vaccinationbackend.dto.ReminderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ReminderMapperTest {

    @Autowired
    private ReminderMapper reminderMapper;

    @Test
    public void testToMessage() {
        Reminder reminder = new Reminder();
        reminder.setScheduledVaccination(new ScheduledVaccination());
        reminder.getScheduledVaccination().setDateTime(LocalDateTime.now());

        ReminderMessage reminderMessage = reminderMapper.toMessage(reminder);

        assertEquals(reminder.getScheduledVaccination().getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), reminderMessage.getVaccinationDateTime());
    }
}