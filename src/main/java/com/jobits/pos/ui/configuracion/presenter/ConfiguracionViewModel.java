/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.binding.value.AbstractValueModel;
import com.jobits.pos.core.domain.models.Configuracion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ConfiguracionViewModel extends AbstractViewModel {

    private Map<R.SettingID, Configuracion> configurationMap = new HashMap<>();

    private Map<R.SettingID, AbstractValueModel> valueModelMap = new HashMap<>();

    public ConfiguracionViewModel() {
    }

    private void setConfiguration(R.SettingID settingId, Object wrapper) {
        Configuracion oldValue = configurationMap.get(settingId);
        if (oldValue != null) {
            oldValue.setValue(wrapper);
            setConfiguration(settingId, oldValue);
        }

    }

    public void setConfiguration(R.SettingID settingId, Configuracion wrapper) {
        Object oldValue = configurationMap.get(settingId);
        configurationMap.put(settingId, wrapper);
        firePropertyChange(settingId.toString(), oldValue, wrapper, false);
    }

    public Configuracion getConfiguration(R.SettingID settingId) {
        return configurationMap.get(settingId) != null
                ? configurationMap.get(settingId)
                : null;
    }

    public AbstractValueModel createValueModelFor(R.SettingID settingId) {
        if (valueModelMap.containsKey(settingId)) {
            if (valueModelMap.get(settingId) != null) {
                return valueModelMap.get(settingId);
            }
        }
        AbstractValueModel newValueModel = new AbstractValueModel() {
            @Override
            public Object getValue() {
                return getConfiguration(settingId).resolveValue();
            }

            @Override
            public void setValue(Object newValue) {
                setConfiguration(settingId, newValue);
            }
            
        };
        valueModelMap.put(settingId, newValueModel);
        return newValueModel;

    }

}
