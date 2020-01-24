/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import java.awt.Container;
import java.util.List;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDialogController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Almacen;
import restManager.persistencia.Cocina;
import restManager.persistencia.IpvVentaRegistro;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.AlmacenDAO;
import restManager.persistencia.models.IpvRegistroVentaDAO;
import restManager.persistencia.models.utils.InsumoPedidoModel;
import restManager.persistencia.models.utils.ProdcutoVentaPedidoModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PedidoIpvVentasController extends AbstractDialogController<IpvVentaRegistro> {

    public PedidoIpvVentasController() {
        super(IpvRegistroVentaDAO.getInstance());
    }

    @Override
    public void constructView(Container parent) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean realizarPedidoDeIpv(List<InsumoPedidoModel> insumosARebajar, List<ProdcutoVentaPedidoModel> pedido, Cocina puntoDestino, Almacen almacenOrigen) {
        if (showConfirmDialog(getView(), "Desea ejecutar el pedido")) {
            AlmacenManageController controller = new AlmacenManageController(almacenOrigen);
            controller.setView(getView());
            controller.setShowDialogs(false);

            IPVController ipvController = new IPVController();
            ipvController.setShowDialogs(false);
            ipvController.setView(getView());

            for (InsumoPedidoModel i : insumosARebajar) {
                controller.crearTransaccion(null, controller.findInsumo(i.getInsumo()), 1, puntoDestino, null, i.getCantidad(), 0, null, false);
            }
            for (ProdcutoVentaPedidoModel p : pedido) {
                ipvController.darEntradaIPV(p.getIpvProducto(), p.getCantidad());
            }
            showSuccessDialog(getView());
            return true;
        }
        return false;
    }

    public List<Almacen> getAlmacenList() {
        return AlmacenDAO.getInstance().findAll();
    }

}
