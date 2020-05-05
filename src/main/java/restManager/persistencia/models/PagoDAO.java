/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.Pago;
import restManager.persistencia.models.AbstractModel;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PagoDAO extends AbstractModel<Pago> {

    private static PagoDAO INSTANCE = null;

    private PagoDAO() {
        super(Pago.class);
    }

    public static PagoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PagoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}
