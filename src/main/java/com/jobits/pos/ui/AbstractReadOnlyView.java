/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.ui;


import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.AbstractDetailController;

/**
 * FirstDream
 * @author Jorge
 * @param <T>
 * 
 */
public abstract class AbstractReadOnlyView <T>  extends OldAbstractView{

    protected T instance;

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller) {
        super(DIALOG_TYPE, controller);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner) {
        super(DIALOG_TYPE, controller, owner);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner, boolean modal) {
        super(DIALOG_TYPE, controller, owner, modal);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner, String title) {
        super(DIALOG_TYPE, controller, owner, title);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner, String title, boolean modal) {
        super(DIALOG_TYPE, controller, owner, title, modal);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(DIALOG_TYPE, controller, owner, title, modal, gc);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner) {
        super(DIALOG_TYPE, controller, owner);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, boolean modal) {
        super(DIALOG_TYPE, controller, owner, modal);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, String title) {
        super(DIALOG_TYPE, controller, owner, title);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, String title, boolean modal) {
        super(DIALOG_TYPE, controller, owner, title, modal);
        this.instance = instance;
    }

    public AbstractReadOnlyView(T instance, DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(DIALOG_TYPE, controller, owner, title, modal, gc);
        this.instance = instance;
    }

    public T getInstance() {
        return instance;
    }

    
    
    
    
    
   

    
}
