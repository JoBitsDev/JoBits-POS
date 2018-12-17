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
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.persistencia.Venta;
import restManager.persistencia.models.VentaDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class VentaDetailController extends AbstractDetailController<Venta>{

    OrdenController ordController;
    
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

    @Override
    public void constructView(Window parent) {
        VentasCreateEditView vi = new VentasCreateEditView(instance, this,(JDialog)parent);
        setView(vi);
        getView().updateView();
        
        getView().setVisible(true);
    }

}
