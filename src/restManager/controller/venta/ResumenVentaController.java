/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.venta;

import GUI.Views.AbstractView;
import GUI.Views.venta.Resumen;
import java.awt.Container;
import java.awt.Window;
import java.util.Date;
import restManager.controller.AbstractDetailController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Venta;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.VentaDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ResumenVentaController extends AbstractDetailController<Venta>{
    
    private Date fechaFinal = null;
    
    public ResumenVentaController() {
        super(VentaDAO.getInstance());
    }

    public ResumenVentaController(Venta instance,Date fechaFinal) {
        super(instance, VentaDAO.getInstance());
        this.fechaFinal = fechaFinal;
    }

    public ResumenVentaController(Window parent) {
        super(parent, VentaDAO.getInstance());
    }

    public ResumenVentaController(Venta instance, Window parent,Date fechaFinal) {
        super(instance, parent, VentaDAO.getInstance());
        this.fechaFinal = fechaFinal;
    }

    
    @Override
    public Venta createNewInstance() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
        setView(new Resumen((AbstractView) parent, this, fechaFinal));
        getView().updateView();
        getView().setVisible(true);
    }

}
