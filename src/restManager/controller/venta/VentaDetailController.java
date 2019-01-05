/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import GUI.CalcularCambio;
import GUI.Views.venta.VentasCreateEditView;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.UnauthorizedAccessException;
import restManager.persistencia.Control.VentaDAO1;
import restManager.persistencia.Orden;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.staticContent;
import restManager.persistencia.models.OrdenDAO;
import restManager.persistencia.models.VentaDAO;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDetailController extends AbstractDetailController<Venta> {

    OrdenController ordController;
    private VentasCreateEditView vi;

    public VentaDetailController() {
        super(VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    public VentaDetailController(Venta instance) {
        super(instance, VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    public VentaDetailController(Window parent) {
        super(parent, VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    public VentaDetailController(Venta instance, Window parent) {
        super(instance, parent, VentaDAO.getInstance());
        OrdenDAO.getInstance().addPropertyChangeListener(this);
    }

    @Override
    public Venta createNewInstance() {
        return getDiaDeVenta();
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        vi = new VentasCreateEditView(getInstance(), this, (JDialog) parent);
        setView(vi);
        getView().updateView();
        getView().setVisible(true);
    }

    public void updateOrdenDialog(Orden objectAtSelectedRow) {
        if (ordController == null) {
            ordController = new OrdenController(objectAtSelectedRow, vi.getjPanelDetailOrdenes());

        } else {
            ordController.setInstance(objectAtSelectedRow);
        }
    }

    public void createNewOrden() {
        boolean nil = ordController == null;
        if (nil) {
            ordController = new OrdenController(getInstance());
        }
        Orden newOrden = ordController.createNewInstance();
        getInstance().getOrdenList().add(newOrden);
        ordController.create(newOrden, true);
        if (nil) {
            ordController = new OrdenController(newOrden, vi.getjPanelDetailOrdenes());
        } else {
            ordController.setInstance(newOrden);
        }
    }

    public List<Orden> getOrdenesActivas() {
        if (R.loggedUser.getPuestoTrabajoList().get(0).getNivelAcceso() <= 2) {
            return VentaDAO1.getOrdenesActivas(getInstance());
        } else {
            return getInstance().getOrdenList();
        }
    }

    public void removeOrden(Orden objectAtSelectedRow) {
        if(R.loggedUser.getPuestoTrabajonombrePuesto().getNivelAcceso() < 3){
            throw new UnauthorizedAccessException(getView());
        }
        ordController.destroy(objectAtSelectedRow,false);
        fetchNewDataFromServer();

    }

    public void fetchNewDataFromServer() {
        getModel().getEntityManager().refresh(getInstance());
        vi.updateView();
    }

    public void terminarVentas() {
        if (showConfirmDialog(vi, "¿Desea terminar el día de trabajo?")) {
            float ventaTotal = 0,
                    ventasGastosEnInsumos = 0;
            for (Orden x : getInstance().getOrdenList()) {
                if (x.getHoraTerminada() == null) {
                    showErrorDialog(vi, "Existen tickets sin cerrar. Cierre los tickets antes de terminar la venta");
                    return;
                }
                if (!x.getDeLaCasa()) {
                    ventaTotal += x.getOrdenvalorMonetario();
                }
                ventasGastosEnInsumos += x.getOrdengastoEninsumos();

            }
            getInstance().setVentaTotal((double) ventaTotal);
            getInstance().setVentagastosEninsumos((double) ventasGastosEnInsumos);
            update(getInstance());

        }
    }

    public void calcularCambio(Orden objectAtSelectedRow) {
        CalcularCambio cc = new CalcularCambio(getView(), true, objectAtSelectedRow);
    }

    private Venta getDiaDeVenta() {
        List<Venta> ventas = getItems();
        for (int i = ventas.size() - 1; i >= 0; i--) {
            if (ventas.get(i).getVentaTotal() == null) {
                return ventas.get(i);
            }

        }
        Venta ret = new Venta();
        ret.setFecha(new Date());
        ret.setVentaTotal(0.0);
        ret.setVentagastosEninsumos(0.0);
        ret.setOrdenList(new ArrayList<>());

        return ret;
    }

}
