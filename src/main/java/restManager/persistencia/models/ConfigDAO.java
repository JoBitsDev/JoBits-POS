/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia.models;

import javax.persistence.LockModeType;
import restManager.persistencia.Configuracion;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ConfigDAO extends AbstractModel<Configuracion>{
    
    public ConfigDAO( ) {
        super(Configuracion.class);
    }

   public  int generateNewId(String idName) {
        Configuracion c = find(idName);
        if(c != null){
           startTransaction();
           getEntityManager().lock(c, LockModeType.OPTIMISTIC);
            int ret = c.getValor();
            c.setValor(ret+1);
            commitTransaction();
            return ret;
        }
        return -1;
   }

 
    

}
