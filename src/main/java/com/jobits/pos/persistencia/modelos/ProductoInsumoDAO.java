/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;

import java.util.ArrayList;
import com.jobits.pos.persistencia.Insumo;
import com.jobits.pos.persistencia.InsumoElaborado;
import com.jobits.pos.persistencia.ProductoInsumo;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoInsumoDAO extends AbstractModel<ProductoInsumo> {

    private static ProductoInsumoDAO INSTANCE = null;

    private ProductoInsumoDAO() {
        super(ProductoInsumo.class);
    }

    public static ProductoInsumoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductoInsumoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }


}
