/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class RestManagerCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent component = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // hides yellow selection highlight
        component.setBorder(BorderFactory.createEmptyBorder());
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        return component;
    }

    @Override
    public void setBackground(Color c) {
        super.setBackground(UIManager.getColor("Table.background"));
    }

    @Override
    public void setForeground(Color c) {
        super.setForeground(UIManager.getColor("Table.foreground"));
    }
}
