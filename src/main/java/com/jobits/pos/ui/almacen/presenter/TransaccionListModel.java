/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.Transaccion;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;

/**
 *
 * @author Home
 */
public class TransaccionListModel extends AbstractListViewModel<Transaccion> {

    private Almacen almacen;

    public static final String PROP_ALMACEN = "almacen";

    private boolean show_mermas = false;

    public static final String PROP_SHOW_MERMAS = "show_mermas";

    /**
     * Get the value of show_mermas
     *
     * @return the value of show_mermas
     */
    public boolean isShow_mermas() {
        return show_mermas;
    }

    /**
     * Set the value of show_mermas
     *
     * @param show_mermas new value of show_mermas
     */
    public void setShow_mermas(boolean show_mermas) {
        boolean oldShow_mermas = this.show_mermas;
        this.show_mermas = show_mermas;
        firePropertyChange(PROP_SHOW_MERMAS, oldShow_mermas, show_mermas);
    }

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
