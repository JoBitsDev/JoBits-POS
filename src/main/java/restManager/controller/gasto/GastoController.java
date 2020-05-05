/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.gasto;

import java.awt.Container;
import restManager.controller.AbstractController;
import restManager.persistencia.Gasto;
import restManager.persistencia.models.GastoDAO;

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
