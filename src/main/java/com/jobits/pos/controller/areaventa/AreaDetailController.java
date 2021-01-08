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

    @Override
    public void constructView(Container parent) {
    }

    @Override
    public List<Carta> getCartaList() {
        return CartaDAO.getInstance().findAll();
    }

    @Override
    public boolean isCreatingMode() {
        return creatingMode;
    }

}
