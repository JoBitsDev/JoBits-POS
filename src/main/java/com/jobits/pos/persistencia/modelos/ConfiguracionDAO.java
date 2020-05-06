/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import com.jobits.pos.persistencia.Almacen;
import com.jobits.pos.persistencia.Area;
import com.jobits.pos.persistencia.Configuracion;
import com.jobits.pos.persistencia.InsumoAlmacen;
import com.jobits.pos.persistencia.Mesa;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ConfiguracionDAO extends AbstractModel<Configuracion> {

    private static ConfiguracionDAO INSTANCE = null;

    private ConfiguracionDAO() {
        super(Configuracion.class);
    }

    public static ConfiguracionDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfiguracionDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public Configuracion find(R.SettingID id) {
        Configuracion c = super.find(id.getValue()); //To change body of generated methods, choose Tools | Templates.
        if (c == null) {
            c = new Configuracion(id.getValue());
            c.setValor(id.getIntegerValue());
            c.setValorString(id.getStringValue());
            startTransaction();
            create(c);
            commitTransaction();
        }
        return c;
    }

}
