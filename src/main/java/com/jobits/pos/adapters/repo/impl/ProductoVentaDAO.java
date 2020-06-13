/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Seccion;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProductoVentaDAO extends AbstractRepository<ProductoVenta> {

    private static ProductoVentaDAO INSTANCE = null;

    private ProductoVentaDAO() {
        super(ProductoVenta.class);
    }

    public static ProductoVentaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductoVentaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

//    @Override
//    public void edit(ProductoVenta entity) {
//        startTransaction();
//        super.edit(entity);
//        commitTransaction();
//        ArrayList<ProductoInsumo> list = new ArrayList<>(entity.getProductoInsumoList());
//        getEntityManager().refresh(entity);
//        startTransaction();
//        for (int i = 0; i < entity.getProductoInsumoList().size(); i++) {
//            getEntityManager().remove(entity.getProductoInsumoList().get(i));
//        }
//        entity.setProductoInsumoList(null);
//        commitTransaction();
//        super.edit(entity);
//        entity.setProductoInsumoList(list);
//        super.edit(entity);
//        commitTransaction();
//    }

    public List<ProductoVenta> findAllVisible() {
        List<ProductoVenta> ret = new ArrayList<>();
        findAll().stream().filter((x) -> (x.getVisible())).forEachOrdered((x) -> {
            ret.add(x);
        });
        Collections.sort(ret, (o1, o2) -> {
            return o1.getNombre().compareTo(o2.getNombre());
        });
        return ret;
    }

    public List<ProductoVenta> findAllVisible(Mesa mesacodMesa) {
        ArrayList<ProductoVenta> productosDisp = new ArrayList<>();
        for (Carta c : mesacodMesa.getAreacodArea().getCartaList()) {
            for (Seccion s : c.getSeccionList()) {
                productosDisp.addAll(new ArrayList<>(s.getProductoVentaList()));
            }
        }
        List<ProductoVenta> ret = new ArrayList<>();
        productosDisp.stream().filter((x) -> (x.getVisible())).forEachOrdered((x) -> {
            ret.add(x);
        });
        Collections.sort(ret, (o1, o2) -> {
            return o1.getNombre().compareTo(o2.getNombre());
        });
        return ret;
    }

}
