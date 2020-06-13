/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.usecase.mesa;

import com.jobits.pos.adapters.repo.MesaRepo;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Mesa;
import java.util.List;

public class MesaUseCaseImpl implements MesaUseCase {

    private MesaRepo repository;

    @Override
    public List<Mesa> getListaMesas(Area delArea) {
        return repository.findFrom(delArea.getCodArea());
    }

}
