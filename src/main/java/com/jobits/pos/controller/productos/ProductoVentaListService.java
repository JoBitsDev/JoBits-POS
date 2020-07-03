/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.productos;

import com.jobits.pos.domain.models.ProductoVenta;
import java.util.Collection;

/**
 *
 * @author Jorge
 */
public interface ProductoVentaListService {

    public void setSelected(ProductoVenta producto);

    public void update();

    public void destroy(ProductoVenta elemento_seleccionado);

    public Collection<? extends ProductoVenta> getItems();
    
    
}
