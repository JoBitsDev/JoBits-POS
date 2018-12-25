/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.venta;

import java.awt.Frame;
import java.awt.Window;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Venta;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.VentaDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class VentaSinArchivarListController extends AbstractListController<Venta>{

    public VentaSinArchivarListController(Frame owner) {
        super(VentaDAO.getInstance());
    }

    @Override
    public AbstractDetailController<Venta> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Venta> getDetailControllerForEdit(Venta selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {

    }
}
