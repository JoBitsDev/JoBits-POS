/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionEntrada;
import restManager.persistencia.TransaccionSalida;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionSalidaDAO extends AbstractModel<TransaccionSalida> {

    private static TransaccionSalidaDAO INSTANCE = null;

    private TransaccionSalidaDAO() {
        super(TransaccionSalida.class);
    }

    public static TransaccionSalidaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionSalidaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    
   
    
}
