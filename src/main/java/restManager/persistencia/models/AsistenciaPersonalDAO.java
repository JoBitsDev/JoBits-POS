/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import java.util.Date;
import java.util.List;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.AsistenciaPersonal;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AsistenciaPersonalDAO extends AbstractModel<AsistenciaPersonal> {

    private static AsistenciaPersonalDAO INSTANCE = null;

    private AsistenciaPersonalDAO() {
        super(AsistenciaPersonal.class);
    }

    public static AsistenciaPersonalDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AsistenciaPersonalDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public List<AsistenciaPersonal> getPersonalTrabajando(Date fecha) {
          return  getEntityManager().createNamedQuery("AsistenciaPersonal.findByVentafecha")
                .setParameter("ventafecha", fecha)
                .getResultList();
    }
       
}
