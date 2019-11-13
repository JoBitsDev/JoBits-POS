/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.ActivoFijo;
import restManager.persistencia.Almacen;
import restManager.persistencia.InsumoAlmacen;
import restManager.persistencia.Ubicacion;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class UbicacionDAO extends AbstractModel<Ubicacion> {

    private static UbicacionDAO INSTANCE = null;

    private UbicacionDAO() {
        super(Ubicacion.class);
    }

    public static UbicacionDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UbicacionDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

   
    
}
