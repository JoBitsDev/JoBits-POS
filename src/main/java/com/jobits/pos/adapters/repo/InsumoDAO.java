/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;

import java.util.ArrayList;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoElaborado;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoDAO extends AbstractModel<Insumo> {

    private static InsumoDAO INSTANCE = null;

    private InsumoDAO() {
        super(Insumo.class);
    }

    public static InsumoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InsumoDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    @Override
    public void edit(Insumo entity) {
        Insumo oldInsumo = find(entity.getCodInsumo());
        startTransaction();
        for (InsumoElaborado x : oldInsumo.getInsumoDerivadoList()) {
            getEntityManager().remove(x);
        }
        commitTransaction();

        startTransaction();

        for (InsumoElaborado x : entity.getInsumoDerivadoList()) {
            getEntityManager().persist(x);
        }

        super.edit(entity);
        commitTransaction();

    }

    @Override
    public void create(Insumo insumo) {
        if (insumo.getProductoInsumoList() == null) {
            insumo.setProductoInsumoList(new ArrayList<>());
        }
        if (insumo.getIpvList() == null) {
            insumo.setIpvList(new ArrayList<>());
        }
        if (insumo.getInsumoDerivadoList() == null) {
            insumo.setInsumoDerivadoList(new ArrayList<>());
        }

        if (insumo.getInsumoAlmacenList() == null) {
            insumo.setInsumoAlmacenList(new ArrayList<>());
        }

        if (insumo.getTransaccionList() == null) {
            insumo.setTransaccionList(new ArrayList<>());
        }
        getEntityManager().persist(insumo);
    }

}
