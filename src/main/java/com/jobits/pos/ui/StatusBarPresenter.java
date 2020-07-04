/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui;

import com.jobits.pos.controller.licencia.Licence;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.controller.licencia.LicenceService;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class StatusBarPresenter extends AbstractViewPresenter<StatusBarViewModel> {

    private final LicenceService service = Application.getInstance().getLicenceController();
    public static final String ACTION_LICENCIA = "";
    public static final String ACTION_REFRESH_BEAN = "Refresh";

    public StatusBarPresenter() {
        super(new StatusBarViewModel());
        refreshBean();

    }

    private void onLicenciaButtonClick() {

    }

    private void refreshBean() {
        getBean().setBoton_licencia_habilitado(true);
        getBean().setEstado_licencia(service.getEstadoLicencia(Licence.TipoLicencia.APLICACION));
        if (Application.getInstance().getLoggedUser() != null) {
            getBean().setUsuario_registrado(Application.getInstance().getLoggedUser().getUsuario());
        }
        getBean().setVersion_software(R.RELEASE_VERSION);
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_LICENCIA) {
            @Override
            public Optional doAction() {
                onLicenciaButtonClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_REFRESH_BEAN) {
            @Override
            public Optional doAction() {
                refreshBean();
                return Optional.empty();
            }
        });
    }

}
