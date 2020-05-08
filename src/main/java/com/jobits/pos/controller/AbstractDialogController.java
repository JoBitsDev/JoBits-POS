/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller;

import com.jobits.pos.ui.OldAbstractView;
import java.awt.Container;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JOptionPane;
import com.jobits.pos.adapters.repo.AbstractRepository;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.OldView;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractDialogController<T> extends AbstractController<T> {
  
    public AbstractDialogController(AbstractRepository<T> dataAccess) {
        super(dataAccess);
    }

    @Override
    public OldAbstractView getView() {
        return (OldAbstractView) super.getView();
    }

    //
    // Persist Action
    //
    /**
     * this method should be overwritten in case of using a list view and it's
     * function is to create a new item on the list
     */
    public void createInstance() {
        throw new com.jobits.pos.exceptions.DevelopingOperationException();
    }
}
