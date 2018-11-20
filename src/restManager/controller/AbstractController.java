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
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.PropertyName;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractController<T> implements PropertyChangeListener {

    private final AbstractModel<T> model;
    protected List<T> items = null;
    protected T selected;
    private AbstractView view;

    public AbstractController(AbstractModel<T> dataAccess) {
        model = dataAccess;
        setPropertyChangeMethods();
    }

    //
    //Public Methods
    //
    public abstract void constructView(Window parent);

    public AbstractView getView() {
        return view;
    }

    public void setView(AbstractView view) {
        this.view = view;
     
    }

    //
    //Property Change Methods
    //

    public void setPropertyChangeMethods(){
        if(model != null){
            model.addPropertyChangeListener(this);
        }
    }

    // Use this to observe property changes from registered models // and propagate them on to all the views.
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropagationId());
        System.out.println(evt.getPropertyName());
        System.out.println(evt.getSource());
        
        switch(evt.getPropertyName()){
            //case "VIEW_CREATE" : create(selected);break;
            //case "VIEW_UPDATE" : update(selected);break;
            //case "VIEW_DELETE" : destroy(selected);break;
            default : view.updateView();
        }
      
      
    }

    /**
     * This to fire property changes back to the models. This method uses
     * reflection to inspect each of the model classes to determine whether it
     * is the owner of the property in question. If it isn't, a
     * NoSuchMethodException is thrown, which the method ignores.
     *
     *
     * @param propertyName = The name of the property.
     * @param newValue = An object that represents the new value of the
     * property.
     */
    protected void setModelProperty(String propertyName, Object newValue) {
//        for (AbstractModel m : registeredModels) {
//            try {
//                Method method = m.getClass().getMethod("set" + propertyName, new Class[]{newValue.getClass()});
//                method.invoke(m, newValue);
//            } catch (Exception ex) {
//                // Handle exception.
//            }
//        }
    }

    //
    //Protected Methods
    //
    protected void showSuccessDialog(Container view) {
        JOptionPane.showMessageDialog(view, R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"),
                R.RESOURCE_BUNDLE.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE);
    }

    protected boolean showConfirmDialog(Container view) {
        return JOptionPane.showConfirmDialog(view, R.RESOURCE_BUNDLE.getString("desea_aplicar_cambios"),
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION;
    }

    protected boolean showEditingDialog(Container view, Object obj) {
        return JOptionPane.showConfirmDialog(view, R.RESOURCE_BUNDLE.getString("desea_editar_datos" + obj.toString()),
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION;
    }

    protected void showErrorDialog(Container view, String errorText) {
        JOptionPane.showMessageDialog(view, errorText,
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE);
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

    protected void create() {
        persist(PersistAction.CREATE);
    }

    /**
     *
     * @param selected
     */
    public void create(T selected) {
        if (showConfirmDialog(getView())) {
            this.selected = selected;
            this.create();
            this.selected = null;
            showSuccessDialog(getView());
        }
    }

    public void update() {
        persist(PersistAction.UPDATE);
    }

    /**
     *
     * @param selected
     */
    public void update(T selected) {
        if (showEditingDialog(getView(), selected)) {
            this.selected = selected;
            this.update();
            this.selected = null;
            showSuccessDialog(getView());
        }
    }

    protected void destroy() {
        persist(PersistAction.DELETE);

    }

    /**
     *
     * @param selected
     */
    public void destroy(T selected) {
        if (showConfirmDialog(getView())) {
            this.selected = selected;
            this.destroy();
            this.selected = null;
            showSuccessDialog(getView());
        }
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

    public AbstractModel<T> getModel() {
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
