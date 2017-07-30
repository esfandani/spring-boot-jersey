package com.esfandani.labs.reminder.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esfandani.labs.reminder.entities.Reminder;
import com.esfandani.labs.reminder.entities.Status;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByDueDateBetweenAndStatus(LocalDateTime from, LocalDateTime to, Status status);

    List<Reminder> findByDueDateBetween(LocalDateTime from, LocalDateTime to);
}
