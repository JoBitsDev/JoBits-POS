package com.jobits.pos.ui.reserva.model;

import com.jobits.pos.reserva.core.domain.Categoria;
import java.awt.*;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA. User: jgerth Date: 1/25/11 Time: 11:10 PM To change
 * this template use File | Settings | File Templates.
 */
public class CategoriaWrapper implements Category {

    private Categoria data;

    public CategoriaWrapper(Categoria data) {
        this.data = data;
    }

    public Categoria getData() {
        return data;
    }

    /**
     * This model uses categories to color the appointments so this category
     * color determines the appointment color.
     *
     * @return (not null) Color for the category.
     */
    @Override
    public Color getColor() {
        return new Color(data.getColor());
    }

    @Override
    public String getTitle() {
        return data.getNombre();
    }

    @Override
    public String toString() {
        return data.getNombre();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.data);
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
        final CategoriaWrapper other = (CategoriaWrapper) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
    
    
}
