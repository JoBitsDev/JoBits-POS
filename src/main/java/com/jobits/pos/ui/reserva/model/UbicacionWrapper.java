/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.model;

import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.ui.scheduler.Availability;
import com.jobits.ui.scheduler.Resource;
import java.awt.Color;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class UbicacionWrapper implements Resource {

    Ubicacion data;

    public UbicacionWrapper(Ubicacion u) {
        this.data = u;
    }

    @Override
    public Iterator<Availability> getAvailability(LocalDate date) {
        ArrayList<Availability> ret = new ArrayList<>();
        Availability av = new Availability(data.getDisponibledesde(),
                Duration.between(data.getDisponibledesde(),
                        data.getDisponiblehasta()));
        ret.add(av);
        return ret.iterator();
    }

    @Override
    public String getTitle() {
        return data.getNombreubicacion();
    }

    @Override
    public Color getColor() {
        return new Color(Integer.parseInt(data.getColorubicacion()));
    }

    public Ubicacion getUbicacion() {
        return data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.data);
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
        final UbicacionWrapper other = (UbicacionWrapper) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
    
    
}
