/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.insumo;

import com.jobits.pos.ui.insumo.InsumoListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.controller.OldAbstractListController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.adapters.repo.impl.InsumoDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoListController extends OldAbstractListController<Insumo> implements InsumoListService{

    private final String PREFIX_FOR_ID = "In-";

    public InsumoListController() {
        super(InsumoDAO.getInstance());
    }


    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
  
    }

    @Override
    public void createInstance() {
        detailController = getDetailControllerForNew();
        items = null;
      //  getView().updateView();//TODO:metodo forzado
    }

    @Override
    public AbstractDetailController<Insumo> getDetailControllerForNew() {
        return new InsumoDetailController();
     //   return new InsumoDetailController(getView());
    }

    @Override
    public AbstractDetailController<Insumo> getDetailControllerForEdit(Insumo selected) {
        return new InsumoDetailController();
       // return new InsumoDetailController(getSelected(), getView());
    }

}
