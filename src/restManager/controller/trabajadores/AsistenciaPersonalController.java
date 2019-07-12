/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;

import GUI.Views.trabajadores.AsistenciaTrabajadoresView;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import restManager.controller.AbstractFragmentListController;
import restManager.controller.venta.VentaDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.AsistenciaPersonal;
import restManager.persistencia.Gasto;
import restManager.persistencia.GastoVenta;
import restManager.persistencia.Personal;
import restManager.persistencia.TipoGasto;
import restManager.persistencia.Venta;
import restManager.persistencia.models.AsistenciaPersonalDAO;
import restManager.persistencia.models.GastoDAO;
import restManager.persistencia.models.GastoVentaDAO;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.TipoGastoDAO;
import restManager.printservice.Impresion;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AsistenciaPersonalController extends AbstractFragmentListController<AsistenciaPersonal> {

    Venta diaVenta;

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
            setView(new AsistenciaTrabajadoresView(this, parent, diaVenta));
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

    public List<AsistenciaPersonal> updateSalaries() {
        ArrayList<AsistenciaPersonal> ret = new ArrayList<>(getPersonalTrabajando(diaVenta));
        VentaDetailController controller = new VentaDetailController(diaVenta);
        for (AsistenciaPersonal a : ret) {
            a.setPago(controller.getPagoTrabajador(a.getPersonal()));
            update(a, true);
        }
        return ret;
    }

    public void imprimirAsistencia() {
        Impresion.getDefaultInstance().printPersonalTrabajando(getItems());
    }

}
