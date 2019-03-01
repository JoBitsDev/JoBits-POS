/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller;

import GUI.Views.View;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.JOptionPane;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.models.AbstractModel;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractController<T> implements Controller {

    private AbstractModel<T> model;
    protected List<T> items = null;
    protected T selected;
    private View view;
    private boolean dismissOnAction = true;
    private boolean showDialogs = true;

    public AbstractController(AbstractModel<T> dataAccess) {
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

    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
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
        System.out.println(evt.getPropagationId());
        System.out.println(evt.getPropertyName());
        System.out.println(evt.getSource());
        System.out.println(getClass().toString());
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
                R.RESOURCE_BUNDLE.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/exitoso.png")));
    }

    protected void showSuccessDialog(Container view, String text) {
        JOptionPane.showMessageDialog(view, text,
                R.RESOURCE_BUNDLE.getString("label_informacion"), JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/exitoso.png")));
    }

    protected boolean showConfirmDialog(Container view) {
        return showDialogs ? JOptionPane.showConfirmDialog(view, R.RESOURCE_BUNDLE.getString("desea_aplicar_cambios"),
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")))
                == JOptionPane.YES_OPTION : true;
    }

    protected boolean showConfirmDialog(Container view, String text) {
        return showDialogs ? JOptionPane.showConfirmDialog(view, text,
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")))
                == JOptionPane.YES_OPTION : true;
    }

    protected boolean showEditingDialog(Container view, Object obj) {
        return showDialogs ? JOptionPane.showConfirmDialog(view, R.RESOURCE_BUNDLE.getString("desea_editar_datos") + obj.toString(),
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")))
                == JOptionPane.YES_OPTION : true;
    }

    protected void showErrorDialog(Container view, String errorText) {
        JOptionPane.showMessageDialog(view, errorText,
                R.RESOURCE_BUNDLE.getString("label_error"), JOptionPane.ERROR_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/alerta.png")));
    }

    protected boolean showDeleteDialog(Container view, Object obj) {
        return showDialogs ? JOptionPane.showConfirmDialog(view, R.RESOURCE_BUNDLE.getString("desea_borrar_datos") + obj.toString(),
                R.RESOURCE_BUNDLE.getString("label_confirmacion"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/eliminar.png")))
                == JOptionPane.YES_OPTION : true;
    }

    protected String showInputDialog(Container view, String text) {
        String ret = JOptionPane.showInputDialog(view, text, "Entrada", JOptionPane.QUESTION_MESSAGE);
        if (ret != null) {
            return ret;
        } else {
            throw new ValidatingException(view);
        }
    }

    protected String showInputDialog(Container view, String text, Object defaultValue) {
        String ret = JOptionPane.showInputDialog(view, text, defaultValue);
        if (ret != null) {
            return ret;
        } else {
            throw new ValidatingException(view);
        }
    }

    protected Object showInputDialog(Container view, String msg, String title, Object[] selections, Object initialValue) {
        Object ret = JOptionPane.showInputDialog(view, msg, title,
                JOptionPane.QUESTION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/pregunta.png")),
                selections, initialValue);
        if (ret != null) {
            return ret;
        } else {
            throw new ValidatingException(view);
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
        create(selected);
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

    @Override
    public AbstractModel<T> getModel() {
        return model;
    }

    public void setModel(AbstractModel<T> model) {
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
            try {
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
                getModel().getEntityManager().getEntityManagerFactory().getCache().evict(getModel().getClass());
                getModel().commitTransaction();
            }catch(Exception e ){
                showErrorDialog((Container)getView(), "No Pueden Existir duplicados en la base de datos \n" + e.getMessage() );
                getModel().getEntityManager().getTransaction().rollback();
            }
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
