package com.esfandani.labs.reminder.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esfandani.labs.reminder.dtos.ReminderDTO;
import com.esfandani.labs.reminder.dtos.SearchCriteria;
import com.esfandani.labs.reminder.entities.Reminder;
import com.esfandani.labs.reminder.repositories.ReminderRepository;
import com.esfandani.labs.reminder.services.ReminderService;

@Service
public class ReminderServiceImpl implements ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public ReminderDTO save(Reminder reminder) {
        return Optional.ofNullable(reminderRepository.save(reminder))
                .map(source -> conversionService.convert(source, ReminderDTO.class))
                .orElse(null);
    }

    @Override
    public List<ReminderDTO> findAll() {
        return reminderRepository.findAll()
                .stream()
                .map(source -> conversionService.convert(source, ReminderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Long id) {
        reminderRepository.delete(id);
        return true;
    }

    @Override
    public ReminderDTO findById(Long id) {
        return Optional.ofNullable(reminderRepository.findOne(id))
                .map(source -> conversionService.convert(source, ReminderDTO.class))
                .orElse(null);
    }

    @Override
    public List<ReminderDTO> findByCriteria(SearchCriteria criteria) {
        List<Reminder> ret = criteria.getStatus()
                .map(status -> reminderRepository.findByDueDateBetweenAndStatus(criteria.getFrom(), criteria.getTo(), status))
                .orElseGet(() -> {
                    return reminderRepository.findByDueDateBetween(criteria.getFrom(), criteria.getTo());
                });
        return ret.stream()
                .map(source -> conversionService.convert(source, ReminderDTO.class))
                .collect(Collectors.toList());
    }

}
