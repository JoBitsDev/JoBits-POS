package com.jobits.pos.ui.reserva.model;

import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Example Appointment
 *
 * @author Joshua Gerth - jgerth@thirdnf.com
 */
public class ReservaWrapper implements Appointment {

    private Category _category;
    private Resource _resource;
    private Reserva reserva;

    public ReservaWrapper(Reserva reserva, Category category, Resource resource) {
        this.reserva = reserva;
        _category = category;
        _resource = resource;
    }

    public Category getCategory() {
        return _category;
    }

    public void setCategory(Category category) {
        _category = category;
    }

    public Reserva getReserva() {
        return reserva;
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(reserva.getFechareserva(), reserva.getHorareserva());
    }

    @Override
    public Resource getResource() {
        return _resource;
    }

    @Override
    public Duration getDuration() {
        return Duration.ofSeconds(reserva.getDuracionreservasegundos());
    }

    @Override
    public String getTitle() {
        if (reserva.getNotasreserva() != null) {
            return reserva.getNotasreserva();
        } else {
            return "No Title";
        }
    }

    public static ReservaWrapper create(
            Reserva reserva,
            Category category,
            Resource resource) {

        ReservaWrapper appointment = new ReservaWrapper(reserva, category, resource);
        return appointment;
    }
}
