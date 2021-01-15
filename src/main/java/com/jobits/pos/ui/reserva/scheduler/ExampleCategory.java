package com.jobits.pos.ui.reserva.scheduler;

import java.awt.*;

/**
 * Created by IntelliJ IDEA. User: jgerth Date: 1/25/11 Time: 11:10 PM To change
 * this template use File | Settings | File Templates.
 */
public class ExampleCategory {

    private final Color _color;
    private final String _title;

    /**
     * Create the category.
     *
     * @param title (not null) Title of the category
     * @param color (not null) Color for the category
     */
    public ExampleCategory(String title, Color color) {
        _title = title;
        _color = color;
    }

    /**
     * This model uses categories to color the appointments so this category
     * color determines the appointment color.
     *
     * @return (not null) Color for the category.
     */
    public Color getColor() {
        return _color;
    }

    public String getTitle() {
        return _title;
    }

    @Override
    public String toString() {
        return _title;
    }
}
