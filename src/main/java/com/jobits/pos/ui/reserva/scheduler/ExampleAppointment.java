package com.jobits.pos.ui.reserva.scheduler;

import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * Example Appointment
 *
 * @author Joshua Gerth - jgerth@thirdnf.com
 */
public class ExampleAppointment implements Appointment {

    private ExampleCategory _category;
    private ExampleResource _resource;
    private String _title;
    private LocalDateTime _dateTime;
    private Duration _duration;

    public ExampleAppointment(String title, ExampleCategory category, ExampleResource resource) {
        _title = title;
        _category = category;
        _resource = resource;
    }

    public ExampleCategory getCategory() {
        return _category;
    }

    public void setCategory(ExampleCategory category) {
        _category = category;
    }

    @Override
    public LocalDateTime getDateTime() {
        return _dateTime;
    }

    @Override
    public Resource getResource() {
        return _resource;
    }

    public void setResource(ExampleResource resource) {
        _resource = resource;
    }

    @Override
    public Duration getDuration() {
        return _duration;
    }

    public void setDuration(Duration duration) {
        _duration = duration;
    }

    @Override
    public String getTitle() {
        return _title;
    }

    /**
     * Set the title of the appointment.
     *
     * @param title (not null) New title for the appointment
     */
    public void setTitle(String title) {
        _title = title;
    }

    public void setDateTime(LocalDateTime time) {
        _dateTime = time;
    }

    public static ExampleAppointment create(
            String title,
            ExampleCategory category,
            ExampleResource resource,
            LocalTime time,
            int minutes) {

        ExampleAppointment appointment = new ExampleAppointment(title, category, resource);

        LocalDateTime date = LocalDateTime.of(
                ExampleScheduleModel.Today.getYear(),
                ExampleScheduleModel.Today.getMonth(),
                ExampleScheduleModel.Today.getDayOfMonth(),
                time.getHour(), time.getMinute(), time.getSecond(), 0);
        
        appointment.setDateTime(date);
        appointment.setDuration(Duration.ofMinutes(minutes));

        return appointment;
    }
}
