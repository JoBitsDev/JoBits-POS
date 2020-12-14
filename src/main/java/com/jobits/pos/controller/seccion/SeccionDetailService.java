/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.seccion;

import com.jobits.pos.domain.models.Seccion;

/**
 *
 * @author Jorge
 */
public interface SeccionDetailService {
    
    public Seccion crearNuevaInstancia();
    
    public Seccion getSeccion();
    
    public void setSeccion(Seccion seccion);
    
    public void crearSeccion(Seccion seccion);
    
    public void editarSeccion(Seccion seccion);
    
    public boolean isCreatingMode();
    
    
    
    
}
