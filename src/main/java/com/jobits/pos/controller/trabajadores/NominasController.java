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
        if (del == null || al == null) {
            throw new IllegalArgumentException("Campos de fechas vacios");
        }
        if (al.compareTo(del) < 0) {
            throw new IllegalArgumentException("La fecha fin no puede ser mayor a la fecha inicio");
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

    @Override
    public void pagar(ArrayListModel<AsistenciaPersonalEstadisticas> list, boolean flag) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("La lista de empleados a pagar esta vacia");
        }
        for (AsistenciaPersonalEstadisticas i : list) {
            if (i.isUse()) {
                Personal personal = i.getP();
                if (flag) {
                    Impresion imp = Impresion.getDefaultInstance();
                    imp.print(new ComprobantePagoFormatter(personal), null);
                }
                PersonalDetailService service = new PersonalDetailController(personal);
                service.pagarTrabajador();
            }
        }
    }

    @Override
    public void imprimirEstadisticas(ArrayListModel<AsistenciaPersonalEstadisticas> lista_personal) {
        if (lista_personal.isEmpty()) {
            throw new IllegalArgumentException("La lista a imprimir esta vacia");
        }
        for (int i = 0; i < lista_personal.getSize(); i++) {
            if (lista_personal.get(i).isUse()) {
                Impresion.getDefaultInstance().print(new ComprobantePagoFormatter(lista_personal.get(i).getP()), null);
            }
        }
    }
}
