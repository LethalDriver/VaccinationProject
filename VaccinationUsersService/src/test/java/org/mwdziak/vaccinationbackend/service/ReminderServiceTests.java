package org.mwdziak.vaccinationbackend.service;


import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.repository.ReminderRepository;
import org.springframework.scheduling.TaskScheduler;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.awaitility.Awaitility.await;
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
        reminder.setDateTime(ZonedDateTime.now().plusMinutes(10));
    }

    @Test
    public void should_return_reminders_to_be_send() {
        when(reminderRepository.findToBeSendInNextMinutes(any(ZonedDateTime.class), any(ZonedDateTime.class))).thenReturn(Arrays.asList(reminder));
        List<Reminder> reminders = reminderService.getRemindersToBeSend(1);
        Assertions.assertEquals(1, reminders.size());
    }

    @Test
    public void testGetMinutesToExecute() {
        int minutes = reminderService.getMinutesToExecute(reminder);
        assert(minutes >= 0);
    }


}