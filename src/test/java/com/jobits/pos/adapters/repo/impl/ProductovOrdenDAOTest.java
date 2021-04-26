/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import com.jobits.pos.core.repo.impl.ProductovOrdenDAO;
import com.jobits.pos.core.domain.models.ProductovOrden;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jorge
 */
public class ProductovOrdenDAOTest {

    public ProductovOrdenDAOTest() {
    }

    @Before
    public void setUp() {
        //DBConnector.init(new UbicacionConexionController().getUbicaciones().getUbicacionActiva());
    }

    /**
     * Test of getInstance method, of class ProductovOrdenDAO.
     */
   // @Test
    public void testGetInstance() {
        ProductovOrdenDAO.getInstance().startTransaction();
        for (ProductovOrden pvo : ProductovOrdenDAO.getInstance().findAll()) {
            pvo.setPrecioVendido(pvo.getProductoVenta().getPrecioVenta());
           // pvo.setNombreProductoVendido(pvo.getProductoVenta().getNombre());
            ProductovOrdenDAO.getInstance().edit(pvo);
        }
        ProductovOrdenDAO.getInstance().commitTransaction();
    }

}
