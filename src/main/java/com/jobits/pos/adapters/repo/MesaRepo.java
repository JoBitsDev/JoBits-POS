/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo;

import com.jobits.pos.domain.models.Mesa;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface MesaRepo {

    public void create(Mesa mesaToCreate);

    public void edit(Mesa mesaToUpdate);

    public void remove(Mesa mesaToRemove);

    public Mesa find(Object idToFind);

    public List<Mesa> findFrom(String areaId);

    public List<Mesa> findAll();
}
