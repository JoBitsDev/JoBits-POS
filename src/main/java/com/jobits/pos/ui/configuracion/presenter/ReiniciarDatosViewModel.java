/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.configuracion.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.Almacen;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;

/**
 *
 * @author Home
 */
public class ReiniciarDatosViewModel extends AbstractViewModel {

    private ArrayListModel<Almacen> lista_almacen = new ArrayListModel<>();

    public static final String PROP_LISTA_ALMACEN = "lista_almacen";

    private Almacen almacen_seleccionado;

    public static final String PROP_ALMACEN_SELECCIONADO = "almacen_seleccionado";

    private Date fecha_venta_seleccionada = new Date();

    public static final String PROP_FECHA_VENTA_SELECCIONADA = "fecha_venta_seleccionada";

    private String id_venta = "<ID Venta>";

    public static final String PROP_ID_VENTA = "id_venta";

    private ArrayListModel<Cocina> lista_cocinas = new ArrayListModel<>();

    public static final String PROP_LISTA_COCINAS = "lista_cocinas";

    private Cocina cocina_seleccionada;

    public static final String PROP_COCINA_SELECCIONADA = "cocina_seleccionada";

    /**
     * Get the value of cocina_seleccionada
     *
     * @return the value of cocina_seleccionada
     */
    public Cocina getCocina_seleccionada() {
        return cocina_seleccionada;
    }

    /**
     * Set the value of cocina_seleccionada
     *
     * @param cocina_seleccionada new value of cocina_seleccionada
     */
    public void setCocina_seleccionada(Cocina cocina_seleccionada) {
        Cocina oldCocina_seleccionada = this.cocina_seleccionada;
        this.cocina_seleccionada = cocina_seleccionada;
        firePropertyChange(PROP_COCINA_SELECCIONADA, oldCocina_seleccionada, cocina_seleccionada);
    }

    /**
     * Get the value of lista_cocinas
     *
     * @return the value of lista_cocinas
     */
    public ArrayListModel<Cocina> getLista_cocinas() {
        return lista_cocinas;
    }

    /**
     * Set the value of lista_cocinas
     *
     * @param lista_cocinas new value of lista_cocinas
     */
    public void setLista_cocinas(ArrayListModel<Cocina> lista_cocinas) {
        ArrayListModel<Cocina> oldLista_cocinas = this.lista_cocinas;
        this.lista_cocinas = lista_cocinas;
        firePropertyChange(PROP_LISTA_COCINAS, oldLista_cocinas, lista_cocinas);
    }

    /**
     * Get the value of id_venta
     *
     * @return the value of id_venta
     */
    public String getId_venta() {
        return id_venta;
    }

    /**
     * Set the value of id_venta
     *
     * @param id_venta new value of id_venta
     */
    public void setId_venta(String id_venta) {
        String oldId_venta = this.id_venta;
        this.id_venta = id_venta;
        firePropertyChange(PROP_ID_VENTA, oldId_venta, id_venta);
    }

    /**
     * Get the value of fecha_venta_seleccionada
     *
     * @return the value of fecha_venta_seleccionada
     */
    public Date getFecha_venta_seleccionada() {
        return fecha_venta_seleccionada;
    }

    /**
     * Set the value of fecha_venta_seleccionada
     *
     * @param fecha_venta_seleccionada new value of fecha_venta_seleccionada
     */
    public void setFecha_venta_seleccionada(Date fecha_venta_seleccionada) {
        Date oldFecha_venta_seleccionada = this.fecha_venta_seleccionada;
        this.fecha_venta_seleccionada = fecha_venta_seleccionada;
        firePropertyChange(PROP_FECHA_VENTA_SELECCIONADA, oldFecha_venta_seleccionada, fecha_venta_seleccionada);
    }

    /**
     * Get the value of almacen_seleccionado
     *
     * @return the value of almacen_seleccionado
     */
    public Almacen getAlmacen_seleccionado() {
        return almacen_seleccionado;
    }

    /**
     * Set the value of almacen_seleccionado
     *
     * @param almacen_seleccionado new value of almacen_seleccionado
     */
    public void setAlmacen_seleccionado(Almacen almacen_seleccionado) {
        Almacen oldAlmacen_seleccionado = this.almacen_seleccionado;
        this.almacen_seleccionado = almacen_seleccionado;
        firePropertyChange(PROP_ALMACEN_SELECCIONADO, oldAlmacen_seleccionado, almacen_seleccionado);
    }

    /**
     * Get the value of lista_almacen
     *
     * @return the value of lista_almacen
     */
    public ArrayListModel<Almacen> getLista_almacen() {
        return lista_almacen;
    }

    /**
     * Set the value of lista_almacen
     *
     * @param lista_almacen new value of lista_almacen
     */
    public void setLista_almacen(ArrayListModel<Almacen> lista_almacen) {
        ArrayListModel<Almacen> oldLista_almacen = this.lista_almacen;
        this.lista_almacen = lista_almacen;
        firePropertyChange(PROP_LISTA_ALMACEN, oldLista_almacen, lista_almacen);
    }

}
