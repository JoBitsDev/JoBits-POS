/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.binding.value.AbstractValueModel;
import com.jobits.pos.controller.configuracion.ConfiguracionController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.notification.NotificationService;
import com.jobits.pos.notification.TipoNotificacion;
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
public class ConfigurationViewPresenter extends AbstractViewPresenter<ConfiguracionViewModel> {

    public static final String ACTION_APPLY = "Aplicar";

    private ConfiguracionController controller;

    public ConfigurationViewPresenter(ConfiguracionController controller) {
        super(new ConfiguracionViewModel());
        this.controller = controller;
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
    }

    @Override
    public AbstractValueModel getModel(String propertyName) {
        return getBean().createValueModelFor(R.SettingID.valueOf(propertyName));
    }

    private void onAplicarClick() {
        for (R.SettingID v : R.SettingID.values()) {
            controller.updateConfiguracion(v, getBean().getConfiguration(v));
        }
        NotificationService.getInstance().notify("Propiedades guardadas exitosamente", TipoNotificacion.SUCCESS);
        NavigationService.getInstance().navigateUp();
    }

    private void updateBeanConfig() {
        for (R.SettingID v : R.SettingID.values()) {
            Object o = controller.getConfiguracion(v);
            if (o != null) {
                getBean().setConfiguration(v, o.toString());
            }
        }
    }

}
