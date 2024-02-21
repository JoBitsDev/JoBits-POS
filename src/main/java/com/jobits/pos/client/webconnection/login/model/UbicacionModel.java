/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.login.model;

import java.io.Serializable;
import java.util.Objects;
import lombok.Builder;

@Builder
public class UbicacionModel implements Serializable {

    @Builder.Default
    private String nombre = "Por defecto";
    @Builder.Default
    private String ip = "192.168.173.1";
    @Builder.Default
    private String puerto = "8080";
    @Builder.Default
    private String usuario = "admin";
    @Builder.Default
    private String password = "admin";
    @Builder.Default
    private int usuarioId = 0;
    @Builder.Default
    private int baseDatosId = 0;

    private UbicacionModel() {
    }

    public UbicacionModel(String nombre, String ip, String puerto, String usuario, String password, int usuarioId, int baseDatosId) {
        this.nombre = nombre;
        this.ip = ip;
        this.puerto = puerto;
        this.usuario = usuario;
        this.password = password;
        this.usuarioId = usuarioId;
        this.baseDatosId = baseDatosId;
    }

    public static UbicacionModel getDefaultUbicacion() {
        return new UbicacionModelBuilder().build();
    }

    public static UbicacionModel[] getDefaultArrayUbicaciones() {
        return new UbicacionModel[]{new UbicacionModelBuilder().build(),
            new UbicacionModelBuilder().build(),
            new UbicacionModelBuilder().build(),
            new UbicacionModelBuilder().build()};
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getBaseDatosId() {
        return baseDatosId;
    }

    public void setBaseDatosId(int baseDatosId) {
        this.baseDatosId = baseDatosId;
    }

    @Override
    public String toString() {
        return "U:{" + "nombre=" + nombre + ", usuarioId=" + usuarioId + ", baseDatosId=" + baseDatosId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.ip);
        hash = 59 * hash + Objects.hashCode(this.puerto);
        hash = 59 * hash + Objects.hashCode(this.usuario);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + this.usuarioId;
        hash = 59 * hash + this.baseDatosId;
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
        final UbicacionModel other = (UbicacionModel) obj;
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        if (this.baseDatosId != other.baseDatosId) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.ip, other.ip)) {
            return false;
        }
        if (!Objects.equals(this.puerto, other.puerto)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

}
