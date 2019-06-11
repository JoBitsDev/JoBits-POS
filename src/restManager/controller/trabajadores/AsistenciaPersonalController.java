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
            setView(new AsistenciaTrabajadoresView(this, parent,diaVenta));
        }
        getView().updateView();
        getView().setVisible(true);
    }

    public void createNewGasto(R.TipoGasto cat, String nombre, float monto, String descripcion) {
        if (showConfirmDialog(getView(), "Desea confirmar la accion")) {
            int idCat = -1;
            for (TipoGasto x : TipoGastoDAO.getInstance().findAll()) {
                if (x.getNombre().equals(cat.getNombre())) {
                    idCat = x.getIdGasto();
                    break;
                }
            }

            if (idCat == -1) {
                TipoGasto nuevo = new TipoGasto(TipoGastoDAO.getInstance().generateIDCode());
                nuevo.setGastoList(new ArrayList<>());
                nuevo.setNombre(cat.getNombre());
                getModel().startTransaction();
                TipoGastoDAO.getInstance().create(nuevo);
                getModel().commitTransaction();
                idCat = nuevo.getIdGasto();
            }

            Gasto gast = null;
            for (Gasto g : GastoDAO.getInstance().findAll()) {
                if (g.getNombre().equals(nombre)) {
                    gast = g;
                    break;
                }
            }
            if (gast == null) {
                gast = new Gasto(GastoDAO.getInstance().generateStringCode("G-"));
                gast.setFrecuenciaPago(-1);
                gast.setGastoVentaList(new ArrayList<>());
                gast.setNombre(nombre);
                gast.setTipoGastoidGasto(TipoGastoDAO.getInstance().find(idCat));
                gast.setUltimoPago(diaVenta.getFecha());
                getModel().startTransaction();
                GastoDAO.getInstance().create(gast);
                getModel().commitTransaction();
            }

            for (GastoVenta li : diaVenta.getGastoVentaList()) {
                if (li.getGasto().getNombre().equals(nombre)) {
                    li.setImporte(li.getImporte() + monto);
                    getModel().startTransaction();
                    GastoVentaDAO.getInstance().edit(li);
                    getModel().commitTransaction();
                    showSuccessDialog(getView());
                    getView().updateView();
                    return;
                }
            }

            GastoVenta v = new GastoVenta(gast.getCodGasto(), diaVenta.getFecha());
            v.setImporte(monto);
            v.setGasto(gast);
            v.setVenta(diaVenta);
            v.setDescripcion(descripcion);
            getModel().startTransaction();
            GastoVentaDAO.getInstance().create(v);
            getModel().commitTransaction();
            diaVenta.getGastoVentaList().add(v);
            showSuccessDialog(getView());
            getView().updateView();

        }

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
        create(ret,true);
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

}
