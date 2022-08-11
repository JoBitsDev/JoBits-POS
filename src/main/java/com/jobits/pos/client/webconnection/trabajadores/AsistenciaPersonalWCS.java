/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.trabajadores;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.client.webconnection.CRUDBaseConnection;
import com.jobits.pos.controller.trabajadores.AsistenciaPersonalService;
import com.jobits.pos.controller.trabajadores.NominasUseCase;
import com.jobits.pos.core.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.core.domain.models.AsistenciaPersonal;
import retrofit2.Retrofit;

import java.time.LocalDate;
import java.util.List;

/**
 * JoBits
 *
 * @author Jorge
 */
public class AsistenciaPersonalWCS extends CRUDBaseConnection<AsistenciaPersonal> implements AsistenciaPersonalService {


    AsistenciaPersonalWCI service = retrofit.create(AsistenciaPersonalWCI.class);

    public AsistenciaPersonalWCS() {
        super();
    }

    @Override
    public AsistenciaPersonal calcularPagoTrabajador(int idVenta, String usuario) {
        return handleCall(service.calcularPagoTrabajador(idVenta, usuario));
    }


    @Override
    public void imprimirAsistencia(int idVenta) {
        handleCall(service.imprimirAsistencia(idVenta));
    }

    @Override
    public AsistenciaPersonal updateAMayores(int idVenta, String usuario, float aMayoresValor) {
        return handleCall(service.updateAMayores(idVenta, usuario, aMayoresValor));
    }


    @Override
    public List<AsistenciaPersonal> getPersonalTrabajando(int idVenta) {
        return handleCall(service.getPersonalTrabajando(idVenta));
    }

    @Override
    public List<AsistenciaPersonal> updateSalaries(int idVenta) {
        return handleCall(service.updateSalaries(idVenta));
    }
}
