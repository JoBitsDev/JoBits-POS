/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import restManager.controller.AbstractController;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public abstract class AbstractListDialog<T> extends AbstractDialog{

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller) {
        super(DIALOG_TYPE, controller);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Frame owner) {
        super(DIALOG_TYPE, controller, owner);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Frame owner, boolean modal) {
        super(DIALOG_TYPE, controller, owner, modal);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title) {
        super(DIALOG_TYPE, controller, owner, title);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title, boolean modal) {
        super(DIALOG_TYPE, controller, owner, title, modal);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(DIALOG_TYPE, controller, owner, title, modal, gc);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner) {
        super(DIALOG_TYPE, controller, owner);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, boolean modal) {
        super(DIALOG_TYPE, controller, owner, modal);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title) {
        super(DIALOG_TYPE, controller, owner, title);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title, boolean modal) {
        super(DIALOG_TYPE, controller, owner, title, modal);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(DIALOG_TYPE, controller, owner, title, modal, gc);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Window owner) {
        super(DIALOG_TYPE, controller, owner);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Window owner, ModalityType modalityType) {
        super(DIALOG_TYPE, controller, owner, modalityType);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title) {
        super(DIALOG_TYPE, controller, owner, title);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title, ModalityType modalityType) {
        super(DIALOG_TYPE, controller, owner, title, modalityType);
    }

    public AbstractListDialog(DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(DIALOG_TYPE, controller, owner, title, modalityType, gc);
    }
    
    
    
}
