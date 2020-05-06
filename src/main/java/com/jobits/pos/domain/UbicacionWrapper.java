/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class UbicacionWrapper {

    private UbicacionConexionModel[] ubicaciones = new UbicacionConexionModel[4];
    private int selectedUbicacion;

    public UbicacionWrapper() {
    }

    public UbicacionWrapper(UbicacionConexionModel[] ubicaciones, int selectedUbicacion) {
        this.ubicaciones = ubicaciones;
        this.selectedUbicacion = selectedUbicacion;
    }

    public UbicacionConexionModel[] getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(UbicacionConexionModel[] ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public int getSelectedUbicacion() {
        return selectedUbicacion;
    }

    public void setSelectedUbicacion(int selectedUbicacion) {
        this.selectedUbicacion = selectedUbicacion;
    }

    @JsonIgnore
    public UbicacionConexionModel getUbicacionActiva() {
        return selectedUbicacion == -1 ? null : ubicaciones[selectedUbicacion];
    }

}
