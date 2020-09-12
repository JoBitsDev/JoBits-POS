/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.insumo;

import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoElaborado;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import java.util.List;

/**
 *
 * @author Home
 */
public interface InsumoDetailService {

    public Insumo createNewInstance();

    public Insumo getInstance();

    public void createUpdateInstance();

    public List<Almacen> getAlmacenList();

    public List<ProductoVenta> getProductoList();

    public List<Insumo> getItems();

    public void agregarInsumoElaboradoaInsumo(Insumo insumo_disponible_seleccionado, float cantidad);

    public void eliminarInsumoElaboradoDeInsumo(InsumoElaborado insumo_contenido_seleccionado);

    public void agregarProductoVentaAInsumo(ProductoVenta producto_disponible_seleccionado, float cantidad);

    public void eliminarProductoVentaDeInsumo(ProductoInsumo producto_contenido_seleccionado);

}
