/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import GUI.Views.venta.VentasCreateEditView;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Control.VentaDAO1;
import restManager.persistencia.Orden;
import restManager.persistencia.Venta;
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
    }

    public VentaDetailController(Venta instance) {
        super(instance, VentaDAO.getInstance());
    }

    public VentaDetailController(Window parent) {
        super(parent, VentaDAO.getInstance());
    }

    public VentaDetailController(Venta instance, Window parent) {
        super(instance, parent, VentaDAO.getInstance());
    }

    @Override
    public Venta createNewInstance() {
        Venta ret = new Venta();
        ret.setFecha(new Date());
        ret.setVentaTotal(0.0);
        ret.setVentagastosEninsumos(0.0);
        ret.setOrdenList(new ArrayList<>());
        return ret;
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        vi = new VentasCreateEditView(instance, this, (JDialog) parent);
        setView(vi);
        getView().updateView();
        getView().setVisible(true);
    }

    public void updateOrdenDialog(Orden objectAtSelectedRow) {
        if(ordController == null){
         ordController = new OrdenController(objectAtSelectedRow, vi.getjPanelDetailOrdenes());
        }else{
        ordController.setInstance(objectAtSelectedRow);
        }
        }

    public void createNewOrden() {
        ordController = new OrdenController(vi.getjPanelDetailOrdenes(), instance);

    }

    public List<Orden> getOrdenesActivas() {
        if(R.loggedUser.getPuestoTrabajoList().get(0).getNivelAcceso() > 2){
            return VentaDAO1.getOrdenesActivas(getInstance());
        }else{
            return getInstance().getOrdenList();
        }
    }

    public void removeOrden(Orden objectAtSelectedRow) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
