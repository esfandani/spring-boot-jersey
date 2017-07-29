package com.esfandani.labs.reminder.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import com.esfandani.labs.reminder.dtos.ReminderDTO;
import com.esfandani.labs.reminder.entities.Reminder;

@Service
public class ConversionService extends DefaultConversionService {
    public ConversionService(){
        Converter<Reminder, ReminderDTO> reminder2ReminderDTO = new Converter<Reminder, ReminderDTO>() {
            @Override
            public ReminderDTO convert(Reminder source) {
                ReminderDTO dto = new ReminderDTO();
                BeanUtils.copyProperties(source, dto);
                return dto;
            }
        };
        Converter<ReminderDTO,Reminder> reminderDTO2Reminder = new Converter<ReminderDTO, Reminder>() {
            @Override
            public Reminder convert(ReminderDTO source) {
                Reminder entity = new Reminder();
                BeanUtils.copyProperties(source,entity);
                return entity;
            }
        };

        addConverter(reminder2ReminderDTO);
        addConverter(reminderDTO2Reminder);
    }
}
