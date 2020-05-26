/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller;

import com.jobits.pos.ui.AbstractDetailView;
import com.jobits.pos.ui.OldAbstractView;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import java.awt.Window;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.adapters.repo.AbstractRepository;

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

    public AbstractDetailController(AbstractRepository<T> dataAccess) {
        super(dataAccess);
        state = State.CREATING;
    }

    public AbstractDetailController(T instance, AbstractRepository<T> model) {
        super(model);
        this.instance = instance;
        if (instance == null) {
            state = State.CREATING;
            this.instance = createNewInstance();
        } else {
            state = State.EDITING;
        }
    }

    public AbstractDetailController(Window parent, AbstractRepository<T> dataAccess) {
        super(dataAccess);
        this.parent = parent;
        new LongProcessActionServiceImpl() {
            @Override
            protected void longProcessMethod() {
                instance = createNewInstance();
            }

            @Override
            protected void whenDone() {
                state = State.CREATING;
                constructView(parent);
            }

        }.performAction(parent);
        // getView().setCreatingMode();
        //getView().setVisible(true);
    }

    public AbstractDetailController(T instance, Window parent, AbstractRepository<T> dataAccess) {
        super(dataAccess);
        this.instance = instance;
        this.parent = parent;
        state = State.EDITING;
        constructView(parent);
        //getView().setEditingMode();
        // getView().setVisible(true);
    }

    public void createUpdateInstance() {
        if (((AbstractDetailView<T>) getView()).validateData()) {
            switch (state) {
                case CREATING:
                    create(instance);
                    break;
                case EDITING:
                    update(instance);
                    break;
            }
        } else {
            throw new ValidatingException();
        }
    }

    public abstract T createNewInstance();

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
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
