/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.seccion;

import com.jobits.pos.adapters.repo.impl.SeccionDAO;
import com.jobits.pos.domain.models.Seccion;

public class SeccionDetailServiceImpl implements SeccionDetailService {

    private SeccionDAO model = SeccionDAO.getInstance();
    private Seccion instance = null;

    public SeccionDetailServiceImpl() {
    }

    @Override
    public Seccion crearNuevaInstancia() {
        return new Seccion();
    }

    @Override
    public void crearSeccion(Seccion seccion) {
        model.startTransaction();
        model.create(seccion);
        model.commitTransaction();
    }

    @Override
    public void editarSeccion(Seccion seccion) {
        model.startTransaction();
        model.edit(seccion);
        model.commitTransaction();
    }

    @Override
    public Seccion getSeccion() {
        if (instance == null) {
            throw new IllegalStateException("La seccion del controller es nula");
        }
        return instance;
    }

    @Override
    public void setSeccion(Seccion seccion) {
        instance = seccion;
    }

    @Override
    public boolean isCreatingMode() {
        return instance == null;
    }

}
