/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.domain.InsumoPedidoModel;
import com.jobits.pos.domain.ProdcutoVentaPedidoModel;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.IpvVentaRegistro;
import java.util.List;

/**
 *
 * @author Home
 */
public interface PedidoIpvVentasService {

    public Cocina getElaboracion();

    public List<IpvVentaRegistro> getIpvProductList();

    public List<Almacen> getAlmacenList();

    public boolean realizarPedidoDeIpv(List<InsumoPedidoModel> insumosARebajar, List<ProdcutoVentaPedidoModel> pedido, Cocina puntoDestino, Almacen almacenOrigen);

}
