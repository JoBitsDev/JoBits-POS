/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.configuracion;

import com.jobits.pos.recursos.R;

/**
 *
 * @author ERIK QUESADA
 */
public interface ConfiguracionService {

    public void cargarConfiguracion();

    public void updateConfiguracion(R.SettingID v, Object configuration);

    public Object getConfiguracion(R.SettingID v);

}
