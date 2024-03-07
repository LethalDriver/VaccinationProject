package org.mwdziak.vaccinationbackend.service;

import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.scheduling.ReminderScheduler;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReminderSchedulerTest {
    @Mock
    private ReminderService reminderService;
    @InjectMocks
    private ReminderScheduler reminderScheduler;


    @Test
    public void testScheduleReminders() {
        List<Reminder> reminders = new ArrayList<>();
        reminders.add(new Reminder());
        when(reminderService.getRemindersToBeSend(1)).thenReturn(reminders);

        reminderScheduler.scheduleReminders();

        await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
            verify(reminderService, atLeast(1)).getRemindersToBeSend(5);
        });
    }
}
