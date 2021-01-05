/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller;

import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import com.jobits.pos.exceptions.HiddenException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.adapters.repo.impl.AbstractRepository;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.OldView;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractController<T> implements Controller {

    private AbstractRepository<T> model;
    protected List<T> items = null;
    protected T selected;
    private OldView view;
    protected boolean dismissOnAction = true;
    protected boolean showDialogs = true;

    public AbstractController(AbstractRepository<T> dataAccess) {
        model = dataAccess;
        setPropertyChangeMethods();
    }

    //
    //Public Methods
    //
    /**
     *
     * @param parent the value of parent
     */
    public abstract void constructView(java.awt.Container parent);

    public OldView getView() {
        return view;
    }

    public void setView(OldView view) {
        this.view = view;

    }

    //
    //Property Change Methods
    //
    public void setPropertyChangeMethods() {
        if (model != null) {
            model.addPropertyChangeListener(this);
        }
    }

    // Use this to observe property changes from registered models // and propagate them on to all the views.
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("U: " + R.loggedUser + ": " + evt.getPropertyName() + " - (nuevo):" + evt.getNewValue() + " - (viejo):" + evt.getOldValue()
                + " \n      Origen: " + getClass().getName());
        if (view != null) {
            System.out.println(view.getClass().toString());
            items = null;
            view.updateView();
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
//        for (AbstractRepository m : registeredModels) {
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
        if (showDialogs) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"),
                    R.RESOURCE_BUNDLE.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE,
                    new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/exitoso.png")));
        }
    }

    protected void showSuccessDialog(Container view, String text) {
        if (showDialogs) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), text,
                    R.RESOURCE_BUNDLE.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE,
                    new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/exitoso.png")));
        }
    }

    protected boolean showConfirmDialog(Container view) {
        return showDialogs ? JOptionPane.showConfirmDialog(Application.getInstance().getMainWindow(), R.RESOURCE_BUNDLE.getString("desea_aplicar_cambios"),
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")))
                == JOptionPane.YES_OPTION : true;
    }

    protected boolean showConfirmDialog(Container view, String text) {
        return showDialogs ? JOptionPane.showConfirmDialog(Application.getInstance().getMainWindow(), text,
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")))
                == JOptionPane.YES_OPTION : true;
    }

    protected boolean showEditingDialog(Container view, Object obj) {
        return showDialogs ? JOptionPane.showConfirmDialog(Application.getInstance().getMainWindow(), R.RESOURCE_BUNDLE.getString("desea_editar_datos") + obj.toString(),
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")))
                == JOptionPane.YES_OPTION : true;
    }

    protected void showErrorDialog(Container view, String errorText) {
        if (showDialogs) {
            JOptionPane.showMessageDialog(view, errorText,
                    R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE,
                    new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/alerta.png")));
        }
    }

    protected boolean showDeleteDialog(Container view, Object obj) {
        return showDialogs ? JOptionPane.showConfirmDialog(Application.getInstance().getMainWindow(), R.RESOURCE_BUNDLE.getString("desea_borrar_datos") + obj.toString(),
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/eliminar.png")))
                == JOptionPane.YES_OPTION : true;
    }

    protected String showInputDialog(Container view, String text) {
        String ret = JOptionPane.showInputDialog(Application.getInstance().getMainWindow(), text, "Entrada", JOptionPane.QUESTION_MESSAGE);
        if (ret != null) {
            return ret;
        } else {
            return "";
        }
    }

    protected String showInputDialog(Container view, String text, Object defaultValue) {
        String ret = JOptionPane.showInputDialog(Application.getInstance().getMainWindow(), text, defaultValue);
        if (ret != null) {
            return ret;
        } else {
            return "";
        }
    }

    protected Object showInputDialog(Container view, String msg, String title, Object[] selections, Object initialValue) {
        Object ret = JOptionPane.showInputDialog(Application.getInstance().getMainWindow(), msg, title,
                JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")),
                selections, initialValue);
        if (ret != null) {
            return ret;
        } else {
            return "";
        }
    }

    //
    // Persist Action
    //
    protected void create() {
        persist(PersistAction.CREATE);
    }

    /**
     *
     * @param selected
     */
    public void create(T selected) {
        if (showConfirmDialog((Container) getView())) {
            this.selected = selected;
            this.create();
            showSuccesDialogAndDismiss();
        }
    }

    public void create(T selected, boolean quietMode) {
        boolean previousValue = this.showDialogs;
        setShowDialogs(!quietMode);
        this.create(selected);
        setShowDialogs(previousValue);
    }

    public void update() {
        persist(PersistAction.UPDATE);
    }

    /**
     *
     * @param selected
     */
    public void update(T selected) {
        if (showEditingDialog((Container) getView(), selected)) {
            this.selected = selected;
            this.update();
            showSuccesDialogAndDismiss();
        }
    }

    public void update(T selected, boolean quietMode) {
        boolean previousValue = this.showDialogs;
        setShowDialogs(!quietMode);
        update(selected);
        setShowDialogs(previousValue);
    }

    protected void destroy() {
        persist(PersistAction.DELETE);

    }

    /**
     *
     * @param selected
     */
    public void destroy(T selected) {
        if (showDeleteDialog((Container) getView(), selected)) {
            this.selected = selected;
            this.destroy();
            this.selected = null;
            showSuccesDialogAndDismiss();
        }
    }

    public void destroy(T selected, boolean quietMode) {
        boolean previousValue = this.showDialogs;
        setShowDialogs(!quietMode);
        destroy(selected);
        setShowDialogs(previousValue);
    }

    //
    // Getters n Setters
    //
    public List<T> getItems() {
        return getModel().findAll();
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    @Override
    public AbstractRepository<T> getModel() {
        return model;
    }

    public void setModel(AbstractRepository<T> model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public boolean isShowDialogs() {
        return showDialogs;
    }

    public void setShowDialogs(boolean showDialogs) {
        this.showDialogs = showDialogs;
    }

    public boolean isDismissOnAction() {
        return dismissOnAction;
    }

    public void setDismissOnAction(boolean dismissOnAction) {
        this.dismissOnAction = dismissOnAction;
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
                    selected = null;
                    break;
                case UPDATE:
                    getModel().edit(selected);
                    break;
            }
            getModel().commitTransaction();
            // getModel().getEntityManager().getEntityManagerFactory().getCache().evictAll();
        }
    }

    private void showSuccesDialogAndDismiss() {
        if (showDialogs) {
            showSuccessDialog((Container) getView());
            if (getView() != null && dismissOnAction && showDialogs) {
                getView().dispose();
            }
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
