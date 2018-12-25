/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller;

import GUI.Views.AbstractFragmentView;
import GUI.Views.View;
import java.awt.Container;
import java.awt.Window;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.models.AbstractModel;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractFragmentController<T> extends AbstractController<T> {

    protected T instance;
    private Container parent;

    public AbstractFragmentController(AbstractModel<T> dataAccess) {
        super(dataAccess);
    }

    public AbstractFragmentController(T instance, Container parent, AbstractModel<T> dataAccess) {
        super(dataAccess);
        this.instance = instance;
        this.parent = parent;
    }

    public AbstractFragmentController(T instance, AbstractModel<T> dataAccess) {
        super(dataAccess);
        this.instance = instance;
    }

    public AbstractFragmentController(Container parent, AbstractModel<T> dataAccess) {
        super(dataAccess);
        this.parent = parent;
    }

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public Container getParent() {
        return parent;
    }

    
    
    public abstract T createNewInstance();

    @Override
    public void constructView(Container parent) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractFragmentView<T> getView() {
        return (AbstractFragmentView<T>) super.getView(); //To change body of generated methods, choose Tools | Templates.
    }

}
