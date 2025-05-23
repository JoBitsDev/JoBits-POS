/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.binding.value.AbstractValueModel;
import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.configuracion.Configuracion;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.root101.clean.core.app.services.utils.TipoNotificacion;

import java.util.HashMap;
import java.util.Optional;

/**
 * JoBits
 *
 * @author Jorge
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
                if ((boolean) Application.getInstance().getNotificationService().
                        showDialog("Esta seguro que desea cancelar?",
                                TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                    Application.getInstance().getNavigator().navigateUp();
                }
                return Optional.empty();
            }
        });
    }

    @Override
    public AbstractValueModel getModel(String propertyName) {
        return getBean().createValueModelFor(R.SettingID.valueOf(propertyName));
    }

    private void onAplicarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea confirmar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            var map = new HashMap<String, Configuracion>();
            for (R.SettingID v : R.SettingID.values()) {
                Object config = getBean().getConfiguration(v);
                if (config != null) {
                    map.put(v.getValue(), getBean().getConfiguration(v));
                }
            }
            service.guardarConfiguracion(map);
            Application.getInstance().getNotificationService().notify("Propiedades guardadas exitosamente", TipoNotificacion.SUCCESS);
            NavigationService.getInstance().navigateUp();
        }
    }

    private void updateBeanConfig() {
        var map = service.cargarConfiguracion();
        for (R.SettingID v : R.SettingID.values()) {
            var c = map.get(v.getValue());
            if (c != null) {
                getBean().setConfiguration(v, c);
            }
        }
    }

}
