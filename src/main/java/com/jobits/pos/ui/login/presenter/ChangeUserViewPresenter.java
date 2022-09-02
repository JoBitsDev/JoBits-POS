/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jobits.pos.client.webconnection.exception.ServerErrorException;
import com.jobits.pos.client.webconnection.login.LoginService;
import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;

import java.util.Optional;

/**
 * @author Home
 */
public class ChangeUserViewPresenter extends AbstractViewPresenter<ChangeUserViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_ACEPTAR = "Aceptar";
    public static String PROP_LOGIN_FAILED = "loginFailed";
    LoginService service = PosDesktopUiModule.getInstance().getImplementation(LoginService.class);

    public ChangeUserViewPresenter() {
        super(new ChangeUserViewModel());
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR) {
            @Override
            public Optional doAction() {
                onAgregarClick();
                return Optional.empty();
            }
        });
    }

    private void onCancelarClick() {
        firePropertyChange(PROP_LOGIN_FAILED, 0, 1);
        Application.getInstance().getNavigator().navigateUp();
    }

    private void onAgregarClick() {
        String password = utils.getSHA256(getBean().getPassword());
        String passwordPlain = (getBean().getPassword());
        getBean().setPassword("");
        if (password != null) {
            var configuracionService = PosDesktopUiModule.getInstance().getImplementation(ConfiguracionService.class);
            boolean ret = false;
            try {
                ret = service.autenticar(getBean().getUser(), password.toCharArray());
            } catch (ServerErrorException ex) {
                if (configuracionService.getConfiguracion(R.SettingID.GENERAL_AUTENTICACION_PLANA).getValor() == 1) {
                    ret = service.autenticar(getBean().getUser(), passwordPlain.toCharArray());
                }
            }
            if (ret) {
                Application.getInstance().setLoggedUser(service.getUsuarioConectado());
                RootView.getInstance().getStatusBar().refreshView();
                Application.getInstance().getNavigator().navigateUp();
            }
        }

    }
}
