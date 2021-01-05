/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.areaventa;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.adapters.repo.impl.AreaDAO;
import com.jobits.pos.adapters.repo.impl.CartaDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AreaDetailController extends AbstractDetailController<Area> implements AreaDetailService {

    private boolean creatingMode = true;

    public AreaDetailController() {
        super(AreaDAO.getInstance());
        instance = createNewInstance();
    }

    public AreaDetailController(Area instance) {
        super(instance, AreaDAO.getInstance());
        creatingMode = false;
    }

//    public AreaDetailController(Area instance, AbstractRepository<Area> model) {
//        super(instance, AreaDAO.getInstance());
//    }
//
//    public AreaDetailController(Window parent) {
//        super(parent, AreaDAO.getInstance());
//    }
//
//    public AreaDetailController(Area instance, Window parent) {
//        super(instance, parent, AreaDAO.getInstance());
//    }
    @Override
    public Area createNewInstance() {
        Area ret = new Area();
        ret.setCapacidad(0);
        ret.setCartaList(new ArrayList<>());
        ret.setCodArea(getModel().generateStringCode("A-"));
        ret.setMesaList(new ArrayList<>());
        ret.setNombre("");
        ret.setPorcientoPorServicio(0);
        return ret;
    }

//    @Override
//    public void createUpdateInstance() {
//        if (getView().validateData()) {
//            switch (state) {
//                case CREATING:
//                    create(instance);
//                    break;
//                case EDITING:
//
//                    update(instance);
//                    break;
//            }
//        } else {
//            throw new ValidatingException();
//        }
//    }

    @Override
    public void constructView(Container parent) {
//        setView(new AreaCreateEditView(getInstance(), this, (OldAbstractView) parent));
//        getView().updateView();
//        getView().setVisible(true);
    }

    @Override
    public List<Carta> getCartaList() {
        return CartaDAO.getInstance().findAll();
    }

//    @Override
//    public AbstractDetailView<Area> getView() {
//        return (AbstractDetailView<Area>) super.getView(); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean isCreatingMode() {
        return creatingMode;
    }

}
