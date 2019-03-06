/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import restManager.persistencia.Almacen;
import restManager.persistencia.Area;
import restManager.persistencia.Configuracion;
import restManager.persistencia.InsumoAlmacen;
import restManager.persistencia.Mesa;

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

}
