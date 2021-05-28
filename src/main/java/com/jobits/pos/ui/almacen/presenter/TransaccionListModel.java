/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.Transaccion;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;

/**
 *
 * @author Home
 */
public class TransaccionListModel extends AbstractListViewModel<Transaccion> {

    private Almacen almacen;

    public static final String PROP_ALMACEN = "almacen";

    /**
     * Get the value of almacen
     *
     * @return the value of almacen
     */
    public Almacen getAlmacen() {
        return almacen;
    }

    /**
     * Set the value of almacen
     *
     * @param almacen new value of almacen
     */
    public void setAlmacen(Almacen almacen) {
        Almacen oldAlmacen = this.almacen;
        this.almacen = almacen;
        firePropertyChange(PROP_ALMACEN, oldAlmacen, almacen);
    }

}
