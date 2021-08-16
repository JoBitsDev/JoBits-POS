package com.jobits.pos.ui.reserva.model;

import com.jobits.pos.cliente.core.domain.ClienteDomain;
import com.jobits.pos.cliente.core.usecase.ClienteUseCase;
import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.reserva.core.domain.ReservaEstado;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.awt.Color;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private ClienteUseCase clienteService = PosDesktopUiModule.getInstance().getImplementation(ClienteUseCase.class);

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
        if (reserva.getNotasreserva() != null) {
            return reserva.getNotasreserva();
        } else {
            return " ";
        }
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

    @Override
    public String getDescription() {
        String nombre = "Sin Nombre", checkin = "-:-- xx", checkout = "-:-- xx", orden = "O-xxxxx", cliente = "---";
        if (reserva.getNotasreserva() != null) {
            nombre = reserva.getNotasreserva();
        }
        if (reserva.getCheckin() != null) {
            checkin = reserva.getCheckin().format(DateTimeFormatter.ofPattern("h:mm a"));
        }
        if (reserva.getCheckout() != null) {
            checkout = reserva.getCheckout().format(DateTimeFormatter.ofPattern("h:mm a"));
        }
        if (reserva.getNumeroPedidoAsociado() != null) {
            orden = reserva.getNumeroPedidoAsociado();
        }
        if (reserva.getClienteidcliente() != null) {
            ClienteDomain c = clienteService.findBy(reserva.getClienteidcliente());
            if (c != null) {
                cliente = c.toString();
            }
        }
        return "<html>"
                + "Nombre: " + nombre + "<br>"
                + "Cliente: " + cliente + "<br>"
                + "Estado: " + reserva.getEstado() + "<br>"
                + "Hora: " + reserva.getHorareserva().format(DateTimeFormatter.ofPattern("h:mm a")) + "<br>"
                + "Duracion: " + reserva.getDuracionMinutos() + " minutos" + "<br>"
                + "CheckIn: " + checkin + "<br>"
                + "CheckOut: " + checkout + "<br>"
                + "Orden: " + orden + "<br>"
                + "</html>";
    }

    @Override
    public Color getColorStatus() {
        String status = reserva.getEstado();
        if (status.equals(ReservaEstado.AGENDADA.toString())) {
            return Color.yellow;
        } else if (status.equals(ReservaEstado.CHEQUEADA.toString())) {
            return Color.green;
        } else if (status.equals(ReservaEstado.COMPLETADA.toString())) {
            return Color.blue;
        } else if (status.equals(ReservaEstado.CANCELADA.toString())) {
            return Color.red;
        } else if (status.equals(ReservaEstado.RECHAZADA.toString())) {
            return Color.darkGray;
        } else {
            return Color.black;
        }
    }

    @Override
    public boolean cheackIn() {
        return reserva.getCheckin() != null;
    }

}
