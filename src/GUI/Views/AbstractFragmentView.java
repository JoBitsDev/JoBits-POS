/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import java.awt.Container;
import java.beans.PropertyChangeEvent;
import javax.swing.JPanel;
import restManager.controller.Controller;
import restManager.exceptions.DevelopingOperationException;
import restManager.util.ComponentMover;

/**
 * FirstDream
 *
 * @author Jorge
 * @param <T>
 *
 */
public abstract class AbstractFragmentView<T> extends JPanel implements View {

    final Controller controller;
    private Container parentComponent;

    public AbstractFragmentView(Controller controller) {
        super();
        this.controller = controller;

    }

    public AbstractFragmentView(Controller controller, Container parentComponent) {
        super();
        this.controller = controller;
        setParentComponent(parentComponent);
    }

    @Override
    public void initDefaults() {
        ComponentMover cr = new ComponentMover(this, this);
    }

    public Container getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(Container parentComponent) {
        if (this.parentComponent != null) {
            this.parentComponent.remove(this);
        }
        this.parentComponent = parentComponent;
        this.parentComponent.add(this);

    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        //TODO: do something with this
    }

    @Override
    public void fetchComponentData() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Controller getController() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        setVisible(false);
    }

    
    
}
