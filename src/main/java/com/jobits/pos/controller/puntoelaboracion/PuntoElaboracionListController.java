/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.puntoelaboracion;

import java.util.ArrayList;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.DuplicatedException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.ProductoVentaDAO;
import com.jobits.pos.domain.models.Insumo;
import java.util.Collections;
import java.util.List;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuntoElaboracionListController extends OldAbstractListController<Cocina> implements PuntoElaboracionListService {

    public PuntoElaboracionListController() {
        super(CocinaDAO.getInstance());
    }

    @Override
    public void createInstance(String newCocina) {
        if (newCocina != null) {
            if (!newCocina.equals("")) {
                throw new IllegalArgumentException("Nombre no puede ser nulo o vacio");
            }
            Cocina c = new Cocina(getModel().generateStringCode("C-"));
            c.setImpresoraList(new ArrayList<>());
            c.setIpvList(new ArrayList<>());
            c.setNombreCocina(newCocina);
            c.setProductoVentaList(new ArrayList<>());
            getItems().stream().filter((x)
                    -> (x.getNombreCocina().toLowerCase().equals(newCocina.toLowerCase()))).forEachOrdered((_item) -> {
                throw new DuplicatedException();
            });
            create(c);
        }
    }

    @Override
    public void update(Cocina selected, String editCocina) {
        if (editCocina != null && !editCocina.equals("")) {
            getItems().stream().filter((x)
                    -> (x.getNombreCocina().toLowerCase().equals(editCocina.toLowerCase()))).forEachOrdered((_item) -> {
                throw new ValidatingException(getView());
            });
            selected.setNombreCocina(editCocina);
            setSelected(selected);
            super.update();
        }

    }

    @Override
    public void destroy(Cocina selected, boolean flag) {
        if (flag) {
            for (ProductoVenta p : selected.getProductoVentaList()) {
                p.setCocinacodCocina(null);
                p.setVisible(false);
                ProductoVentaDAO.getInstance().edit(p);
            }
        }
        super.destroy(selected); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Cocina> getDetailControllerForNew() {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Cocina> getDetailControllerForEdit(Cocina selected) {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
    }

    @Override
    public List<Cocina> getItems() {
        List<Cocina> retSorted = super.getItems();
        Collections.sort(retSorted);
        return retSorted;
    }

}
