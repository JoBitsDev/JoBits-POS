/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui;

import java.awt.Container;
import java.beans.PropertyChangeEvent;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.Controller;

/**
 *
 * @author Jorge
 */
public interface OldView {

    public void updateView();

    public void initDefaults();

    public void modelPropertyChange(final PropertyChangeEvent evt);

    public void fetchComponentData();

    public Controller getController();
    
    public Container getContainer();

    public void dispose();

    public enum DialogType {
        /**
         * 400 * 600
         */
        LIST(400,600),
        /**
         * 800*600
         */
        NORMAL(800,600),
        /**
         * 400*600
         */
        INPUT(400,600),
        /**
         * 590 * 700
         */
        INPUT_LARGE(590,650),
        /**
         * Full screen size
         */
        FULL_SCREEN(-1,-1),
        /**
         * Defined by the user
         */
        DEFINED(-1,-1);
        
        
        public final int width,height;

        private DialogType(int width, int height) {
            this.width = width;
            this.height = height;
        }
        
    }
}
