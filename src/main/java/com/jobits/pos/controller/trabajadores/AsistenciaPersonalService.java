/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.Venta;
import java.awt.Container;
import java.util.List;

/**
 *
 * @author Home
 */
public interface AsistenciaPersonalService {

    void calcularPagoTrabajador(AsistenciaPersonal ret, int codVenta);

    void constructView(Container parent);

    AsistenciaPersonal createNewInstance(Personal selected, Venta v);

    AsistenciaPersonal createNewInstanceAndAdd();

    AsistenciaPersonal editInstance(AsistenciaPersonal instance);

    List<AsistenciaPersonal> getPersonalTrabajando(Venta v);

    List<Personal> getTrabajadoresList();

    void imprimirAsistencia();

    boolean isReadOnlyData();

    void setDiaVenta(Venta instance);

    void setReadOnlyData(boolean readOnlyData);

    void updateAMayores(AsistenciaPersonal personalABuscar, float aMayoresValor);

    List<AsistenciaPersonal> updateSalaries(int codVenta);

    public void destroy(AsistenciaPersonal asistenciaPersonal, boolean quietMode);

}
