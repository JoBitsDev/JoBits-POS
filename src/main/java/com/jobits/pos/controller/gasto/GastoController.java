/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.controller.gasto;

import java.awt.Container;
import com.jobits.pos.controller.AbstractController;
import com.jobits.pos.persistencia.Gasto;
import com.jobits.pos.persistencia.modelos.GastoDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class GastoController extends AbstractController<Gasto>{

    public GastoController() {
        super(GastoDAO.getInstance());
    }

    
    
    @Override
    public void constructView(Container parent) {
        
    }


    @Override
    public GastoDAO getModel() {
        return (GastoDAO) super.getModel();
    }
    
    
     

}
