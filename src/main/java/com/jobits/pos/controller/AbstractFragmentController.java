/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller;

import com.jobits.pos.ui.AbstractFragmentView;
import java.awt.Container;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.adapters.repo.AbstractRepository;
import com.jobits.pos.adapters.repo.PropertyName;
import com.jobits.pos.ui.OldView;

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

    public AbstractFragmentController(AbstractRepository<T> dataAccess) {
        super(dataAccess);
    }

    public AbstractFragmentController(T instance, Container parent, AbstractRepository<T> dataAccess) {
        super(dataAccess);
        this.instance = instance;
        this.parent = parent;
    }

    public AbstractFragmentController(T instance, AbstractRepository<T> dataAccess) {
        super(dataAccess);
        this.instance = instance;
    }

    public AbstractFragmentController(Container parent, AbstractRepository<T> dataAccess) {
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

    public void setParent(Container parent) {
        this.parent = parent;
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
