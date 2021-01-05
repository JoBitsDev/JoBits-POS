/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import com.jobits.pos.controller.AbstractFragmentListController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.adapters.repo.impl.AsistenciaPersonalDAO;
import com.jobits.pos.adapters.repo.autenticacion.PersonalDAO;
import com.jobits.pos.main.Application;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.servicios.impresion.formatter.PersonalTrabajandoFormatter;
import javax.swing.JOptionPane;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AsistenciaPersonalController extends AbstractFragmentListController<AsistenciaPersonal> {

    private Venta diaVenta;
    private boolean readOnlyData = false;

    public AsistenciaPersonalController() {
        super(AsistenciaPersonalDAO.getInstance());
    }

    public AsistenciaPersonalController(/*Container parent*/Venta fecha) {
        this();
        //setParent(parent);
        this.diaVenta = fecha;

    }

    @Override
    public void constructView(Container parent) {
//        if (getView() == null) {
//            setView(new AsistenciaTrabajadoresView(this, parent, diaVenta, readOnlyData));
//        }
//        getView().updateView();
//        getView().setVisible(true);
    }

    @Override
    public AsistenciaPersonal createNewInstanceAndAdd() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AsistenciaPersonal editInstance(AsistenciaPersonal instance) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Personal> getTrabajadoresList() {
        return PersonalDAO.getInstance().findAll();
    }

    public void calcularPagoTrabajador(AsistenciaPersonal ret, int codVenta) {
        VentaDetailController controller = new VentaDetailController();
        ret.setPago(controller.getPagoTrabajador(ret.getPersonal(), codVenta));
        ret.setPropina(controller.getPropinaTrabajador(ret.getPersonal(), codVenta));

    }

    public List<AsistenciaPersonal> getPersonalTrabajando(Venta v) {
        if (AsistenciaPersonalDAO.getInstance().getPersonalTrabajando(v.getId()) != null) {
            return AsistenciaPersonalDAO.getInstance().getPersonalTrabajando(v.getId());
        }
        return new ArrayList<>();
    }

    public AsistenciaPersonal createNewInstance(Personal selected, Venta v) {
        AsistenciaPersonal ret = new AsistenciaPersonal(v.getId(), selected.getUsuario());
        ret.setPersonal(selected);
        ret.setVenta(v);
        calcularPagoTrabajador(ret, v.getId());
        create(ret, true);
        return ret;
    }

    public void setDiaVenta(Venta instance) {
        this.diaVenta = instance;
    }

    public boolean isReadOnlyData() {
        return readOnlyData;
    }

    public void setReadOnlyData(boolean readOnlyData) {
        this.readOnlyData = readOnlyData;
    }

    public List<AsistenciaPersonal> updateSalaries(int codVenta) {
        if (!readOnlyData) {
            ArrayList<AsistenciaPersonal> ret = new ArrayList<>(getPersonalTrabajando(diaVenta));
            VentaDetailController controller = new VentaDetailController();
            for (AsistenciaPersonal a : ret) {
                a.setPago(controller.getPagoTrabajador(a.getPersonal(), codVenta));
                a.setPropina(controller.getPropinaTrabajador(a.getPersonal(), codVenta));
                update(a, true);
            }
            return ret;
        }
        return diaVenta.getAsistenciaPersonalList();

    }

    public void updateAMayores(AsistenciaPersonal personalABuscar, float aMayoresValor) {
        personalABuscar.setAMayores(aMayoresValor);
        AsistenciaPersonalDAO.getInstance().edit(personalABuscar);
    }

    public void imprimirAsistencia() {
        if (getPersonalTrabajando(diaVenta).isEmpty()) {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "No hay trabajadores registrados en esta fecha");
        } else {
            Impresion.getDefaultInstance().print(new PersonalTrabajandoFormatter(getPersonalTrabajando(diaVenta)), null);
        }
    }

}
