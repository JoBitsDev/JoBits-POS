/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionTraspaso;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionTraspasoDAO extends AbstractModel<TransaccionTraspaso> {

    private static TransaccionTraspasoDAO INSTANCE = null;

    private TransaccionTraspasoDAO() {
        super(TransaccionTraspaso.class);
    }

    public static TransaccionTraspasoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionTraspasoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

   
    
}
