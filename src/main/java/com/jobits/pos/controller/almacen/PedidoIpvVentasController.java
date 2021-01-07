/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import java.awt.Container;
import java.util.List;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.IpvVentaRegistro;
import com.jobits.pos.adapters.repo.impl.AlmacenDAO;
import com.jobits.pos.adapters.repo.impl.IpvRegistroVentaDAO;
import com.jobits.pos.domain.InsumoPedidoModel;
import com.jobits.pos.domain.ProdcutoVentaPedidoModel;
import com.jobits.pos.domain.TransaccionSimple;
import com.jobits.pos.domain.models.Venta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PedidoIpvVentasController extends AbstractDialogController<IpvVentaRegistro> implements PedidoIpvVentasService {

    private List<IpvVentaRegistro> ipvProductList;
    private Cocina elaboracion;
    private Venta venta;

    public PedidoIpvVentasController(List<IpvVentaRegistro> ipvProductList, Cocina elaboracion, Venta venta) {
        super(IpvRegistroVentaDAO.getInstance());
        this.ipvProductList = ipvProductList;
        this.elaboracion = elaboracion;
        this.venta = venta;
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

    @Override
    public void realizarPedidoDeIpv(List<InsumoPedidoModel> insumosARebajar, List<ProdcutoVentaPedidoModel> pedido, Cocina puntoDestino, Almacen almacenOrigen) {
        AlmacenManageService service = new AlmacenManageController(almacenOrigen);
        IPVController ipvController = new IPVController();
        ipvController.setShowDialogs(false);
        ipvController.setView(getView());
        for (InsumoPedidoModel i : insumosARebajar) {
            TransaccionSimple transaccionSalida = new TransaccionSimple(
                    service.findInsumo(i.getInsumo()),
                    i.getCantidad(),
                    puntoDestino);
            service.crearOperacionSalida(new ArrayList<>(Arrays.asList(transaccionSalida)), null, venta.getFecha(), venta.getId());
        }
        for (ProdcutoVentaPedidoModel p : pedido) {
            ipvController.darEntradaIPV(p.getIpvProducto(), p.getCantidad());
        }
    }

    @Override
    public List<Almacen> getAlmacenList() {
        return AlmacenDAO.getInstance().findAll();
    }

}
