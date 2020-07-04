/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.AsistenciaPersonalEstadisticas;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author ERIK QUESADA
 */
public interface NominasService {

    public Collection<? extends AsistenciaPersonalEstadisticas> getPersonalActivo(Date fecha_desde, Date fecha_hasta);

    public void pagar(AsistenciaPersonalEstadisticas i);

    public void imprimirEstadisticas(ArrayListModel<AsistenciaPersonalEstadisticas> lista_personal);
    
}
