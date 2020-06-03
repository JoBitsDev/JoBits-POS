/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.domain.models.Personal;
import lombok.Value;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
@Value
public class ResumenVentaUsuarioTablaModel {

    Personal personal;
    String usuario, montoRecaudado, ordenesAtendidas, pagoPorVenta;

    public ResumenVentaUsuarioTablaModel(Personal personal, String montoRecaudado, String ordenesAtendidas, String pagoPorVenta) {
        this.personal = personal;
        this.montoRecaudado = montoRecaudado;
        this.ordenesAtendidas = ordenesAtendidas;
        this.pagoPorVenta = pagoPorVenta;
        this.usuario = personal.getUsuario();
    }

}
