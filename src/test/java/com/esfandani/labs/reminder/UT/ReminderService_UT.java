package com.esfandani.labs.reminder.UT;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esfandani.labs.reminder.dtos.ReminderDTO;
import com.esfandani.labs.reminder.entities.Reminder;
import com.esfandani.labs.reminder.entities.Status;
import com.esfandani.labs.reminder.repositories.ReminderRepository;
import com.esfandani.labs.reminder.services.ReminderService;
import com.esfandani.labs.reminder.services.impl.ConversionService;
import com.esfandani.labs.reminder.services.impl.ReminderServiceImpl;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ReminderService_UT {
    @Mock
    private ReminderRepository reminderRepository;

    @InjectMocks
    private ConversionService conversionService = new ConversionService();

    @InjectMocks
    ReminderService reminderService = new ReminderServiceImpl(reminderRepository, conversionService);

    @Test
    public void testSave() {
        LocalDateTime now = LocalDateTime.now();
        String name = "reminder";
        String desc = "description";
        Status status = Status.DONE;
        Reminder r = createReminder(name, desc, now, status);
        r.setId(1L);
        when(reminderRepository.save(any(Reminder.class))).thenReturn(
                r);
        ReminderDTO dto = reminderService.save(r);
        Assert.assertNotNull(dto);
        Assert.assertEquals(name, dto.getName());
        Assert.assertEquals(desc, dto.getDescription());
        Assert.assertEquals(now, dto.getDueDate());
        Assert.assertEquals(status, dto.getStatus());
    }

    private Reminder createReminder(String name, String desc, LocalDateTime dueDate, Status status) {
        Reminder reminder = new Reminder();
        reminder.setName(name);
        reminder.setDueDate(dueDate);
        reminder.setDescription(desc);
        reminder.setStatus(status);
        return reminder;
    }

}
