/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.Border;

/**
 *
 * @author Home
 */
public class ColoredCellRender extends DefaultListCellRenderer {

    private Border unselectedBorder = null;
    private Border selectedBorder = null;
    private boolean isBordered = true;

    @Override
    public void setBackground(Color col) {
        // Ignore setting the background, we will do that
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object color,
            int index, boolean isSelected, boolean cellHasFocus) {
        setText(" ");
        super.setBackground(new Color((Integer) color));
        if (isBordered) {
            if (isSelected) {
                if (selectedBorder == null) {
                    selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, list.getSelectionBackground());
                }
                setBorder(selectedBorder);
            } else {
                if (unselectedBorder == null) {
                    unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, list.getBackground());
                }
                setBorder(unselectedBorder);
            }
        }
        return this;
    }
}
