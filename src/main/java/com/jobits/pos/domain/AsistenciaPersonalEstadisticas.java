/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.domain;

import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.ui.utils.utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class AsistenciaPersonalEstadisticas {

    private List<AsistenciaPersonal> asistencia;
    private Personal p;
    private boolean use;

    public AsistenciaPersonalEstadisticas(List<AsistenciaPersonal> asistencia, Personal p) {
        this.asistencia = asistencia;
        Collections.sort(this.asistencia);
        this.p = p;
    }

    public AsistenciaPersonalEstadisticas(AsistenciaPersonal asistencia, Personal p) {
        this.asistencia = new ArrayList<>();
        this.asistencia.add(asistencia);
        this.p = p;
    }

    public AsistenciaPersonalEstadisticas(List<AsistenciaPersonal> asistencia) {
        this.asistencia = asistencia;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public List<AsistenciaPersonal> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(List<AsistenciaPersonal> asistencia) {
        this.asistencia = asistencia;
        Collections.sort(this.asistencia);

    }

    public Personal getP() {
        return p;
    }

    public void setP(Personal p) {
        this.p = p;
    }

    public int getCantidadDiasTrabajados() {
        return asistencia.size();
    }

    public float getPromedioCobro() {
        float ret = 0;
        for (AsistenciaPersonal a : asistencia) {
            ret += a.getPago();
        }
        return utils.setDosLugaresDecimalesFloat(ret / getCantidadDiasTrabajados());
    }

    public float getTotalPago() {
        float ret = 0;
        for (AsistenciaPersonal a : asistencia) {
            if (a.getPago() != null) {
                ret += a.getPago();
            }
        }
        return utils.setDosLugaresDecimalesFloat(ret);

    }

    public float getTotalPropina() {
        float ret = 0;
        for (AsistenciaPersonal a : asistencia) {
            if (a.getPropina() != null) {
                ret += a.getPropina();
            }
        }
        return utils.setDosLugaresDecimalesFloat(ret);
    }

    public List<?> getDiasTrabajados() {
        List<Date> dias = new ArrayList<>();
        for (AsistenciaPersonal a : asistencia) {
            dias.add(a.getVenta().getFecha());
        }
        return dias;
    }

    public List< ? extends Number> getMontos() {
        List<Float> ret = new ArrayList<>();
        for (AsistenciaPersonal a : asistencia) {
            ret.add(a.getPago());
        }
        return ret;
    }

    public void addDiaTrabajado(AsistenciaPersonal dia) {
        getAsistencia().add(dia);
        Collections.sort(this.asistencia);
    }

    @Override
    public String toString() {
        return p.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AsistenciaPersonalEstadisticas) {
            return p.equals(((AsistenciaPersonalEstadisticas) obj).getP());
        }
        if (obj instanceof AsistenciaPersonal) {
            return p.equals(((AsistenciaPersonal) obj).getPersonal());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.p.getUsuario());
        return hash;
    }

}
