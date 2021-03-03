/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.statusbar;

import com.jobits.pos.controller.licencia.LicenceService;
import com.jobits.pos.controller.licencia.impl.Licence;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.licencia.LicenceDialogView;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
        Application.getInstance().getNavigator().navigateTo(LicenceDialogView.VIEW_NAME, null, DisplayType.POPUP);
    }

    private void refreshBean() {
        getBean().setBoton_licencia_habilitado(true);
        licenseStatus();
//        getBean().setEstado_licencia(service.getEstadoLicencia(Licence.TipoLicencia.APLICACION));
        if (Application.getInstance().getLoggedUser() != null) {
            getBean().setUsuario_registrado(Application.getInstance().getLoggedUser().getUsuario());
            colorUser();
        }
        Application.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                getBean().setUsuario_registrado(((Personal) evt.getNewValue()).getUsuario());
                colorUser();
            }
        });

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

    private void colorUser() {
        switch (Application.getInstance().getLoggedUser().getPuestoTrabajonombrePuesto().getNivelAcceso()) {
            case 0:
                getBean().setUsuario_registrado_color(Color.DARK_GRAY);
                break;
            case 1:
                getBean().setUsuario_registrado_color(Color.CYAN);
                break;
            case 2:
                getBean().setUsuario_registrado_color(Color.BLUE);
                break;
            case 3:
                getBean().setUsuario_registrado_color(new Color(51, 51, 255));//morado
                break;
            case 4:
                getBean().setUsuario_registrado_color(Color.RED);
                break;
            case 5:
                getBean().setUsuario_registrado_color(Color.ORANGE);//dorado
                break;
            default:
                break;
        }
    }

    private void licenseStatus() {
        String licenseStatus = service.getEstadoLicencia(Licence.TipoLicencia.APLICACION);
        Color colorStatus = Color.black;
        if (licenseStatus.contains("Dias restantes")) {
            String[] value = licenseStatus.split(" ");
            int daysLeft = Integer.parseInt(value[2]);
            if (daysLeft <= 7 && daysLeft > 3) {
                colorStatus = Color.orange;
            } else if (daysLeft <= 3) {
                colorStatus = Color.red;
            }
        } else {
            colorStatus = Color.red;
        }
        getBean().setEstado_licencia(licenseStatus);
        getBean().setEstado_licencia_color(colorStatus);
    }

}
