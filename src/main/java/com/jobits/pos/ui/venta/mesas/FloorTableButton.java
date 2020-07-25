/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.mesas;

import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.ui.components.MaterialComponentsFactory;
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
    private final Color COLOR_OCUPADA = Color.YELLOW;
    private final Color COLOR_OCUPADA_PERSONAL = Color.RED;
    private final Icon 
            ICONO_VACIA =null,// MaterialIcons.ADD_CIRCLE_OUTLINE.deriveIconTTF(40f),
            ICONO_OCUPADA = null,//MaterialIcons.BLOCK.deriveIconTTF(40f),
            ICONO_OCUPADA_PERSONAL = null;//MaterialIcons.DETAILS.deriveIconTTF(40f);

    public FloorTableButton(Mesa m) {
        super();
        this.m = m;
        initUI();
        m.addPropertyChangeListener((evt) -> {
            if (evt.getPropertyName().equals(Mesa.PROP_ESTADO)) {
                updateBackbround();
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

    public void setM(Mesa m) {
        this.m = m;
        updateBackbround();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        labelMesa = new JLabel();
        labelMesa.setHorizontalAlignment(SwingConstants.CENTER);
        labelOrden = new JLabel();
        labelOrden.setHorizontalAlignment(SwingConstants.RIGHT);
        botonAbrir = MaterialComponentsFactory.Buttons.getOutlinedButton();
        add(labelMesa, BorderLayout.NORTH);
        add(labelOrden, BorderLayout.SOUTH);
        add(botonAbrir, BorderLayout.CENTER);
        botonAbrir.addActionListener((e) -> {
            firePropertyChange(PROP_CLICK_MESA, null, m);
        });
        updateBackbround();
    }

    private void updateBackbround() {
        setVisible(m != null);
        if (m != null) {
            labelMesa.setText("Mesa - " + m.getCodMesa());
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
