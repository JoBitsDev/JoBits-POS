/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.login;

import java.awt.Container;
import java.util.Arrays;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.recursos.DBConnector;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import com.jobits.pos.domain.UbicacionConexionModel;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class LogInController {

    private boolean AUTORIZADO = false;
    private int nivelMinimo = -1;
    private String usuarioRequerido = "";
    private UbicacionConexionModel conexionActiva;

    public LogInController() {

    }

    private boolean connect() throws Exception {
        DBConnector.init(conexionActiva);
        return DBConnector.isCONECTADO();
    }

    public boolean autenticar(String user, char[] password) {

        Personal p = null;
        String msg;

        if (!user.isEmpty() && password.length != 0) {
            p = PersonalDAO.getInstance().find(user);
            if (p != null) {
                if (Arrays.equals(p.getContrasenna().toCharArray(), password)) {
                    msg = "Autenticación correcta";
                } else {
                    msg = "La contraseña es incorrecta";
                }
            } else {
                msg = "El usuario no existe";
            }
        } else {
            msg = "Campos vacios";
        }
        if (msg.equals("Autenticación correcta")) {
            Application.getInstance().setLoggedUser(p);
            return true;
        } else {
            throw new IllegalArgumentException(msg);
        }

    }

    public boolean isConnected() {
        return DBConnector.isCONECTADO();
    }

    //
    //Metodos para la ventana de autorizacion
    //
    private void constructLoginPanel(Container Parent, String title) {
        //  setView(new AutenticacionFragmentView(Parent, this, true, title));

    }

    public boolean constructoAuthorizationViewForConfirm(Container parent) {

        constructLoginPanel(parent, "Confirmar Accion (" + R.loggedUser.getUsuario() + ")");
        this.usuarioRequerido = R.loggedUser.getUsuario();
        // getView().setVisible(true); //TODO Cordinator
        return AUTORIZADO;
    }

    public boolean constructoAuthorizationView(Container parent, R.NivelAcceso nivelMinimo) {
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() >= nivelMinimo.getNivel()) {
            return true;
        }
        constructLoginPanel(parent, "Nivel Minimo (" + nivelMinimo + ")");
        this.nivelMinimo = nivelMinimo.getNivel();
        //  getView().setVisible(true); //TODO Cordinator
        return AUTORIZADO;
    }

    public boolean constructoAuthorizationView(Container parent, String usuario) {
        int nivelUsuario = PersonalDAO.getInstance().find(usuario).getPuestoTrabajonombrePuesto().getNivelAcceso();
        if (R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() > nivelUsuario) {
            return true;
        }
        if (R.loggedUser.getUsuario().equals(usuario)) {
            return true;
        }
        constructLoginPanel(parent, "Usuario Requerido (" + usuario + ")");
        this.usuarioRequerido = usuario;
        //getView().setVisible(true); //TODO Cordinator
        return AUTORIZADO;
    }

    /**
     *
     * @param user
     * @param password
     * @return
     */
    public boolean autorizar(String user, char[] password) {
        if (!user.isEmpty() && password.length != 0) {
            Personal p = PersonalDAO.getInstance().find(user);
            if (p != null) {
                if (Arrays.equals(p.getContrasenna().toCharArray(), password)) {
                    if (p.getPuestoTrabajonombrePuesto().getNivelAcceso() > 3) {
                        return AUTORIZADO = true;

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
        return true;
    }

    public void connect(UbicacionConexionModel ubicacionActiva) throws Exception {
        this.conexionActiva = ubicacionActiva;
        connect();
    }

}
