/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.login;

import GUI.Views.AbstractView;
import GUI.Views.login.LogInDialogView;
import GUI.Views.util.AutenticacionFragmentView;
import java.awt.Container;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import javax.swing.JDialog;
import javax.swing.SwingWorker;
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

    private boolean AUTORIZADO = false;
    private int nivelMinimo = -1;
    private String usuarioRequerido = "";

    public LogInController() {
        super(null);
    }

    public boolean constructoAuthorizationView(Container parent, int nivelMinimo) {
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= nivelMinimo) {
            return true;
        }
        String nombreNivel = "No Identificado";
        for (R.NivelAcceso v : R.NivelAcceso.values()) {
            if (v.getNivel() == nivelMinimo) {
                nombreNivel = v.name();
            }
        }

        constructLoginPanel(parent, "Nivel Minimo (" + nombreNivel + ")");
        this.nivelMinimo = nivelMinimo;
        getView().setVisible(true);
        return AUTORIZADO;
    }

    public boolean constructoAuthorizationView(Container parent, String usuario) {
        int nivelUsuario = PersonalDAO.getInstance().find(usuario).getPuestoTrabajonombrePuesto().getNivelAcceso();
        if(R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() > nivelUsuario){
            return true;
        }
        if (R.loggedUser.getUsuario().equals(usuario)) {
            return true;
        }
        constructLoginPanel(parent, "Usuario Requerido (" + usuario + ")");
        this.usuarioRequerido = usuario;
        getView().setVisible(true);
        return AUTORIZADO;
    }

    @Override
    public void constructView(Container parent) {
        setView(new LogInDialogView(this));
        getView().setVisible(true);
    }

    private void constructLoginPanel(Container Parent, String title) {
        setView(new AutenticacionFragmentView(Parent, this, true, title));

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
                try {
                    staticContent.init(R.PERIRSTENCE_UNIT_NAME);
                } catch (Exception e) {
                    LoadingWindow.hide();
                    showErrorDialog(getView(), e.getMessage());
                    return "error";
                }
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

    /**
     *
     * @param user
     * @param password
     * @return
     */
    public void autorizar(String user, char[] password) {
        if (!user.isEmpty() && password.length != 0) {
            Personal p = PersonalDAO.getInstance().find(user);
            if (p != null) {
                if (Arrays.equals(p.getContrasenna().toCharArray(), password)) {
                    if (p.getPuestoTrabajonombrePuesto().getNivelAcceso() > 3) {
                        AUTORIZADO = true;
                        return;
                    }
                    if (nivelMinimo != -1) {
                        AUTORIZADO = p.getPuestoTrabajonombrePuesto().getNivelAcceso() >= nivelMinimo;
                    }
                    if (!usuarioRequerido.isEmpty()) {
                        AUTORIZADO = p.getUsuario().equals(usuarioRequerido);
                    }
                    // return "Autenticación correcta";

                } else {
                    // return "La contraseña es incorrecta";
                }
            } else {
                //  return "El usuario no existe";
            }
        } else {
            //   return "Campos vacios";
        }
        getView().dispose();
    }

}
