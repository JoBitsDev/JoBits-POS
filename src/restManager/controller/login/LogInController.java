/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.login;

import GUI.EsquemaSalon;
import GUI.Views.login.MainView;
import GUI.Views.login.LogInDialogView;
import java.awt.Container;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDialogController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Personal;
import restManager.persistencia.jpa.staticContent;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.PersonalDAO;
import restManager.resources.R;
import restManager.util.LoadingWindow;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class LogInController extends AbstractDialogController<Personal> {

    public LogInController() {
        super(null);
    }

    @Override
    public void constructView(Container parent) {
        setView(new LogInDialogView(this));
        getView().setVisible(true);
    }

    public boolean connectLocal() {
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_local");
        return connect();
    }

    public boolean connectRemote() {
        R.PERIRSTENCE_UNIT_NAME = R.RESOURCE_BUNDLE.getString("unidad_persistencia_remota");
        return connect();
    }

    private boolean connect() {
        LoadingWindow.show(getView());
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                getView().setEnabled(false);
                staticContent.init(R.PERIRSTENCE_UNIT_NAME);
                return "gg";
            }

            @Override
            protected void done() {
                    getView().updateView();
                    getView().setEnabled(true);
                    LoadingWindow.hide();
          
            }
        };
        worker.execute();
        return staticContent.isCONECTADO();
    }

    public void autenticar(String user, char[] password) {
        LoadingWindow.show(getView());

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            Personal p;

            @Override
            protected String doInBackground() throws Exception {
                if (!user.isEmpty() && password.length != 0) {
                    p = PersonalDAO.getInstance().find(user);
                    if (p != null) {
                        if (Arrays.equals(p.getContrasenna().toCharArray(), password)) {
                            return "Autenticación correcta";
                        } else {
                            return "La contraseña es incorrecta";
                        }
                    } else {
                        return "El usuario no existe";
                    }
                } else {
                    return "Campos vacios";
                }
            }

            @Override
            protected void done() {
                String status;
                try {
                    status = get();
                    LoadingWindow.hide();
                    if (status.equals("Autenticación correcta")) {
                        showSuccessDialog(getView(), "Bienvenido");
                        MainController controller = new MainController(p, getView());
                    } else {
                        showErrorDialog(getView(), status);
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(EsquemaSalon.class.getName()).log(Level.SEVERE, null, ex);
                    LoadingWindow.hide();
                    showErrorDialog(getView(), ex.getMessage());
                }
            }
        };

        worker.execute();
    }

    public boolean isConnected() {
        return staticContent.isCONECTADO();
    }

}
