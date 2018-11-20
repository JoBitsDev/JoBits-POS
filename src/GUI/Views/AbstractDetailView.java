/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Views;


import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDetailController;

/**
 * FirstDream
 * @author Jorge
 * @param <T>
 * 
 */
public abstract class AbstractDetailView <T>  extends AbstractView{

    protected T instance;

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller) {
        super(DIALOG_TYPE, controller);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Frame owner) {
        super(DIALOG_TYPE, controller, owner);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Frame owner, boolean modal) {
        super(DIALOG_TYPE, controller, owner, modal);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title) {
        super(DIALOG_TYPE, controller, owner, title);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title, boolean modal) {
        super(DIALOG_TYPE, controller, owner, title, modal);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(DIALOG_TYPE, controller, owner, title, modal, gc);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Dialog owner) {
        super(DIALOG_TYPE, controller, owner);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, boolean modal) {
        super(DIALOG_TYPE, controller, owner, modal);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title) {
        super(DIALOG_TYPE, controller, owner, title);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title, boolean modal) {
        super(DIALOG_TYPE, controller, owner, title, modal);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(DIALOG_TYPE, controller, owner, title, modal, gc);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Window owner) {
        super(DIALOG_TYPE, controller, owner);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Window owner, ModalityType modalityType) {
        super(DIALOG_TYPE, controller, owner, modalityType);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title) {
        super(DIALOG_TYPE, controller, owner, title);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title, ModalityType modalityType) {
        super(DIALOG_TYPE, controller, owner, title, modalityType);
        this.instance = instance;
    }

    public AbstractDetailView(T instance, DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(DIALOG_TYPE, controller, owner, title, modalityType, gc);
        this.instance = instance;
    }

  

    @Override
    public AbstractDetailController<T> getController() {
        return (AbstractDetailController<T>) controller;
    }
    
    public abstract void setEditingMode();
    
    public abstract void setCreatingMode();
    
    /**
     * Cheks that the data inputted by the user matches the constrains defined in the database
     * this method also writes the instance field as the validation occur
     * @return true if the data is valid otherwise false
     */
    public abstract boolean validateData();
    
   

    
}
