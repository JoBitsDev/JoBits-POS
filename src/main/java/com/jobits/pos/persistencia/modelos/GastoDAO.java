/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;



import java.util.Date;
import java.util.List;
import com.jobits.pos.persistencia.Gasto;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class GastoDAO extends AbstractModel<Gasto> {

    private static GastoDAO INSTANCE = null;

    private GastoDAO() {
        super(Gasto.class);
    }

    public static GastoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GastoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
    
    public List<String> getNombresGastosByTipo(int idTipo){
      return getEntityManager().createNamedQuery("Gasto.findByNombre")
                .setParameter("id", idTipo)
                .getResultList();
    }
    
   
       
}
