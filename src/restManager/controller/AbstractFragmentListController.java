/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller;

import GUI.Views.AbstractFragmentView;
import GUI.Views.AbstractListView;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.util.List;
import restManager.persistencia.models.AbstractModel;

/**
 * FirstDream
 * @author Jorge
 * @param <T>
 * 
 */
public abstract class AbstractFragmentListController<T> extends AbstractController<T>{

    Container parent;
    List<T> list;
    
 public AbstractFragmentListController(AbstractModel<T> dataAccess) {
        super(dataAccess);
    }

    public AbstractFragmentListController(List<T> list, Container parent, AbstractModel<T> dataAccess) {
        super(dataAccess);
        this.list = list;
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

  

    @Override
    public AbstractFragmentView<T> getView() {
        return (AbstractFragmentView<T>) super.getView(); //To change body of generated methods, choose Tools | Templates.
    }
    
public abstract T createNewInstanceAndAdd();

public abstract T editInstance(T instance);
    
    
   

}
