/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import java.util.ArrayList;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoElaborado;
import com.jobits.pos.domain.models.ProductoInsumo;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoInsumoDAO extends AbstractRepository<ProductoInsumo> {

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
