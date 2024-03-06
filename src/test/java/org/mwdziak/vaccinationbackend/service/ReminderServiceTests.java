package org.mwdziak.vaccinationbackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.repository.ReminderRepository;
import org.springframework.scheduling.TaskScheduler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReminderServiceTests {

    @Mock
    private TaskScheduler taskScheduler;

    @Mock
    private ReminderRepository reminderRepository;

    @Mock
    private ReminderEmitter reminderEmitter;

    @InjectMocks
    private ReminderService reminderService;

    private Reminder reminder;

    @BeforeEach
    public void setup() {
        reminder = new Reminder();
        reminder.setDateTime(LocalDateTime.now().plusMinutes(10));
    }

    @Test
    public void testScheduleReminders() {
        List<Reminder> reminders = Arrays.asList(reminder);
        when(reminderRepository.findToBeSendInNextMinutes(any(LocalDateTime.class))).thenReturn(reminders);

        reminderService.scheduleReminders();

        verify(taskScheduler, times(1)).schedule(any(Runnable.class), any(Instant.class));
    }

    @Test
    public void testGetMinutesToExecute() {
        int minutes = reminderService.getMinutesToExecute(reminder);
        assert(minutes >= 0);
    }
}