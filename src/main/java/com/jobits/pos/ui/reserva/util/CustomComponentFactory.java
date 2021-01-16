package com.jobits.pos.ui.reserva.util;

import com.jobits.pos.ui.reserva.util.AppointmentListener;
import com.jobits.pos.ui.reserva.util.ResourceListener;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import com.jobits.ui.scheduler.components.AbstractAppointmentComponent;
import com.jobits.ui.scheduler.components.AbstractResourceComponent;
import com.jobits.ui.scheduler.components.BasicComponentFactory;

/**
 * This is the example component factory which is used to create custom resource
 * and appointment components.
 *
 * @author Joshua Gerth - jgerth@thirdnf.com
 */
public class CustomComponentFactory extends BasicComponentFactory {

    // The appointment listener to call for mouse clicks on the appointments.
    private AppointmentListener _appointmentListener;

    // The resource listener to call for mouse clicks on the resources.
    private ResourceListener _resourceListener;

    @Override

    public AbstractResourceComponent makeResourceComponent(Resource resource) {
        ResourceComponent component = new ResourceComponent(resource);
        component.setResourceListener(_resourceListener);

        return component;
    }

    @Override

    public AbstractAppointmentComponent makeAppointmentComponent(Appointment appointment) {
        AppointmentComponent component = new AppointmentComponent(appointment);
        component.setAppointmentListener(_appointmentListener);

        return component;
    }

    /**
     * Set the appointment listener. This will be passed into every appointment
     * component which is created.
     *
     * @param appointmentListener (not null) The listener to call for mouse
     * clicks on the appointments.
     */
    public void setAppointmentListener(AppointmentListener appointmentListener) {
        _appointmentListener = appointmentListener;
    }

    public void setResourceListener(ResourceListener resourceListener) {
        _resourceListener = resourceListener;
    }
}
