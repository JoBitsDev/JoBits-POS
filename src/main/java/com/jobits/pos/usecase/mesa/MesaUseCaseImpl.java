/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.usecase.mesa;

import com.jobits.pos.core.repo.MesaRepo;
import com.jobits.pos.core.repo.impl.AreaDAO;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MesaUseCaseImpl implements MesaUseCase {

    private MesaRepo repository;

    public MesaUseCaseImpl(MesaRepo repository) {
        this.repository = repository;
    }

    @Override
    public List<Mesa> getListaMesas(Area delArea) {
        List<Mesa> ret = repository.findFrom(delArea.getCodArea());
        Collections.sort(ret, (Mesa o1, Mesa o2) -> {
            Integer cod1 = Integer.parseInt(o1.getCodMesa().substring(2));
            Integer cod2 = Integer.parseInt(o2.getCodMesa().substring(2));
            return cod1.compareTo(cod2);
        });
        return ret;
    }

    @Override
    public List<Area> getListaAreasDisponibles() {
        return AreaDAO.getInstance().findAll();//TODO: pifia esta llamando a una implementacion en la capa de la app
    }

}
