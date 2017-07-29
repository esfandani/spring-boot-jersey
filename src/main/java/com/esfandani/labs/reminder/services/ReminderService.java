package com.esfandani.labs.reminder.services;

import java.util.List;

import com.esfandani.labs.reminder.dtos.ReminderDTO;
import com.esfandani.labs.reminder.dtos.SearchCriteria;
import com.esfandani.labs.reminder.entities.Reminder;

public interface ReminderService {
    ReminderDTO save(Reminder reminder);
    List<ReminderDTO> findAll();
    boolean delete(Long id);
    ReminderDTO findById(Long id);
    List<ReminderDTO> findByCriteria(SearchCriteria criteria);

}
