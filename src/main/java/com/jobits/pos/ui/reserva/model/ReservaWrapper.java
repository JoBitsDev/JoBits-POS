package com.jobits.pos.ui.reserva.model;

import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

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
        return Duration.ofMinutes(reserva.getDuracionMinutos());
    }

    @Override
    public String getTitle() {
        return reserva.getNotasreserva() + " (" + reserva.getDuracionMinutos() + " mins)";
    }

    public static ReservaWrapper create(
            Reserva reserva,
            Category category,
            Resource resource) {

        ReservaWrapper appointment = new ReservaWrapper(reserva, category, resource);
        return appointment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this._category);
        hash = 67 * hash + Objects.hashCode(this._resource);
        hash = 67 * hash + Objects.hashCode(this.reserva);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservaWrapper other = (ReservaWrapper) obj;
        if (!Objects.equals(this._category, other._category)) {
            return false;
        }
        if (!Objects.equals(this._resource, other._resource)) {
            return false;
        }
        if (!Objects.equals(this.reserva, other.reserva)) {
            return false;
        }
        return true;
    }

}
