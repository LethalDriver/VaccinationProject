package org.mwdziak.vaccinationbackend.scheduling;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.dto.reminder.ReminderMessage;
import org.mwdziak.vaccinationbackend.repository.ReminderRepository;
import org.mwdziak.vaccinationbackend.service.ReminderEmitter;
import org.mwdziak.vaccinationbackend.service.ReminderService;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {
    private final ReminderService reminderService;
    private final ReminderEmitter reminderEmitter;
    private final TaskScheduler taskScheduler;
    private final ReminderRepository reminderRepository;
    @Value("${reminder.getRemindersToBeSendInMinutes}")
    private Integer getRemindersToBeSendInMinutes;
    @Scheduled(fixedRateString = "${reminder.msToScanDb}")
    public void scheduleReminders() {
        List<Reminder> reminders = reminderService.getRemindersToBeSend(getRemindersToBeSendInMinutes);
        List<ReminderMessage> reminderMessages = reminderService.getReminderMessagesToBeSend(reminders);
        for (int i = 0; i < reminders.size(); i++) {
            if (reminders.get(i).isSent()) {
                continue;
            }
            Reminder reminder = reminders.get(i);
            Integer minutesToExecute = reminderService.getMinutesToExecute(reminder);
            scheduleReminder(minutesToExecute, reminderMessages.get(i));
            reminder.setSent(true);
            reminderRepository.save(reminder);
        }
    }

    private void scheduleReminder(Integer minutesToExecute, ReminderMessage reminder) {
        Instant instant = Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(minutesToExecute));
        taskScheduler.schedule(new ReminderSenderTask(reminderEmitter, reminder), instant);
    }
}
