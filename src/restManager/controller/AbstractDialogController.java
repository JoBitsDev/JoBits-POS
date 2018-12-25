/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller;

import GUI.Views.AbstractView;
import GUI.Views.View;
import java.awt.Container;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JOptionPane;
import restManager.persistencia.models.AbstractModel;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractDialogController<T> extends AbstractController<T> {

    public AbstractDialogController(AbstractModel<T> dataAccess) {
        super(dataAccess);
    }

    @Override
    public AbstractView getView() {
        return (AbstractView) super.getView();
    }

    //
    // Persist Action
    //
    /**
     * this method should be overwritten in case of using a list view and it's
     * function is to create a new item on the list
     */
    public void createInstance() {
        throw new restManager.exceptions.DevelopingOperationException();
    }
}
