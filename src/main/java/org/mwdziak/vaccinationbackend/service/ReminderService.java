package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.repository.ReminderRepository;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final TaskScheduler taskScheduler;
    private final ReminderRepository reminderRepository;
    private final ReminderEmitter reminderEmitter;

    private void scheduleReminder(Integer minutesToExecute) {
        Instant instant = Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(minutesToExecute));
        taskScheduler.schedule(new ReminderSenderTask(reminderEmitter), instant);
    }

    private List<Reminder> getRemindersToBeSend(Integer minutes) {
        LocalDateTime nextMinutes = LocalDateTime.now().plusMinutes(minutes);
        return reminderRepository.findToBeSendInNextMinutes(nextMinutes);
    }

    public void scheduleReminders() {
        List<Reminder> reminders = getRemindersToBeSend(5);
        reminders.forEach(reminder -> scheduleReminder(getMinutesToExecute(reminder)));
    }

    public Integer getMinutesToExecute(Reminder reminder) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderTime = reminder.getDateTime();
        return (int) now.until(reminderTime, ChronoUnit.MINUTES);
    }

}