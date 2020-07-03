/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface ProductoVentaDetailService {

    public Collection<? extends Insumo> getInsumoList();

    public void registrarNuevoInsumo();

    public ProductoVenta createNewInstance();

    public ProductoVenta getInstance();

    public void agregarInsumoaProducto(Insumo insumo_disponible_sel, float cantidad);

    public void eliminarInsumoProducto(ProductoInsumo insumo_contenido_seleccionado);

    public void discardChanges();

    public void create(ProductoVenta p);

    public List<Seccion> getSeccionList();

    public List<Cocina> getCocinaList();

}
