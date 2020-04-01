/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.persistencia.volatil;

import restManager.persistencia.IpvVentaRegistro;
import restManager.persistencia.ProductoVenta;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class ProdcutoVentaPedidoModel {

    private final IpvVentaRegistro ipvProducto;
    private final float cantidad;

    public ProdcutoVentaPedidoModel(IpvVentaRegistro producto, float cantidad) {
        this.ipvProducto = producto;
        this.cantidad = cantidad;
    }

    public IpvVentaRegistro getIpvProducto() {
        return ipvProducto;
    }

    public float getCantidad() {
        return cantidad;
    }

}
