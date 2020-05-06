/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.controller.areaventa;

import com.jobits.pos.ui.AbstractDetailView;
import com.jobits.pos.ui.AbstractView;
import com.jobits.pos.ui.areaventa.AreaCreateEditView;
import java.awt.Container;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.persistencia.Area;
import com.jobits.pos.persistencia.Carta;
import com.jobits.pos.persistencia.modelos.AbstractModel;
import com.jobits.pos.persistencia.modelos.AreaDAO;
import com.jobits.pos.persistencia.modelos.CartaDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class AreaDetailController extends AbstractDetailController<Area>{

    public AreaDetailController(Area instance, AbstractModel<Area> model) {
        super(instance, AreaDAO.getInstance());
    }

    public AreaDetailController(Window parent) {
        super(parent, AreaDAO.getInstance());
    }

    public AreaDetailController(Area instance, Window parent) {
        super(instance, parent, AreaDAO.getInstance());
    }
    
    @Override
    public Area createNewInstance() {
        Area ret = new Area();
        ret.setCapacidad(0);
        ret.setCartaList(new ArrayList<>());
        ret.setCodArea(getModel().generateStringCode("A-"));
        ret.setMesaList(new ArrayList<>());
        ret.setNombre("Nueva Area");
        ret.setPorcientoPorServicio(0);
        return ret;
    }

    @Override
    public void createUpdateInstance() {
         if (getView().validateData()) {
            switch (state) {
                case CREATING:
                    create(instance);
                    break;
                case EDITING:
                    
                    update(instance);
                    break;
            }
        }else{
            throw new ValidatingException();
        }
    }

    
    
    @Override
    public void constructView(Container parent) {
        setView(new AreaCreateEditView(getInstance(), this, (AbstractView) parent));
        getView().updateView();
        getView().setVisible(true);
    }

    public List<Carta> getCartaList(){
        return CartaDAO.getInstance().findAll();
    }

    @Override
    public AbstractDetailView<Area> getView() {
        return (AbstractDetailView<Area>) super.getView(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
