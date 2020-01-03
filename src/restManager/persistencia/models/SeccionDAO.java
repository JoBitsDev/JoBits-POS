/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import java.util.ArrayList;
import java.util.List;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Carta;
import restManager.persistencia.Mesa;
import restManager.persistencia.Seccion;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class SeccionDAO extends AbstractModel<Seccion> {

    private static SeccionDAO INSTANCE = null;

    private SeccionDAO() {
        super(Seccion.class);
    }

    public static SeccionDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SeccionDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public List<Seccion> findVisibleSecciones(Mesa mesacodMesa) {
        List<Seccion> secciones = new ArrayList<>();
        for (Carta c : mesacodMesa.getAreacodArea().getCartaList()) {
            secciones.addAll(c.getSeccionList());
        }
        return secciones;
    }
}
