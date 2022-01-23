/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.mesas;

import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.ui.components.MaterialComponentsFactory;
import com.jobits.ui.utils.MaterialColor;
import com.root101.swing.material.standards.MaterialIcons;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
    private final Color COLOR_OCUPADA = MaterialColor.YELLOW_400;
    private final Color COLOR_OCUPADA_PERSONAL = MaterialColor.RED_600;
    private final Icon 
            ICONO_VACIA = MaterialIcons.ADD_CIRCLE_OUTLINE.deriveIcon(40f),
            ICONO_OCUPADA = MaterialIcons.BLOCK.deriveIcon(40f),
            ICONO_OCUPADA_PERSONAL = MaterialIcons.DETAILS.deriveIcon(40f);

    public FloorTableButton(Mesa m) {
        super();
        this.m = m;
        initUI();
        m.addPropertyChangeListener((evt) -> {
            if (evt.getPropertyName().equals(Mesa.PROP_ESTADO)) {
                updateBackground();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 150);//TODO configuracion
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public void setM(Mesa m) {
        this.m = m;
        updateBackground();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        labelMesa = new JLabel();
        labelMesa.setHorizontalAlignment(SwingConstants.CENTER);
        labelOrden = new JLabel();
        labelOrden.setHorizontalAlignment(SwingConstants.CENTER);
        botonAbrir = MaterialComponentsFactory.Buttons.getOutlinedButton();
        add(labelMesa, BorderLayout.NORTH);
        add(labelOrden, BorderLayout.SOUTH);
        add(botonAbrir, BorderLayout.CENTER);
        botonAbrir.addActionListener((e) -> {
            firePropertyChange(PROP_CLICK_MESA, null, m);
        });
        updateBackground();
    }

    private void updateBackground() {
        setVisible(m != null);
        if (m != null) {
            labelMesa.setText(m.toString());
            labelOrden.setText(m.getEstado());
            if (m.getEstado().equals("vacia")) {
                setBackground(COLOR_VACIA);
                botonAbrir.setIcon(ICONO_VACIA);
            } else if (m.getEstado().split(" ")[1].equals(Application.getInstance().getLoggedUser().getUsuario())) {
                setBackground(COLOR_OCUPADA_PERSONAL);
                botonAbrir.setIcon(ICONO_OCUPADA_PERSONAL);
            } else {
                setBackground(COLOR_OCUPADA);
                botonAbrir.setIcon(ICONO_OCUPADA);
            }
        }
    }

    private JLabel labelMesa, labelOrden;
    private JButton botonAbrir;

}
