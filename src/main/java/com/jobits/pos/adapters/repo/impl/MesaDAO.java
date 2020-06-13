/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import com.jobits.pos.adapters.repo.MesaRepo;
import com.jobits.pos.domain.models.IpvRegistro;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.Nota;
import java.util.ArrayList;
import java.util.List;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class MesaDAO extends AbstractRepository<Mesa> implements MesaRepo {

    private static MesaDAO INSTANCE = null;

    private MesaDAO() {
        super(Mesa.class);
    }

    public static MesaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MesaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    @Override
    public List<Mesa> findFrom(String areaId) {
        return new ArrayList<>(
                getEntityManager().createNamedQuery("Mesa.findFromArea")
                        .setParameter("areaCod", areaId)
                        .getResultList());
    }
}
