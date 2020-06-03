/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta;

import com.jobits.pos.domain.models.Mesa;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class FloorTableButton extends JButton {

    private Mesa m;
    
    public FloorTableButton(Mesa m) {
        super();
        this.m = m;
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
    protected void fireActionPerformed(ActionEvent event) {
        event.setSource(m);
        super.fireActionPerformed(event); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
