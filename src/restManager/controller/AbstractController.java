/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller;

import java.awt.Container;
import java.awt.Window;
import java.util.List;
import javax.swing.JOptionPane;
import restManager.persistencia.models.AbstractFacade;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractController<T> {

    protected final AbstractFacade<T> model;
    protected List<T> items = null;
    protected T selected;

    public AbstractController(AbstractFacade dataAccess) {
        this.model = dataAccess;
        
    }

    public void createInstance(){
        
    }
    
    public void deleteInstance(T selected){
        
    }
    
    public void editInstance(T selected){
        
    }
    //
    //Protected Methods
    //
    public abstract void constructView(Window parent);

    protected void showSuccessDialog(Container view) {
        JOptionPane.showMessageDialog(view, R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"),
                R.RESOURCE_BUNDLE.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE);
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
            items = getModel().findAll();
        }
        return items;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    public AbstractFacade<T> getModel() {
        return model;
    }

    //
    // Private Methods
    //
    private void persist(PersistAction persistAction) {
        if (selected != null) {
            getModel().startTransaction();
            switch (persistAction) {
                case CREATE:
                    getModel().create(selected);
                    break;
                case DELETE:
                    getModel().remove(selected);
                    break;
                case UPDATE:
                    getModel().edit(selected);
                    break;
            }
            getModel().commitTransaction();
            selected = null;
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
