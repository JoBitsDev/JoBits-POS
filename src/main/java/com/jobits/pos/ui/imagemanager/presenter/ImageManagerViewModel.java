/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.imagemanager.presenter;

import com.jobits.pos.ui.imagemanager.PanelDibujo;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Home
 */
public class ImageManagerViewModel extends AbstractViewModel {

    private PanelDibujo panel_dibujo;

    public static final String PROP_PANEL_DIBUJO = "panel_dibujo";

    private ImageIcon old_image;

    public static final String PROP_OLD_IMAGE = "old_image";

    /**
     * Get the value of old_image
     *
     * @return the value of old_image
     */
    public ImageIcon getOld_image() {
        return old_image;
    }

    /**
     * Set the value of old_image
     *
     * @param old_image new value of old_image
     */
    public void setOld_image(ImageIcon old_image) {
        ImageIcon oldOld_image = this.old_image;
        this.old_image = old_image;
        firePropertyChange(PROP_OLD_IMAGE, oldOld_image, old_image);
    }

    /**
     * Get the value of panel_dibujo
     *
     * @return the value of panel_dibujo
     */
    public PanelDibujo getPanel_dibujo() {
        return panel_dibujo;
    }

    /**
     * Set the value of panel_dibujo
     *
     * @param panel_dibujo new value of panel_dibujo
     */
    public void setPanel_dibujo(PanelDibujo panel_dibujo) {
        PanelDibujo oldPanel_dibujo = this.panel_dibujo;
        this.panel_dibujo = panel_dibujo;
        firePropertyChange(PROP_PANEL_DIBUJO, oldPanel_dibujo, panel_dibujo);
    }

}
