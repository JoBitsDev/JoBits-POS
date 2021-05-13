/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jobits.pos.main.Application;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.ConfigLoaderController;
import com.jobits.pos.ui.utils.ConfigLoaderService;
import com.jobits.ui.themes.ThemeType;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class VisualesViewPresenter extends AbstractViewPresenter<VisualesViewModel> {

    ConfigLoaderService service = new ConfigLoaderController();

    public VisualesViewPresenter() {
        super(new VisualesViewModel());
        refreshState();
        addListeners();
    }

    @Override
    protected void registerOperations() {
    }

    @Override
    protected Optional refreshState() {
        String sValue = service.getConfigValue("app.theme");
//        ThemeType ttValue = ThemeType.valueOf(sValue);
        getBean().setThemeType_Seleccionado(sValue);
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void addListeners() {
        getBean().addPropertyChangeListener(VisualesViewModel.PROP_THEMETYPE_SELECCIONADO, (PropertyChangeEvent evt) -> {
            String value = (String) evt.getNewValue();
            if (value != null) {
                service.setConfigValue("app.theme", value);
                Application.getInstance().getNotificationService().
                        showDialog("Los cambios tendran efecto al reiniciar el sistema", TipoNotificacion.INFO);
            }
        });
    }

}
