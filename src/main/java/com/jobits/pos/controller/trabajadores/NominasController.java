/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jgoodies.common.collect.ArrayListModel;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.exceptions.NoSelectedException;
import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.adapters.repo.impl.AsistenciaPersonalDAO;
import com.jobits.pos.domain.AsistenciaPersonalEstadisticas;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.ComprobantePagoFormatter;
import com.jobits.pos.utils.utils;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class NominasController extends AbstractDetailController<AsistenciaPersonal> implements NominasService {

    public NominasController() {
        super(AsistenciaPersonalDAO.getInstance());
        instance = createNewInstance();
    }

    @Override
    public AsistenciaPersonal createNewInstance() {
        return null;
    }

    @Override
    public void constructView(Container parent) {
    }

    /**
     *
     * @param del
     * @param al
     * @return
     */
    @Override
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
            i.print(new ComprobantePagoFormatter(personal), null);
        }
        if (showConfirmDialog(getView(), "Confirme el pago a " + personal.getDatosPersonales().getNombre() + " " + personal.getDatosPersonales().getApellidos())) {
            PersonalDetailController controller = new PersonalDetailController(personal);
            controller.setView(getView());
            controller.pagarTrabajador();
        }
    }

    @Override
    public void imprimirEstadisticas(ArrayListModel<AsistenciaPersonalEstadisticas> lista_personal) {
        for (int i = 0; i < lista_personal.getSize(); i++) {
            if (lista_personal.get(i).isUse()) {
                Impresion.getDefaultInstance().print(new ComprobantePagoFormatter(lista_personal.get(i).getP()), null);
            }
        }
    }
}
