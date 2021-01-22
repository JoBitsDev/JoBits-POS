package com.jobits.pos.ui.reserva.util;

import com.jobits.ui.scheduler.Appointment;

public interface AppointmentListener {

    /**
     * Method to call when a mouse click has happened on an appointment.
     *
     * @param appointment (not null) The appointment which was clicked on.
     * @param clickCount Click count
     */
    void handleClick(Appointment appointment, int clickCount);

    void handleCheckIn(Appointment appointment);

    void handleCheckOut(Appointment appointment);

    void handleEdit(Appointment appointment);

    void handleCancel(Appointment appointment);
}
