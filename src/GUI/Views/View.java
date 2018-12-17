/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import java.beans.PropertyChangeEvent;
import restManager.controller.AbstractDialogController;
import restManager.controller.Controller;

/**
 *
 * @author Jorge
 */
public interface View {

    public void updateView();

    public void initDefaults();

    public void modelPropertyChange(final PropertyChangeEvent evt);

    public void fetchComponentData();

    public Controller getController();

    public void dispose();

    public enum DialogType {
        /**
         * 400 * 600
         */
        LIST,
        /**
         * 800*600
         */
        NORMAL,
        /**
         * 400*600
         */
        INPUT,
        /**
         * 590 * 700
         */
        INPUT_LARGE,
        /**
         * Defined by the user
         */
        DEFINED
    }
}
