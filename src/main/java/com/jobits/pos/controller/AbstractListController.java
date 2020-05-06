/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.controller;

import com.jobits.pos.ui.AbstractListView;
import java.beans.PropertyChangeEvent;
import com.jobits.pos.persistencia.modelos.AbstractModel;

/**
 * FirstDream
 * @author Jorge
 * @param <T>
 * 
 */
public abstract class AbstractListController<T> extends AbstractDialogController<T>{

    protected AbstractDetailController<T> detailController;

    public AbstractListController(AbstractModel<T> dataAccess) {
        super(dataAccess);
        setDismissOnAction(false);
    }

    @Override
    public void createInstance() {
        detailController = getDetailControllerForNew();
    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        super.
//        switch(evt.getPropertyName()){
//            case "CREATE" : getView().getModel().addrow((T)evt.getNewValue());break;
//            case "DELETE" : getView().getModel().deleteRow((T) evt.getOldValue());break;
//        }
//    }

    @Override
    public void update(T selected) {
        detailController = getDetailControllerForEdit(selected);
    }

    @Override
    public AbstractListView<T> getView() {
        return (AbstractListView<T>) super.getView(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public abstract AbstractDetailController<T> getDetailControllerForNew();

    public abstract AbstractDetailController<T> getDetailControllerForEdit(T selected);
    
    
    
    
    
   

}
