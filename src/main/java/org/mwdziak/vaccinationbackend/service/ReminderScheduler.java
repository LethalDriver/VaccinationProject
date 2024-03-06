package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {
    private final ReminderService reminderService;
    private final ReminderEmitter reminderEmitter;
    private final TaskScheduler taskScheduler;
    @Scheduled(fixedRate = 60000)
    public void scheduleReminders() {
        List<Reminder> reminders = reminderService.getRemindersToBeSend(1);
        reminders.forEach(reminder -> scheduleReminder(reminderService.getMinutesToExecute(reminder)));
    }

    private void scheduleReminder(Integer minutesToExecute) {
        Instant instant = Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(minutesToExecute));
        taskScheduler.schedule(new ReminderSenderTask(reminderEmitter), instant);
    }
}
