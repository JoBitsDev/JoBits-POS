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

    private static JDialog loadingDialog;
    private Window window;
    private SwingWorker.StateValue state = SwingWorker.StateValue.PENDING;

    public LongProcessAction() {
    }

    public abstract void longProcessMethod();

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
        };

        if (c == null) {
            window = FocusManager.getCurrentManager().getActiveWindow();
        } else {
            window = SwingUtilities.getWindowAncestor(c);
        }
        loadingDialog = new JDialog(window, null, Dialog.ModalityType.APPLICATION_MODAL);

        backgroundWorker.addPropertyChangeListener((PropertyChangeEvent evt1) -> {
            if (evt1.getPropertyName().equals("state")) {
                if (evt1.getNewValue() == SwingWorker.StateValue.DONE) {
                    loadingDialog.dispose();
                    state = SwingWorker.StateValue.DONE;
                }
            }
        });
        backgroundWorker.execute();

        mostrarDialogoCargando();

    }

    private void mostrarDialogoCargando() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(new JLabel("Ejecutando Operacion......."), BorderLayout.PAGE_START);
        loadingDialog.add(panel);
        loadingDialog.setUndecorated(true);
        loadingDialog.pack();
        loadingDialog.setLocationRelativeTo(window);
        loadingDialog.setVisible(state != SwingWorker.StateValue.DONE);
    }

}
