package com.esfandani.labs.reminder.resources;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esfandani.labs.reminder.dtos.ReminderDTO;
import com.esfandani.labs.reminder.dtos.SearchCriteria;
import com.esfandani.labs.reminder.entities.Reminder;
import com.esfandani.labs.reminder.entities.Status;
import com.esfandani.labs.reminder.services.ReminderService;

@Component
@Path("reminders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReminderResrouce {

    @Autowired
    ReminderService reminderService;

    @POST
    public ReminderDTO save(Reminder reminder) {
        Objects.requireNonNull(reminder);
        return reminderService.save(reminder);
    }

    @PUT
    public ReminderDTO update(Reminder reminder) {
        Objects.requireNonNull(reminder);
        Objects.requireNonNull(reminder.getId());
        return reminderService.save(reminder);
    }

    @Path("{id}")
    @DELETE
    public boolean delete(@PathParam("id") Long id) {
        Objects.requireNonNull(id);
        return reminderService.delete(id);
    }

    @Path("{id}")
    @GET
    public ReminderDTO findById(@PathParam("id") Long id) {
        Objects.requireNonNull(id);
        return reminderService.findById(id);
    }

    @GET
    public List<ReminderDTO> findByStatusDueDate(@QueryParam("from") String from,
                                                 @QueryParam("to") String to,
                                                 @QueryParam("status") Status status) {

        LocalDateTime fromDateTime = from != null ? LocalDateTime.parse(from) : null;
        LocalDateTime toDateTime = to != null ? LocalDateTime.parse(to) : null;
        SearchCriteria criteria = new SearchCriteria.SearchCriteriaBuilder().setFrom(fromDateTime)
                .setTo(toDateTime)
                .setStatus(status)
                .build();
        return reminderService.findByCriteria(criteria);
    }

}
