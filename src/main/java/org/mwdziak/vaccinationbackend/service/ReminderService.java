package org.mwdziak.vaccinationbackend.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.vaccinationbackend.domain.Reminder;
import org.mwdziak.vaccinationbackend.dto.ReminderMessage;
import org.mwdziak.vaccinationbackend.mapper.ReminderMapper;
import org.mwdziak.vaccinationbackend.repository.ReminderRepository;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    public List<Reminder> getRemindersToBeSend(Integer minutes) {
        LocalDateTime nextMinutes = LocalDateTime.now().plusMinutes(minutes);
        return reminderRepository.findToBeSendInNextMinutes(LocalDateTime.now(), LocalDateTime.now().plusMinutes(minutes));
    }

    public List<ReminderMessage> getReminderMessagesToBeSend(List<Reminder> reminders){
        return reminders.stream().map(reminderMapper::toMessage).toList();
    }

    public Integer getMinutesToExecute(Reminder reminder) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderTime = reminder.getDateTime();
        return (int) now.until(reminderTime, ChronoUnit.MINUTES);
    }

}