package com.esfandani.labs.reminder.IT;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.esfandani.labs.reminder.dtos.ReminderDTO;
import com.esfandani.labs.reminder.dtos.SearchCriteria;
import com.esfandani.labs.reminder.entities.Reminder;
import com.esfandani.labs.reminder.entities.Status;
import com.esfandani.labs.reminder.repositories.ReminderRepository;
import com.esfandani.labs.reminder.services.ReminderService;

public class ReminderService_IT extends Base_IT {

    @Autowired
    private ReminderRepository repository;

    @Autowired
    ReminderService reminderService;

    @Before
    public void init() {
        repository.deleteAll();
    }

    @Test
    public void testFindById() {

        //ARRANGE
        LocalDateTime now = LocalDateTime.now();
        String name = "reza";
        String desc = "esfandani";
        Status status = Status.DONE;
        Reminder r = createReminder(name, desc, now, status);
        Reminder dbEntity = repository.save(r);

        //ACT
        ReminderDTO serviceDTO = reminderService.findById(dbEntity.getId());


        //ASSERT
        Assert.assertNotNull(serviceDTO);
        Assert.assertEquals(dbEntity.getId(), serviceDTO.getId());
        Assert.assertEquals(dbEntity.getDescription(), serviceDTO.getDescription());
        Assert.assertEquals(dbEntity.getName(), serviceDTO.getName());
        Assert.assertEquals(dbEntity.getDueDate(), serviceDTO.getDueDate());
        Assert.assertEquals(dbEntity.getStatus(), serviceDTO.getStatus());
    }

    @Test
    public void testFindByCriteria_StatusNull(){
        LocalDateTime now = LocalDateTime.now();
        String name = "reza";
        String desc = "esfandani";
        Status done = Status.DONE;
        Status notDone = Status.NOTDONE;
        Reminder r0 = createReminder(name, desc, now, done);
        Reminder db0 = repository.save(r0);

        Reminder r00 = createReminder(name, desc, now, notDone);
        Reminder db00 = repository.save(r00);

        Reminder r1 = createReminder(name, desc, now.minusDays(1), done);
        Reminder db1 = repository.save(r1);

        Reminder r2 = createReminder(name, desc, now.minusDays(2), done);
        Reminder db2 = repository.save(r2);

        Reminder r3 = createReminder(name, desc, now.minusDays(2), notDone);
        Reminder db3 = repository.save(r3);

        SearchCriteria sc0 = createCriteria(now.minusHours(1),now.plusHours(1),null);
        List<ReminderDTO> list = reminderService.findByCriteria(sc0);
        Assert.assertNotNull(list);
        Assert.assertEquals(2,list.size());
        Set<Long> set = list.stream().map(r->r.getId()).collect(Collectors.toSet());
        Assert.assertTrue(set.contains(r0.getId()));
        Assert.assertTrue(set.contains(r00.getId()));

    }

    @Test
    public void testFindByCriteria_Normal(){
        LocalDateTime now = LocalDateTime.now();
        String name = "reza";
        String desc = "esfandani";
        Status done = Status.DONE;
        Status notDone = Status.NOTDONE;
        Reminder r0 = createReminder(name, desc, now, done);
        Reminder db0 = repository.save(r0);

        Reminder r00 = createReminder(name, desc, now, notDone);
        Reminder db00 = repository.save(r00);

        Reminder r1 = createReminder(name, desc, now.minusDays(1), done);
        Reminder db1 = repository.save(r1);

        Reminder r2 = createReminder(name, desc, now.minusDays(2), done);
        Reminder db2 = repository.save(r2);

        Reminder r3 = createReminder(name, desc, now.minusDays(2), notDone);
        Reminder db3 = repository.save(r3);

        SearchCriteria sc = createCriteria(now.minusHours(1),now.plusHours(1),done);
        List<ReminderDTO> list = reminderService.findByCriteria(sc);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
        Set<Long> set = list.stream().map(r->r.getId()).collect(Collectors.toSet());
        Assert.assertTrue(set.contains(r0.getId()));

    }


    @Test
    public void testFindByCriteria_AllNull(){
        LocalDateTime now = LocalDateTime.now();
        String name = "reza";
        String desc = "esfandani";
        Status done = Status.DONE;
        Status notDone = Status.NOTDONE;
        Reminder r0 = createReminder(name, desc, now, done);
        Reminder db0 = repository.save(r0);

        Reminder r00 = createReminder(name, desc, now, notDone);
        Reminder db00 = repository.save(r00);

        Reminder r1 = createReminder(name, desc, now.minusDays(1), done);
        Reminder db1 = repository.save(r1);

        Reminder r2 = createReminder(name, desc, now.minusDays(2), done);
        Reminder db2 = repository.save(r2);

        Reminder r3 = createReminder(name, desc, now.minusDays(2), notDone);
        Reminder db3 = repository.save(r3);

        SearchCriteria sc = createCriteria(null,null,null);
        List<ReminderDTO> list = reminderService.findByCriteria(sc);
        Assert.assertNotNull(list);
        Assert.assertEquals(5,list.size());
        Set<Long> set = list.stream().map(r->r.getId()).collect(Collectors.toSet());
        Assert.assertTrue(set.contains(r0.getId()));
        Assert.assertTrue(set.contains(r00.getId()));
        Assert.assertTrue(set.contains(r1.getId()));
        Assert.assertTrue(set.contains(r2.getId()));
        Assert.assertTrue(set.contains(r3.getId()));
    }

    @Test
    public void testFindByCriteria_DueDateNull(){
        LocalDateTime now = LocalDateTime.now();
        String name = "reza";
        String desc = "esfandani";
        Status done = Status.DONE;
        Status notDone = Status.NOTDONE;
        Reminder r0 = createReminder(name, desc, now, done);
        Reminder db0 = repository.save(r0);

        Reminder r00 = createReminder(name, desc, now, notDone);
        Reminder db00 = repository.save(r00);

        Reminder r1 = createReminder(name, desc, now.minusDays(1), done);
        Reminder db1 = repository.save(r1);

        Reminder r2 = createReminder(name, desc, now.minusDays(2), done);
        Reminder db2 = repository.save(r2);

        Reminder r3 = createReminder(name, desc, now.minusDays(2), notDone);
        Reminder db3 = repository.save(r3);

        SearchCriteria sc = createCriteria(null,null,done);
        List<ReminderDTO> list = reminderService.findByCriteria(sc);
        Assert.assertNotNull(list);
        Assert.assertEquals(3,list.size());
        Set<Long> set = list.stream().map(r->r.getId()).collect(Collectors.toSet());
        Assert.assertTrue(set.contains(r0.getId()));
        Assert.assertTrue(set.contains(r1.getId()));
        Assert.assertTrue(set.contains(r2.getId()));
    }

    private Reminder createReminder(String name, String desc, LocalDateTime dueDate, Status status) {
        Reminder reminder = new Reminder();
        reminder.setName(name);
        reminder.setDueDate(dueDate);
        reminder.setDescription(desc);
        reminder.setStatus(status);
        return reminder;
    }

    private SearchCriteria createCriteria(LocalDateTime from,LocalDateTime to,Status status) {
        return  new SearchCriteria.SearchCriteriaBuilder().setFrom(from)
                .setTo(to)
                .setStatus(status)
                .build();
    }


}

