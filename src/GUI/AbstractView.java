/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import javax.swing.JDialog;
import restManager.controller.AbstractController;
import restManager.exceptions.DevelopingOperationException;
import restManager.resources.values.Fonts;
import restManager.util.ComponentMover;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public abstract class AbstractView extends JDialog {

    private final DialogType DIALOG_TYPE;
    private final AbstractController controller;

    //
    // Contructors
    //
    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller) {
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Frame owner) {
        super(owner);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Frame owner, boolean modal) {
        super(owner, modal);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title) {
        super(owner, title);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner) {
        super(owner);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, boolean modal) {
        super(owner, modal);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title) {
        super(owner, title);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Window owner) {
        super(owner);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Window owner, ModalityType modalityType) {
        super(owner, modalityType);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title) {
        super(owner, title);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractController controller, Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    //
    //Protected Methods
    //
    
    public void fetchComponentData(){
        
    }
    
    
    //
    // Getters n Setters
    //
    public AbstractController getController() {
        return controller;
    }

    //
    // Override Methods
    //
    @Override
    public Dimension getMaximumSize() {
        return super.getMaximumSize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension getMinimumSize() {
        switch (DIALOG_TYPE) {
            case NORMAL:
                return new Dimension(800, 600);
            case LIST:
                return new Dimension(400, 600);
            case INPUT_LARGE:
                return new Dimension(590, 600);
            case INPUT:
                return new Dimension(WIDTH, HEIGHT);
            default:
                return super.getMinimumSize();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Font getFont() {
        return Fonts.BODY;
    }

    //
    //Private Methods
    //
    
    private void initDefaults() {
        setMaximumSize(getMaximumSize());
        setMinimumSize(getMinimumSize());
        setPreferredSize(getPreferredSize());
        setFont(getFont());
        ComponentMover cr = new ComponentMover(this, this);
    }

    public enum DialogType {
        LIST, NORMAL, INPUT, INPUT_LARGE,DEFINED
    }

}
