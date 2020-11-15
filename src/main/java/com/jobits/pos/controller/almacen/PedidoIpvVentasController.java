/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import java.awt.Container;
import java.util.List;
import com.jobits.pos.controller.AbstractController;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.IpvVentaRegistro;
import com.jobits.pos.adapters.repo.impl.AbstractRepository;
import com.jobits.pos.adapters.repo.impl.AlmacenDAO;
import com.jobits.pos.adapters.repo.impl.IpvRegistroVentaDAO;
import com.jobits.pos.domain.InsumoPedidoModel;
import com.jobits.pos.domain.ProdcutoVentaPedidoModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PedidoIpvVentasController extends AbstractDialogController<IpvVentaRegistro> implements PedidoIpvVentasService {

    private List<IpvVentaRegistro> ipvProductList;
    private Cocina elaboracion;

    public PedidoIpvVentasController(List<IpvVentaRegistro> ipvProductList, Cocina elaboracion) {
        super(IpvRegistroVentaDAO.getInstance());
        this.ipvProductList = ipvProductList;
        this.elaboracion = elaboracion;
    }

    public List<IpvVentaRegistro> getIpvProductList() {
        return ipvProductList;
    }

    public Cocina getElaboracion() {
        return elaboracion;
    }

    @Override
    public void constructView(Container parent) {
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
