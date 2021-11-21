/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.insumo;

import com.jobits.pos.client.webconnection.product.*;
import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.controller.insumo.InsumoService;
import com.jobits.pos.controller.productos.ProductoVentaService;
import com.jobits.pos.core.domain.models.Insumo;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class InsumoWCS extends BaseConnection implements InsumoService {

    InsumoWCI service = retrofit.create(InsumoWCI.class);

    public InsumoWCS() {
        super();
    }

    @Override
    public List<Insumo> bulkImport(List<Insumo> importList) {
        return handleCall(service.bulkImport(importList));
    }

    @Override
    public Insumo create(Insumo t) throws RuntimeException {
        return handleCall(service.create(t));
    }

    @Override
    public Insumo edit(Insumo t) throws RuntimeException {
        return handleCall(service.edit(t));
    }

    @Override
    public Insumo destroy(Insumo t) throws RuntimeException {
        return destroyById(t.getCodInsumo());
    }

    @Override
    public Insumo destroyById(Object o) throws RuntimeException {
        return handleCall(service.destroyById(o));
    }

    @Override
    public Insumo findBy(Object o) throws RuntimeException {
        return handleCall(service.findBy(o));
    }

    @Override
    public List<Insumo> findAll() throws RuntimeException {
        return handleCall(service.findAll());
        
    }

}
