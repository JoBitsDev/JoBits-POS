/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;


import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.InsumoAlmacen;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class CartaDAO extends AbstractRepository<Carta> {

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
