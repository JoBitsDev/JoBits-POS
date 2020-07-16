/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.trabajadores;

import com.jobits.pos.ui.trabajadores.AsistenciaTrabajadoresView;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import com.jobits.pos.controller.AbstractFragmentListController;
import com.jobits.pos.controller.venta.VentaDetailController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.persistencia.AsistenciaPersonal;
import com.jobits.pos.persistencia.Gasto;
import com.jobits.pos.persistencia.GastoVenta;
import com.jobits.pos.persistencia.Personal;
import com.jobits.pos.persistencia.TipoGasto;
import com.jobits.pos.persistencia.Venta;
import com.jobits.pos.persistencia.modelos.AsistenciaPersonalDAO;
import com.jobits.pos.persistencia.modelos.GastoDAO;
import com.jobits.pos.persistencia.modelos.GastoVentaDAO;
import com.jobits.pos.persistencia.modelos.PersonalDAO;
import com.jobits.pos.persistencia.modelos.TipoGastoDAO;
import com.jobits.pos.servicios.impresion.Impresion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.servicios.impresion.formatter.PersonalTrabajandoFormatter;

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

    public AsistenciaPersonalController(Container parent, Venta fecha) {
        this();
        setParent(parent);
        this.diaVenta = fecha;

    }

    @Override
    public void constructView(Container parent) {
        if (getView() == null) {
            setView(new AsistenciaTrabajadoresView(this, parent, diaVenta, readOnlyData));
        }
        getView().updateView();
        getView().setVisible(true);
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

    public void calcularPagoTrabajador(AsistenciaPersonal ret) {
        VentaDetailController controller = new VentaDetailController(ret.getVenta());
        ret.setPago(controller.getPagoTrabajador(ret.getPersonal()));
        ret.setPropina(controller.getPropinaTrabajador(ret.getPersonal()));

    }

    public List<AsistenciaPersonal> getPersonalTrabajando(Venta v) {
        return AsistenciaPersonalDAO.getInstance().getPersonalTrabajando(v.getFecha());
    }

    public AsistenciaPersonal createNewInstance(Personal selected, Venta v) {
        AsistenciaPersonal ret = new AsistenciaPersonal(v.getFecha(), selected.getUsuario());
        ret.setPersonal(selected);
        ret.setVenta(v);
        calcularPagoTrabajador(ret);
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

    public List<AsistenciaPersonal> updateSalaries() {
        if (!readOnlyData) {
            ArrayList<AsistenciaPersonal> ret = new ArrayList<>(getPersonalTrabajando(diaVenta));
            VentaDetailController controller = new VentaDetailController(diaVenta);
            for (AsistenciaPersonal a : ret) {
                a.setPago(controller.getPagoTrabajador(a.getPersonal()));
                a.setPropina(controller.getPropinaTrabajador(a.getPersonal()));
                update(a, true);
            }
            return ret;
        }
        return diaVenta.getAsistenciaPersonalList();

    }

    public void imprimirAsistencia() {
        Impresion.getDefaultInstance().print(new PersonalTrabajandoFormatter(getPersonalTrabajando(diaVenta)), null);
    }

}
