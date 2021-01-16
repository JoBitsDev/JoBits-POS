package com.jobits.pos.ui.reserva.model;

import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * Example Appointment
 *
 * @author Joshua Gerth - jgerth@thirdnf.com
 */
public class ReservaWrapper implements Appointment {

    private Category _category;
    private Resource _resource;
    private Reserva ubicacion;

    public ReservaWrapper(Reserva ubicacion, Category category, Resource resource) {
        this.ubicacion = ubicacion;
        _category = category;
        _resource = resource;
    }

    public Category getCategory() {
        return _category;
    }

    public void setCategory(Category category) {
        _category = category;
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(ubicacion.getFechareserva(), ubicacion.getHorareserva());
    }

    @Override
    public Resource getResource() {
        return _resource;
    }

    @Override
    public Duration getDuration() {
        return Duration.ofSeconds(ubicacion.getDuracionreservasegundos());
    }

    @Override
    public String getTitle() {
        return ubicacion.getNotasreserva();
    }

    public static ReservaWrapper create(
            Reserva reserva,
            Category category,
            Resource resource) {

        ReservaWrapper appointment = new ReservaWrapper(reserva, category, resource);
        return appointment;
    }
}
