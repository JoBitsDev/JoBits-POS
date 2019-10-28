/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.Factura;
import restManager.persistencia.ContabilidadCuenta;
import restManager.persistencia.models.AbstractModel;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class FacturaDAO extends AbstractModel<Factura> {

    private static FacturaDAO INSTANCE = null;

    private FacturaDAO() {
        super(Factura.class);
    }

    public static FacturaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FacturaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}
