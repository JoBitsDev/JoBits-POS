/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import restManager.persistencia.Mesa;
import restManager.persistencia.Nota;
import restManager.persistencia.Venta;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDAO extends AbstractModel<Venta> {

    private static VentaDAO INSTANCE = null;

    private VentaDAO() {
        super(Venta.class);
    }

    public static VentaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VentaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
}
