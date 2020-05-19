/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.seccion;

import com.jobits.pos.adapters.repo.AbstractRepository;
import com.jobits.pos.adapters.repo.CartaDAO;
import com.jobits.pos.controller.AbstractController;
import com.jobits.pos.domain.models.Carta;
import java.awt.Container;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class MenuController extends AbstractController<Carta> {

    CartaListController cartaListController = new CartaListController();
    SeccionListController seccionListController = new SeccionListController();

    public MenuController() {
        super(CartaDAO.getInstance());
        
    }

    @Override
    public void constructView(Container parent) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    public CartaListController getCartaListController() {
        return cartaListController;
    }

    public SeccionListController getSeccionListController() {
        return seccionListController;
    }

}
