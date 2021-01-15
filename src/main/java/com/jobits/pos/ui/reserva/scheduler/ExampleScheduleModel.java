package com.jobits.pos.ui.reserva.scheduler;

import com.jobits.ui.scheduler.*;
import java.awt.*;
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
public class ExampleScheduleModel extends AbstractScheduleModel {

    public static final LocalDate Today = LocalDate.now();//TODO: AQui se settearia el dia qu ese va a mostar los datos
    public static final LocalDate Tomorrow = Today.plusDays(1);

    private static final List<Resource> TodayResources = new ArrayList<Resource>();
    private static final List<Resource> TomorrowResources = new ArrayList<Resource>();

    private static final List<Appointment> Appointments = new ArrayList<Appointment>();

    // Resources
    private static final ExampleResource Bobby = new ExampleResource("Bobby", new Color(251, 198, 12, 200));
    private static final ExampleResource Johnny = new ExampleResource("Johnny", new Color(12, 251, 160, 200));
    private static final ExampleResource Sally = new ExampleResource("Sally", new Color(166, 251, 12, 200));
    private static final ExampleResource Freddy = new ExampleResource("Freddy", new Color(66, 151, 12, 200));

    // Our categories for our appointments
    private static final ExampleCategory Green = new ExampleCategory("Green", new Color(9, 246, 76, 200));
    private static final ExampleCategory Blue = new ExampleCategory("Blue", new Color(9, 171, 246, 200));

    // This initializes the defaults.
    static {
        TodayResources.add(Bobby);
        TodayResources.add(Johnny);
        TodayResources.add(Sally);

        // Freddy is not going to be listed for today, but an appointment today is going to be assigned
        //  to Freddy.  The appointment should show up in the first column for the day.
        // Populate some default appointments
        Appointments.add(ExampleAppointment.create("Appointment1", Green, Bobby, LocalTime.of(10, 5, 0), 45));
        Appointments.add(ExampleAppointment.create("Appointment2", Blue, Johnny, LocalTime.of(13, 0, 0), 75));
        Appointments.add(ExampleAppointment.create("Appointment3", Blue, Sally, LocalTime.of(8, 0, 0), 60));
        Appointments.add(ExampleAppointment.create("Appointment4", Green, Sally, LocalTime.of(8, 45, 0), 120));
        Appointments.add(ExampleAppointment.create("Appointment5", Blue, Sally, LocalTime.of(10, 45, 0), 30));
        Appointments.add(ExampleAppointment.create("Appointment7", Green, Sally, LocalTime.of(12, 30, 0), 40));
        Appointments.add(ExampleAppointment.create("Appointment8", Blue, Freddy, LocalTime.of(13, 0, 0), 50));
    }

    /**
     * Boring empty constructor for our model.
     */
    public ExampleScheduleModel() {
    }

    @Override
    public void visitAppointments(AppointmentVisitor visitor, LocalDate date) {
        for (Appointment appointment : Appointments) {
            LocalDate appointmentDate = appointment.getDateTime().toLocalDate();
            if (!appointmentDate.equals(date)) {
                continue;
            }
            visitor.visitAppointment(appointment);
        }
    }

    @Override
    public void visitResources(ResourceVisitor visitor, LocalDate date) {
        List<Resource> resources;
        if (date.equals(Today)) {
            resources = TodayResources;
        } else if (date.equals(Tomorrow)) {
            resources = TomorrowResources;
        } else {
            return;
        }

        for (Resource resource : resources) {
            visitor.visitResource(resource);
        }
    }

    public void visitCategories(CategoryVisitor visitor) {
        visitor.visitCategory(Green);
        visitor.visitCategory(Blue);
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
        List<Resource> resources;
        if (date.equals(Today)) {
            resources = TodayResources;
        } else if (date.equals(Tomorrow)) {
            resources = TomorrowResources;
        } else {
            return;
        }

        resources.add(resource);

        fireResourceAdded(resource, date, index);
    }

    public void addAppointment(Appointment appointment) {
        Appointments.add(appointment);

        fireAppointmentAdded(appointment);
    }

    public void updateAppointment(Appointment appointment) {
        fireAppointmentUpdated(appointment);
    }

    public void updateResource(Resource resource) {
        fireResourceUpdated(resource);
    }

    public void deleteResource(Resource resource, LocalDate date) {
        List<Resource> resources;
        if (date.equals(Today)) {
            resources = TodayResources;
        } else if (date.equals(Tomorrow)) {
            resources = TomorrowResources;
        } else {
            return;
        }

        resources.remove(resource);
        fireResourceRemoved(resource, date);
    }

    public void deleteAppointment(Appointment appointment) {
        // Remove it from our list
        Appointments.remove(appointment);

        // Let any listeners know we have removed this appointment.
        fireAppointmentRemoved(appointment);
    }

    @Override
    public LocalTime getEndTime(LocalDate dateTime) {//TODO:Aqui se settea cuand termina el dia de trabajo
        return LocalTime.of(18, 0, 0); // 6 pm
    }

    @Override
    public LocalTime getStartTime(LocalDate dateTime) {//TODO:Aqui se settea cuand empieza el dia de trabajo
        return LocalTime.of(8, 0, 0); // 8 am
    }

}
