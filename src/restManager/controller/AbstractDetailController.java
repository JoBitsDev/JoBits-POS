/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller;

import GUI.Views.AbstractDetailView;
import GUI.Views.AbstractView;
import java.awt.Window;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.models.AbstractModel;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractDetailController<T> extends AbstractDialogController<T> {

    protected T instance;
    protected State state;
    protected Window parent;

    public AbstractDetailController(AbstractModel<T> dataAccess) {
        super(dataAccess);
        instance = createNewInstance();
        state = State.CREATING;
    }

    public AbstractDetailController(T instance, AbstractModel<T> model) {
        super(model);
        this.instance = instance;
        if (instance == null) {
            state = State.CREATING;
            this.instance = createNewInstance();
        } else {
            state = State.EDITING;
        }
    }

    public AbstractDetailController(Window parent, AbstractModel<T> dataAccess) {
        super(dataAccess);
        this.parent = parent;
        instance = createNewInstance();
        state = State.CREATING;
        constructView(parent);
       // getView().setCreatingMode();
        //getView().setVisible(true);

    }

    public AbstractDetailController(T instance, Window parent, AbstractModel<T> dataAccess) {
        super(dataAccess);
        this.instance = instance;
        this.parent = parent;
        state = State.EDITING;
        constructView(parent);
        //getView().setEditingMode();
       // getView().setVisible(true);
    }

    public void createUpdateInstance() {
        if (((AbstractDetailView<T>)getView()).validateData()) {
            switch (state) {
                case CREATING:
                    create(instance);
                    break;
                case EDITING:
                    update(instance);
                    break;
            }
        }else{
            throw new ValidatingException();
        }
    }

    public abstract T createNewInstance();

    public T getInstance() {
        return instance;
    }

    public State getState() {
        return state;
    }

    public Window getParent() {
        return parent;
    }

    public void setParent(Window parent) {
        this.parent = parent;
    }
   
//    @Override
//    public AbstractDetailView<T> getView() {
//        return (AbstractDetailView<T>)super.getView();
//    }

    public enum State {
        CREATING, EDITING
    }

}
