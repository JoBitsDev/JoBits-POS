/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.seccion;

import com.jobits.pos.domain.models.Carta;
import com.jobits.pos.domain.models.Seccion;

/**
 *
 * @author Jorge
 */
public interface SeccionDetailService {

    public Seccion getSeccion(Object id_seccion);

    public void crearSeccion(Carta carta, Seccion seccion);

    public void editarSeccion(Seccion seccion);

    public boolean isCreatingMode();

    public void setCreatingMode(boolean flag);

}
