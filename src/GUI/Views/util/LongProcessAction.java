package GUI.Views.util;

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

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public abstract class LongProcessAction {

    protected static final JDialog LOADING_DIALOG = createLoadingDialog();
    private Window window;
    private SwingWorker.StateValue state = SwingWorker.StateValue.PENDING;

    public LongProcessAction() {
    }

    protected abstract void longProcessMethod();

    protected void whenDone() {
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
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(new JLabel("Ejecutando Operacion......."), BorderLayout.PAGE_START);
        ret.add(panel);
        ret.setUndecorated(true);
        ret.pack();
        return ret;
    }

    private void mostrarDialogoCargando() {
        LOADING_DIALOG.setLocationRelativeTo(window);
        LOADING_DIALOG.setVisible(state != SwingWorker.StateValue.DONE);
    }

}
