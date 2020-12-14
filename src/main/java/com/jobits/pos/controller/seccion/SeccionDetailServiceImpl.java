/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.seccion;

import com.jobits.pos.adapters.repo.impl.SeccionDAO;
import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.Seccion;

public class SeccionDetailServiceImpl implements SeccionDetailService {

    private SeccionDAO model = SeccionDAO.getInstance();
    private boolean creatingMode = false;

    public SeccionDetailServiceImpl() {
    }

    @Override
    public void crearSeccion(Carta carta, Seccion seccion) {
        new SeccionListController().createInstanceOffline(seccion, carta);
    }

    @Override
    public void editarSeccion(Seccion seccion) {
        model.startTransaction();
        model.edit(seccion);
        model.commitTransaction();
    }

    @Override
    public Seccion getSeccion(Object id_seccion) {
        Seccion seccion = model.find(id_seccion);
        if (seccion == null) {
            throw new IllegalStateException("No se ha econtrado la seccion");
        }
        return seccion;
    }

    @Override
    public boolean isCreatingMode() {
        return creatingMode;
    }

    @Override
    public void setCreatingMode(boolean flag) {
        creatingMode = flag;
    }

}
