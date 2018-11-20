/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller;

import restManager.persistencia.models.AbstractModel;

/**
 * FirstDream
 * @author Jorge
 * @param <T>
 * 
 */
public abstract class AbstractListController<T> extends AbstractController<T>{

    protected AbstractDetailController<T> detailController;

    public AbstractListController(AbstractModel<T> dataAccess) {
        super(dataAccess);
    }

    @Override
    public void createInstance() {
        detailController = getDetailControllerForNew();
        items = getItems();
        items.add(detailController.getInstance());
        getView().updateView();
    }

    @Override
    public void update(T selected) {
        detailController = getDetailControllerForEdit(selected);
        getView().updateView();
    }
    
    public abstract AbstractDetailController<T> getDetailControllerForNew();

    public abstract AbstractDetailController<T> getDetailControllerForEdit(T selected);
    
    
    
    
    
   

}
