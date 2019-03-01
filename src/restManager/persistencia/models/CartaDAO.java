/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;


import restManager.persistencia.Almacen;
import restManager.persistencia.Area;
import restManager.persistencia.Carta;
import restManager.persistencia.InsumoAlmacen;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CartaDAO extends AbstractModel<Carta> {

    private static CartaDAO INSTANCE = null;

    private CartaDAO() {
        super(Carta.class);
    }

    public static CartaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CartaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
       
}
