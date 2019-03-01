/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.Transaccion;
import restManager.persistencia.TransaccionEntrada;
import restManager.persistencia.TransaccionMerma;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionMermaDAO extends AbstractModel<TransaccionMerma> {

    private static TransaccionMermaDAO INSTANCE = null;

    private TransaccionMermaDAO() {
        super(TransaccionMerma.class);
    }

    public static TransaccionMermaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionMermaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    
   
    
}
