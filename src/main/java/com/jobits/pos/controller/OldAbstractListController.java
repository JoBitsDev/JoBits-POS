/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.controller;

import com.jobits.pos.ui.OldAbstractListView;
import java.beans.PropertyChangeEvent;
import com.jobits.pos.adapters.repo.AbstractRepository;

/**
 * FirstDream
 * @author Jorge
 * @param <T>
 * 
 */
public abstract class OldAbstractListController<T> extends AbstractDialogController<T>{

    protected AbstractDetailController<T> detailController;

    public OldAbstractListController(AbstractRepository<T> dataAccess) {
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
    public OldAbstractListView<T> getView() {
        return (OldAbstractListView<T>) super.getView(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public abstract AbstractDetailController<T> getDetailControllerForNew();

    public abstract AbstractDetailController<T> getDetailControllerForEdit(T selected);
    
    
    
    
    
   

}
