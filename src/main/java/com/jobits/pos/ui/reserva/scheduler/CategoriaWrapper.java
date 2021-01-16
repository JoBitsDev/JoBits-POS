package com.jobits.pos.ui.reserva.scheduler;

import com.jobits.pos.reserva.core.domain.Categoria;
import java.awt.*;

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
}
