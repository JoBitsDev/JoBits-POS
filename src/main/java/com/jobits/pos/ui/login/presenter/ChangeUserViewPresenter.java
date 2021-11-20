/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.login.presenter;

import com.jobits.pos.client.webconnection.login.LoginService;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.RootView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ChangeUserViewPresenter extends AbstractViewPresenter<ChangeUserViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_ACEPTAR = "Aceptar";
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
        Application.getInstance().getNavigator().navigateUp();
    }

    private void onAgregarClick() {
        String password = getBean().getPassword();
        getBean().setPassword("");
        if (password != null) {
            if (service.autenticar(getBean().getUser(), password.toCharArray())) {
                Application.getInstance().setLoggedUser(service.getUsuarioConectado());
                RootView.getInstance().getStatusBar().refreshView();
                Application.getInstance().getNavigator().navigateUp();
            }
        }
    }
}
