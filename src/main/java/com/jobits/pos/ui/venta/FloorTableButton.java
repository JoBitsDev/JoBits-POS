/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta;

import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class FloorTableButton extends JPanel {

    private Mesa m;

    public static final String PROP_CLICK_MESA = "clickMesa";

    private final Color COLOR_VACIA = DefaultValues.PRIMARY_COLOR_LIGHT;
    private final Color COLOR_OCUPADA = Color.YELLOW;
    private final Color COLOR_OCUPADA_PERSONAL = Color.RED;

    public FloorTableButton(Mesa m) {
        super();
        initUI();
        this.m = m;
        m.addPropertyChangeListener((evt) -> {
            if (evt.getPropertyName().equals(Mesa.PROP_ESTADO)) {
                repaint();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(120, 120);//TODO configuracion
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public void update(Graphics g) {
        super.update(g); //To change body of generated methods, choose Tools | Templates.
        labelMesa.setText("Mesa - " + m.getCodMesa());
        labelOrden.setText(m.getEstado());
        if (m.getEstado().equals("vacia")) {
            setBackground(COLOR_VACIA);
        } else if (m.getEstado().split(" ")[1].equals(Application.getInstance().getLoggedUser().getUsuario())) {
            setBackground(COLOR_OCUPADA_PERSONAL);

        } else {
            setBackground(COLOR_OCUPADA);
        }

    }

    private void initUI() {
        setLayout(new BorderLayout());
        labelMesa = new JLabel();
        labelOrden = new JLabel();
        botonAbrir = MaterialComponentsFactory.Buttons.getOutlinedButton();
        add(labelMesa, BorderLayout.NORTH);
        add(labelOrden, BorderLayout.SOUTH);
        add(botonAbrir, BorderLayout.CENTER);
        botonAbrir.addActionListener((e) -> {
            firePropertyChange(PROP_CLICK_MESA, null, m);
        });
        labelMesa.setOpaque(false);
        botonAbrir.setOpaque(false);
        labelOrden.setOpaque(false);
    }

    private JLabel labelMesa, labelOrden;
    private JButton botonAbrir;

}
