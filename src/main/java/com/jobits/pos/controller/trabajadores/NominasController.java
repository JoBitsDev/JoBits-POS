/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.ui.trabajadores.NominasEstadisticasView;
import java.awt.Container;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JDialog;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.NoSelectedException;
import com.jobits.pos.exceptions.UnExpectedErrorException;
import com.jobits.pos.persistencia.AsistenciaPersonal;
import com.jobits.pos.persistencia.Personal;
import com.jobits.pos.persistencia.modelos.AbstractModel;
import com.jobits.pos.persistencia.modelos.AsistenciaPersonalDAO;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class NominasController extends AbstractDetailController<AsistenciaPersonal> {

    public NominasController() {
        super(AsistenciaPersonalDAO.getInstance());
        instance = createNewInstance();
    }

    public NominasController(Window parent) {
        super(parent, AsistenciaPersonalDAO.getInstance());
    }

    @Override
    public AsistenciaPersonal createNewInstance() {
        return null;
    }

    @Override
    public void constructView(Container parent) {
        setView(new NominasEstadisticasView(instance, this, (JDialog) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    public List<AsistenciaPersonalEstadisticas> getPersonalActivo(Date del, Date al) {
        if (al.compareTo(del) < 0) {
            throw new NoSelectedException("La fecha fin no puede ser mayor a la fecha inicio");
        }
        ArrayList<AsistenciaPersonalEstadisticas> ret = new ArrayList<>();
        ArrayList<AsistenciaPersonal> i = new ArrayList<>(getItems());
        for (AsistenciaPersonal a : i) {
            if (utils.estaEnRangoSinTiempo(a.getVenta().getFecha(), del, al)) {
                int index = ret.indexOf(a);
                getModel().refresh(a);
                if (index == -1) {
                    ret.add(new AsistenciaPersonalEstadisticas(a, a.getPersonal()));
                } else {
                    ret.get(index).addDiaTrabajado(a);
                }
            }
        }
        return ret;

    }

    public void pagar(AsistenciaPersonalEstadisticas objectAtSelectedRow) {
        Personal personal = objectAtSelectedRow.getP();
        if (showConfirmDialog(getView(), "Desea imprimir un comprobante de pago a" + personal)) {
            Impresion i = Impresion.getDefaultInstance();
            i.printComprobantePago(personal);
        }
        if (showConfirmDialog(getView(), "Confirme el pago a " + personal.getDatosPersonales().getNombre() + " " + personal.getDatosPersonales().getApellidos())) {
            PersonalCreateEditController controller = new PersonalCreateEditController(personal);
            controller.setView(getView());
            controller.pagarTrabajador();
        }
    }

    public void imprimirEstadisticas(List<AsistenciaPersonalEstadisticas> items) {
        for (AsistenciaPersonalEstadisticas i : items) {
            if (i.isUse()) {
                Impresion.getDefaultInstance().printComprobantePago(i);
            }
        }
    }

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

}
