package com.jobits.pos.ui.utils;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import javax.swing.FocusManager;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.LongProcessActionService;
import com.jobits.pos.ui.LongProcessMethod;
import com.jobits.ui.components.MaterialComponentsFactory;
import java.awt.Dimension;
import javax.swing.SwingConstants;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public abstract class LongProcessActionServiceImpl implements LongProcessActionService {

    private Window window;
    private SwingWorker.StateValue state = SwingWorker.StateValue.PENDING;
    private static final JLabel LABEL_CARGANDO = new JLabel("Ejecutando Operacion.......");
    protected static final JDialog LOADING_DIALOG = createLoadingDialog();

    private static LongProcessActionServiceImpl INSTANCE = null;

    public LongProcessActionServiceImpl() {
        LABEL_CARGANDO.setText("Ejecutando Operacion.......");
    }

    public LongProcessActionServiceImpl(String labelAccion) {
        LABEL_CARGANDO.setText(labelAccion);
    }

    public static LongProcessActionServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LongProcessActionServiceImpl() {
                @Override
                protected void longProcessMethod() {

                }
            };
        }
        return INSTANCE;
    }

    /**
     *
     * @Deprecated 
     */
    protected abstract void longProcessMethod();

    protected void whenDone() {
    }

    public void performAction() {
        SwingWorker<Void, Void> backgroundWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    longProcessMethod();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                return null;
            }

            @Override
            protected void done() {
                super.done(); //To change body of generated methods, choose Tools | Templates.
                whenDone();
            }

        };

        window = Application.getInstance().getMainWindow();

        backgroundWorker.addPropertyChangeListener((PropertyChangeEvent evt1) -> {
            if (evt1.getPropertyName().equals("state")) {
                if (evt1.getNewValue() == SwingWorker.StateValue.DONE) {
                    LOADING_DIALOG.dispose();
                    state = SwingWorker.StateValue.DONE;
                }
            }
        });
        backgroundWorker.execute();

        mostrarDialogoCargando();

    }

    public void performAction(Component c) {
        SwingWorker<Void, Void> backgroundWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    longProcessMethod();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                return null;
            }

            @Override
            protected void done() {
                super.done(); //To change body of generated methods, choose Tools | Templates.
                whenDone();
            }

        };

        if (c == null) {
            window = FocusManager.getCurrentManager().getActiveWindow();
        } else {
            window = SwingUtilities.getWindowAncestor(c);
        }

        backgroundWorker.addPropertyChangeListener((PropertyChangeEvent evt1) -> {
            if (evt1.getPropertyName().equals("state")) {
                if (evt1.getNewValue() == SwingWorker.StateValue.DONE) {
                    LOADING_DIALOG.dispose();
                    state = SwingWorker.StateValue.DONE;
                }
            }
        });
        backgroundWorker.execute();

        mostrarDialogoCargando();

    }

    private static JDialog createLoadingDialog() {
        JDialog ret = new JDialog(null, null, Dialog.ModalityType.APPLICATION_MODAL);
        ret.getContentPane().setLayout(new BorderLayout());

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        JPanel panel = MaterialComponentsFactory.Containers.getTransparentPanel();
        panel.setLayout(new BorderLayout());
        panel.add(progressBar, BorderLayout.SOUTH);

        LABEL_CARGANDO.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(LABEL_CARGANDO, BorderLayout.NORTH);

        ret.add(panel, BorderLayout.CENTER);
        ret.setUndecorated(true);
        ret.pack();

        return ret;
    }

    private void mostrarDialogoCargando() {
        LOADING_DIALOG.setLocationRelativeTo(window);
        LOADING_DIALOG.setVisible(state != SwingWorker.StateValue.DONE);
    }

    @Override
    public void processInBackground(String actionName, LongProcessMethod method) {
        new LongProcessActionServiceImpl(actionName) {
            @Override
            protected void longProcessMethod() {
                method.execute();
            }
        }.performAction();
    }

    @Override
    public void processInBackground(LongProcessMethod method) {
        new LongProcessActionServiceImpl() {
            @Override
            protected void longProcessMethod() {
                method.execute();
            }
        }.performAction();
    }

}
