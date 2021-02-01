/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.binding.value.AbstractValueModel;
import com.jobits.pos.controller.configuracion.impl.ConfiguracionController;
import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
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
public class ConfigurationViewPresenter extends AbstractViewPresenter<ConfiguracionViewModel> {

    public static final String ACTION_APPLY = "Aplicar";
    public static final String ACTION_CANCEL = "Cancelar";

    private final ConfiguracionService service = PosDesktopUiModule.getInstance().getImplementation(ConfiguracionService.class);

    public ConfigurationViewPresenter() {
        super(new ConfiguracionViewModel());
        updateBeanConfig();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_APPLY) {
            @Override
            public Optional doAction() {
                onAplicarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CANCEL) {
            @Override
            public Optional doAction() {
                Application.getInstance().getNavigator().navigateUp();
                return Optional.empty();
            }
        });
    }

    @Override
    public AbstractValueModel getModel(String propertyName) {
        return getBean().createValueModelFor(R.SettingID.valueOf(propertyName));
    }

    private void onAplicarClick() {
        for (R.SettingID v : R.SettingID.values()) {
            Object config = getBean().getConfiguration(v);
            if (config != null) {
                service.updateConfiguracion(v, getBean().getConfiguration(v));
            }
        }
        Application.getInstance().getNotificationService().notify("Propiedades guardadas exitosamente", TipoNotificacion.SUCCESS);
        service.cargarConfiguracion();
        NavigationService.getInstance().navigateUp();
    }

    private void updateBeanConfig() {
        for (R.SettingID v : R.SettingID.values()) {
            Object o = service.getConfiguracion(v);
            if (o != null) {
                getBean().setConfiguration(v, o);
            }
        }
    }

}
