/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.autorizo.presenter;

import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.controller.login.LogInService;
import com.jobits.pos.controller.login.UbicacionConexionController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.ui.backup.presenter.BackUpViewModel;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class AutorizoViewPresenter extends AbstractViewPresenter<AutorizoViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_ACEPTAR = "Aceptar";
    private final LogInService controller;

    public AutorizoViewPresenter(LogInService controller, String text) {
        super(new AutorizoViewModel());
        this.controller = controller;
        getBean().setHeader(text);
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
        NavigationService.getInstance().navigateUp();
    }

    private void onAgregarClick() {
        controller.autorizar(getBean().getUsuario(), getBean().getContrasenna().toCharArray());
        NavigationService.getInstance().navigateUp();
    }
}
