/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Views;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import javax.swing.JDialog;
import restManager.controller.AbstractDialogController;
import restManager.controller.Controller;
import restManager.exceptions.DevelopingOperationException;
import restManager.resources.values.Fonts;
import restManager.util.ComponentMover;
import restManager.util.ComponentResizer;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public abstract class AbstractView extends JDialog implements View {

    private final DialogType DIALOG_TYPE;
    final Controller controller;
    //
    // Contructors
    //

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller) {
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();

    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner) {
        super(owner);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner, boolean modal) {
        super(owner, modal);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner, String title) {
        super(owner, title);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner) {
        super(owner);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, boolean modal) {
        super(owner, modal);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, String title) {
        super(owner, title);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    public AbstractView(DialogType DIALOG_TYPE, AbstractDialogController controller, Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        this.DIALOG_TYPE = DIALOG_TYPE;
        this.controller = controller;
        initDefaults();
    }

    //
    //Protected Methods
    //
    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {
        //TODO: do something with this
    }

    /**
     * Needs to be overwritten
     */
    @Override
    public void fetchComponentData() {
        throw new DevelopingOperationException();
    }

    @Override
    public abstract void updateView();

    //
    // Getters n Setters
    //
    @Override
    public Controller getController() {
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
        Dimension windows_size = new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
        switch (DIALOG_TYPE) {
            case NORMAL:
            case LIST:
            case INPUT_LARGE:
            case INPUT:
                return new Dimension(
                        DIALOG_TYPE.width > windows_size.width ? windows_size.width : DIALOG_TYPE.width,
                        DIALOG_TYPE.height > windows_size.height ? windows_size.height : DIALOG_TYPE.height);
            case FULL_SCREEN:
                return windows_size;
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
    @Override
    public Container getContainer() {
        return this;
    }

    @Override
    public void initDefaults() {
        setMaximumSize(getMaximumSize());
        setMinimumSize(getMinimumSize());
        setPreferredSize(getPreferredSize());
        setFont(getFont());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);

        Insets drag = new Insets(0, 10, 0, 10);
        ComponentResizer resizer = new ComponentResizer(this);
        ComponentMover cr = new ComponentMover(this, this);
        cr.setDragInsets(drag);
        resizer.setDragInsets(drag);
    }

}
