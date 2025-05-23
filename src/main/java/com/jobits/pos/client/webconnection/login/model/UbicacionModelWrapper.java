/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jobits.db.core.domain.impl.ConexionPropertiesWrapperModelImpl;
import org.jobits.db.core.usecase.impl.LocalUbicacionConexionServiceImpl;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class UbicacionModelWrapper {

    private List<UbicacionModel> ubicaciones = new ArrayList<>();
    private int ubicacionSeleccionada = 0;

    private UbicacionModelWrapper() {
        ubicaciones = Arrays.asList(UbicacionModel.getDefaultArrayUbicaciones());
    }

    public UbicacionModelWrapper(List<UbicacionModel> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public UbicacionModelWrapper(List<UbicacionModel> ubicaciones, int ubicacionSeleccionada) {
        this.ubicaciones = ubicaciones;
        this.ubicacionSeleccionada = ubicacionSeleccionada;
    }

    public List<UbicacionModel> getUbicaciones() {
        return new ArrayList<>(ubicaciones);
    }

    public int getUbicacionSeleccionada() {
        return ubicacionSeleccionada;
    }

    @JsonIgnore
    public UbicacionModel getActiveUbicacion() {
        return ubicaciones.get(ubicacionSeleccionada);

    }

    @Override
    protected UbicacionModelWrapper clone() throws CloneNotSupportedException {
        super.clone();
        return new UbicacionModelWrapper(ubicaciones, ubicacionSeleccionada);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.ubicaciones);
        hash = 83 * hash + this.ubicacionSeleccionada;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UbicacionModelWrapper other = (UbicacionModelWrapper) obj;
        if (this.ubicacionSeleccionada != other.ubicacionSeleccionada) {
            return false;
        }
        if (!Objects.equals(this.ubicaciones, other.ubicaciones)) {
            return false;
        }
        return true;
    }
    
    

}
