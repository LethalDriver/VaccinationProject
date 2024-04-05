package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.dto.reminder.ReminderMessage;
import org.mwdziak.vaccinationbackend.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;

    public List<Reminder> getRemindersToBeSend(Integer minutes) {
        LocalDateTime nextMinutes = LocalDateTime.now().plusMinutes(minutes);
        return reminderRepository.findToBeSendInNextMinutes(LocalDateTime.now(), nextMinutes);
    }

    public List<ReminderMessage> getReminderMessagesToBeSend(List<Reminder> reminders){
        return reminders.stream().map(this::toMessage).toList();
    }

    public Integer getMinutesToExecute(Reminder reminder) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderTime = reminder.getDateTime();
        return (int) now.until(reminderTime, ChronoUnit.MINUTES);
    }

    private ReminderMessage toMessage(Reminder reminder) {
        String title = "Vaccination reminder";
        String message = "Upcoming vaccination on " + reminder.getScheduledVaccination().getDateTime();
        return new ReminderMessage(message, title, reminder.getScheduledVaccination().getUser().getNotificationToken());
    }

}