/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.models;

import restManager.persistencia.Carta;
import restManager.persistencia.Mesa;
import restManager.persistencia.Nota;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class MenuDAO extends AbstractModel<Carta> {

    private static MenuDAO INSTANCE = null;

    private MenuDAO() {
        super(Carta.class);
    }

    public static MenuDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
}
