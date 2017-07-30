package com.esfandani.labs.reminder.services;

import java.util.List;

import com.esfandani.labs.reminder.dtos.ReminderDTO;
import com.esfandani.labs.reminder.dtos.SearchCriteria;
import com.esfandani.labs.reminder.entities.Reminder;

/**
 * Service to manipulate and retrieve reminder object.
 */
public interface ReminderService {
    /**
     * save or update the reminder in database.
     * @param reminder objects which should be saved. if the id is available the  reminder in database would be updated with the by the input parameter
     * @return {@link ReminderDTO} of the saved {@link Reminder} object
     */
    ReminderDTO save(Reminder reminder);

    /**
     * delete the {@link Reminder} object from database which has the given id as input parameter
     * @param id id of the reminder which will be deleted from database.
     * @return true
     */
    boolean delete(Long id);

    /**
     * find the reminder with the given id  and returns the entity's dto
     * @param id find the {@link Reminder} object from database which has the given id as input parameter.
     * @return {@link ReminderDTO}
     */
    ReminderDTO findById(Long id);

    /**
     *
     * find the list of {@link Reminder} objects from database based on the {@link SearchCriteria} passed to the method.
     * if there is no filter used in {@link SearchCriteria} this method doesn't filter and returns everything from database.
     * @param criteria
     * @return list of {@link ReminderDTO}
     */
    List<ReminderDTO> findByCriteria(SearchCriteria criteria);

}
