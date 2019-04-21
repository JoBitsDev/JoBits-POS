/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.gasto;

import GUI.Views.gastos.GastoOperacionView;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import restManager.controller.AbstractFragmentController;
import restManager.controller.AbstractFragmentListController;
import restManager.controller.login.LogInController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Gasto;
import restManager.persistencia.GastoVenta;
import restManager.persistencia.TipoGasto;
import restManager.persistencia.Venta;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.GastoDAO;
import restManager.persistencia.models.GastoVentaDAO;
import restManager.persistencia.models.TipoGastoDAO;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class GastoOperacionController extends AbstractFragmentListController<Gasto> {

    Venta diaVenta;

    public GastoOperacionController() {
        super(GastoDAO.getInstance());
    }

    public GastoOperacionController(Container parent, Venta fecha) {
        this();
        setParent(parent);
        this.diaVenta = fecha;

    }

    public List<String> getNombres(String toString) {
        List<String> ret = new ArrayList<>();
        for (TipoGasto t : TipoGastoDAO.getInstance().findAll()) {
            if (t.getNombre().equals(toString)) {
                return GastoDAO.getInstance().getNombresGastosByTipo(t.getIdGasto());
            }
        }
        return ret;
    }

    public List<GastoVenta> getGastos(Date dia) {
        return GastoVentaDAO.getInstance().getGastosByFecha(dia);
    }

    /**
     *
     * @return
     */
    public List<GastoVenta> getLista() {
        return diaVenta.getGastoVentaList();
    }

    public void setDiaVenta(Venta diaVenta) {
        this.diaVenta = diaVenta;
    }

    @Override
    public Gasto createNewInstanceAndAdd() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Gasto editInstance(Gasto instance) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
        if (getView() == null) {
            setView(new GastoOperacionView(this, parent));
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

    public float getValorTotalGastos() {
        float ret = 0;
        for (GastoVenta x : diaVenta.getGastoVentaList()) {
            ret += x.getImporte();
        }
        return ret;
    }

    public void removeGasto(GastoVenta objectAtSelectedRow) {
        if (new LogInController().constructoAuthorizationView(null, 3)) {
            diaVenta.getGastoVentaList().remove(objectAtSelectedRow);
            getModel().startTransaction();
            GastoVentaDAO.getInstance().remove(objectAtSelectedRow);
            getModel().commitTransaction();
            getView().updateView();
            showSuccessDialog(getView());
        }
    }

}
