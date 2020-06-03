/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.domain.models.Area;
import lombok.Value;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
@Value
public class ResumenVentaAreaTablaModel {

    private Area area;
    private String codArea, nombreArea;
    private float totalReacuadadoNeta, totalRecaudadoReal;

    public ResumenVentaAreaTablaModel(Area area, float totalReacuadadoNeta, float totalRecaudadoReal) {
        this.area = area;
        this.totalReacuadadoNeta = totalReacuadadoNeta;
        this.totalRecaudadoReal = totalRecaudadoReal;
        this.nombreArea = area.getNombre();
        this.codArea = area.getCodArea();
    }

}
