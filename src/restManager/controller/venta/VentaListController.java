/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.venta;

import GUI.Views.AbstractView;
import GUI.Views.venta.VentaCalendarView;
import java.awt.Container;
import java.util.List;
import restManager.controller.AbstractDialogController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Venta;
import restManager.persistencia.models.VentaDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class VentaListController  extends AbstractDialogController<Venta>{

    public VentaListController() {
        super(VentaDAO.getInstance());
    }

    public VentaListController(AbstractView parentView) {
        super(VentaDAO.getInstance());
        constructView(parentView);
    }
    
    public Venta createNewInstance() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
        setView(new VentaCalendarView(this, (AbstractView) parent));
        getView().setVisible(true);
    }

    public void createDetailResumenView(List<Venta> selectedVentas) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }


 

    
}
