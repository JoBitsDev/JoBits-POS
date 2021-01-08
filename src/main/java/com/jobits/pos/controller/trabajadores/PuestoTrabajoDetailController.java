/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import java.util.List;
import com.jobits.pos.controller.AbstractDetailController;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.PuestoTrabajo;
import com.jobits.pos.adapters.repo.impl.AreaDAO;
import com.jobits.pos.adapters.repo.impl.CocinaDAO;
import com.jobits.pos.adapters.repo.impl.PuestoTrabajoDAO;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.RegularExpressions;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PuestoTrabajoDetailController extends AbstractDetailController<PuestoTrabajo> implements PuestoTrabajoDetailService {

    private boolean creatingMode = true;

    public PuestoTrabajoDetailController() {
        super(PuestoTrabajoDAO.getInstance());
        instance = createNewInstance();
    }

    public PuestoTrabajoDetailController(PuestoTrabajo instance) {
        super(instance, PuestoTrabajoDAO.getInstance());
        creatingMode = false;
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
    }

    @Override
    public PuestoTrabajo createNewInstance() {
        return new PuestoTrabajo();
    }

    @Override
    public List<Area> getAreaList() {
        return AreaDAO.getInstance().findAll();
    }

    @Override
    public List<Cocina> getAreasPago() {
        return CocinaDAO.getInstance().findAll();

    }

    @Override
    public boolean isCreatingMode() {
        return creatingMode;
    }

    @Override
    public void fillPuestoTrabajoData(String nombre, Area areaTrabajo, String areaPago, Integer nivelAcceso, boolean pagoPorVentas, boolean propina, Integer puestosDisponibles, float aPartirDe, float salarioFijo, float salarioPorcientoDeArea, float salarioPorcientoVentaTotal) {
        if (nombre == null || nombre.equals("")) {
            throw new IllegalArgumentException("El campo nombre es obligatorio");
        }
        if (nombre.length() > 30 || !nombre.matches(RegularExpressions.ONLY_WORDS_SEPARATED_WITH_SPACES)) {
            throw new IllegalArgumentException("Nombre no permitido");
        }
        instance.setNombrePuesto(nombre);
        instance.setAreacodArea(areaTrabajo);
        instance.setAreaPago(areaPago);
        if (nivelAcceso == null) {
            nivelAcceso = 0;
        }
        instance.setNivelAcceso(nivelAcceso);
        instance.setPagoPorVentas(pagoPorVentas);
        instance.setPropina(propina);
        instance.setPuestosDisponibles(puestosDisponibles);
        instance.setAPartirDe(aPartirDe);
        instance.setSalarioFijo(salarioFijo);
        instance.setSalarioPorcientoDeArea(salarioPorcientoDeArea);
        instance.setSalarioPorcientoVentaTotal(salarioPorcientoVentaTotal);
        if (instance.getPersonalList() == null) {
            instance.setPersonalList(new ArrayList<>());
        }
        if (isCreatingMode()) {
            create(instance);
        } else {
            update(instance);
        }
    }

}
