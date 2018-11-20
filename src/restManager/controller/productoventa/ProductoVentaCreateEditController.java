/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.productoventa;

import GUI.Views.productoventa.ProductoVentaCreateEditView;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.persistencia.Cocina;
import restManager.persistencia.ProductoVenta;
import restManager.persistencia.Seccion;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.CocinaDAO;
import restManager.persistencia.models.ProductoVentaDAO;
import restManager.persistencia.models.SeccionDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class ProductoVentaCreateEditController extends AbstractDetailController<ProductoVenta>{

    public ProductoVentaCreateEditController(AbstractModel<ProductoVenta> dataAccess) {
        super(dataAccess);
    }

    public ProductoVentaCreateEditController(ProductoVenta instance, AbstractModel<ProductoVenta> model) {
        super(instance, model);
    }

    public ProductoVentaCreateEditController(Window parent, AbstractModel<ProductoVenta> dataAccess) {
        super(parent, dataAccess);
    }

    public ProductoVentaCreateEditController(ProductoVenta instance, Window parent, AbstractModel<ProductoVenta> dataAccess) {
        super(instance, parent, dataAccess);
    }

    
    
    @Override
    public ProductoVenta createNewInstance() {
        ProductoVenta p = new ProductoVenta(new ProductoVentaDAO().generateStringCode("P-"));
        p.setProductoInsumoList(new ArrayList<>());
        p.setProductovOrdenList(new ArrayList<>());
        return p;
    }

    @Override
    public void constructView(Window parent) {
        setView(new ProductoVentaCreateEditView(instance, this, (JDialog)parent, true));
        getView().updateView();
        getView().setVisible(true);
        
    }
    
    public List<Cocina> getCocinaList(){
        return new CocinaDAO().findAll();
    }
    
    public List<Seccion> getSeccionList(){
        return new SeccionDAO().findAll();
    }

}
