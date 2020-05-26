/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.login.MainMenuController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.UbicacionConexionModel;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.MainMenuPresenter;
import com.jobits.pos.ui.MainMenuView;
import com.jobits.pos.ui.login.UbicacionView;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.LongProcessActionServiceImpl;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class LoginViewPresenter extends AbstractViewPresenter<LoginViewModel> {

    public static final String ACTION_AUTENTICAR = "Autenticar";

    public static final String ACTION_EDITAR_UBICACION = "Editar ubicacion";

    LogInController controller;
    UbicacionConexionController ubicacionController;

    public LoginViewPresenter(LogInController controller) {
        super(new LoginViewModel());
        this.controller = controller;
        ubicacionController = new UbicacionConexionController();
        getBean().setListaUbicaciones(new ArrayListModel<>(
                Arrays.asList(ubicacionController.getUbicaciones().getUbicaciones())));
        getBean().setUbicacionSeleccionada(ubicacionController.getUbicaciones().getUbicacionActiva());

    }

    private void onAutenticarClick() {
        String password = getBean().getContraseña();
        getBean().setContraseña("");
        try {
            if (controller.autenticar(getBean().getNombreUsuario(), password.toCharArray())) {
                NotificationService.getInstance().notify("Bienvenido", TipoNotificacion.SUCCESS);
                NavigationService.getInstance().navigateTo(MainMenuView.VIEW_NAME,
                        new MainMenuPresenter(new MainMenuController())); //TODO revisar eso codigo que no le pertenece a esta clse
            }
        } catch (IllegalArgumentException ex) {
            NotificationService.getInstance().notify(ex.getMessage(), TipoNotificacion.ERROR);//PENDING jtext fields pierden focus cuando sale la notificacion
        }
    }

    private void onUbicacionSeleccionadaChanged() {
        new LongProcessActionServiceImpl("Conectando a BD") {//TODO: internacionalizar
            @Override
            protected void longProcessMethod() {
                try {
                    ubicacionController.setSelectedUbicacion(getBean().getUbicacionSeleccionada());
                    controller.connect(ubicacionController.getUbicaciones().getUbicacionActiva());
                    actualizarLabelConexionYBotonAutenticar(controller.isConnected());

                } catch (IOException ex) {
                    Logger.getLogger(LoginViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(LoginViewPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.performAction(null);
    }

    private void onEditarUbicacionClick() {
        NavigationService.getInstance().navigateTo(UbicacionView.VIEW_NAME, new UbicacionViewPresenter(ubicacionController));//TODO codigo de ubicaciones

    }

    private void actualizarLabelConexionYBotonAutenticar(boolean conn) {
        if (conn) {
            getBean().setEstadoConexion("Conectado");
            getBean().setColorLabelConexion(Color.green);
        } else {
            getBean().setEstadoConexion("No hay conexión");
            getBean().setColorLabelConexion(Color.red);
        }
        getBean().setBotonAutenticarHabilitado(conn);
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AUTENTICAR) {
            @Override
            public Optional doAction() {
                onAutenticarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EDITAR_UBICACION,false) {
            @Override
            public Optional doAction() {
                onEditarUbicacionClick();
                return Optional.empty();
            }
        });
        getBean().addPropertyChangeListener(LoginViewModel.PROP_UBICACIONSELECCIONADA, (evt) -> {
            onUbicacionSeleccionadaChanged();
        });
    }

}
