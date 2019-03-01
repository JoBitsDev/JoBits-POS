/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.Seccion;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class SeccionDAO extends AbstractModel<Seccion> {

    private static SeccionDAO INSTANCE = null;

    private SeccionDAO() {
        super(Seccion.class);
    }

    public static SeccionDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SeccionDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}
