/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionEntrada;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionEntradaDAO extends AbstractModel<TransaccionEntrada> {

    private static TransaccionEntradaDAO INSTANCE = null;

    private TransaccionEntradaDAO() {
        super(TransaccionEntrada.class);
    }

    public static TransaccionEntradaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionEntradaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    
   
    
}
