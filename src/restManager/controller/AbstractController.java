/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller;

import java.util.List;
import restManager.persistencia.beans.AbstractFacade;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractController<T> {

    protected final AbstractFacade<T> dataAccess;
    protected List<T> items = null;
    protected T selected;

    public AbstractController(AbstractFacade dataAccess) {
        this.dataAccess = dataAccess;
       
    }

    //
    // Persist Action
    //
    
    protected void create() {
        persist(PersistAction.CREATE);
    }

    protected void update() {
        persist(PersistAction.UPDATE);
    }

    protected void destroy() {
        persist(PersistAction.DELETE);

    }

    //
    // Getters n Setters
    //
    
    public List<T> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    public AbstractFacade<T> getFacade() {
        return dataAccess;
    }

    
    //
    // Private Methods
    //
    
    private void persist(PersistAction persistAction) {
        if (selected != null) {
            switch(persistAction){
                case CREATE: getFacade().create(selected);break;
                case DELETE : getFacade().remove(selected);break;
                case UPDATE : getFacade().edit(selected);break;
            }
            items = null;

        }
    }
    
    

    //
    // Enum for persist action
    //
    
    public static enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }
}
