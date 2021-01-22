package com.jobits.pos.ui.reserva.model;

import com.jobits.pos.core.repo.impl.ConfiguracionDAO;
import com.jobits.pos.recursos.R;
import com.jobits.pos.reserva.core.domain.Categoria;
import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.ui.scheduler.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A example model, has its database hard coded and it is only capable of
 * showing two days, Today and Tomorrow. Everything else is blank and will
 * ignore updates.
 *
 * @author Joshua Gerth - jgerth@thirdnf.com
 */
public class ScheduleModel extends AbstractScheduleModel {

    private LocalTime startTime = LocalTime.of(ConfiguracionDAO.getInstance().find(
            R.SettingID.HORARIO_INICIO_HORA).getValor(), 0);
    private LocalTime endTime = LocalTime.of(ConfiguracionDAO.getInstance().find(
            R.SettingID.HORARIO_CIERRE_HORA).getValor(), 0);
    private Duration increments = Duration.ofMinutes(30);

    private LocalDate date;

    private List<Resource> resources;

    private List<Category> categories;

    private List<Appointment> appointments;

//    public ScheduleModel(LocalDate date, LocalTime startTime, LocalTime endTime, Duration incrementos) {
//        this.date = date;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.increments = incrementos;
//    }
    public ScheduleModel(LocalDate date, List<Resource> resources, List<Category> categories, List<Appointment> appointments) {
        this.date = date;
        this.resources = resources;
        this.categories = categories;
        this.appointments = appointments;
    }

    @Override
    public void visitAppointments(AppointmentVisitor visitor, LocalDate date) {
        for (Appointment appointment : appointments) {
            LocalDate appointmentDate = appointment.getDateTime().toLocalDate();
            if (!appointmentDate.equals(date)) {
                continue;
            }
            visitor.visitAppointment(appointment);
        }
    }

    @Override
    public void visitResources(ResourceVisitor visitor, LocalDate date) {

        for (Resource resource : resources) {
            visitor.visitResource(resource);
        }
    }

    public void visitCategories(CategoryVisitor visitor) {
        for (Category category : categories) {
            visitor.visitCategory(category);
        }
    }

    /**
     * Our model has been told to add a resource to its database. This method
     * will add the resource to the underlying database and then trigger a
     * redraw to any components using this model.
     *
     * @param resource (not null) Resource to add
     * @param date (not null) The date to add the resource to.
     * @param index Position to add the resource, -1 indicates that it should be
     * added a the end.
     */
    public void addResource(Resource resource, LocalDate date, int index) {
        resources.add(resource);
        fireResourceAdded(resource, date, index);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);

        fireAppointmentAdded(appointment);
    }

    public void addCategory(Category category) {
        categories.add(category);
        //TODO: agregar fireproperty de categories;
    }

    public void updateAppointment(Appointment appointment) {
        fireAppointmentUpdated(appointment);
    }

    public void updateResource(Resource resource) {
        fireResourceUpdated(resource);
    }

    public void deleteResource(Resource resource, LocalDate date) {
        resources.remove(resource);
        fireResourceRemoved(resource, date);
    }

    public void deleteAppointment(Appointment appointment) {
        // Remove it from our list
        appointments.remove(appointment);

        // Let any listeners know we have removed this appointment.
        fireAppointmentRemoved(appointment);
    }

    @Override
    public LocalTime getEndTime(LocalDate dateTime) {//TODO:Aqui se settea cuand termina el dia de trabajo
        return endTime;
    }

    @Override
    public LocalTime getStartTime(LocalDate dateTime) {//TODO:Aqui se settea cuand empieza el dia de trabajo
        return startTime;
    }

    @Override
    public Duration getIncrements() {
        return increments;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

}
