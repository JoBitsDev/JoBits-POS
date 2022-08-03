/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.almacen.ipv;

import com.jobits.pos.client.webconnection.BaseConnection;
import com.jobits.pos.inventario.core.almacen.domain.Ipv;
import com.jobits.pos.inventario.core.almacen.domain.IpvRegistro;
import com.jobits.pos.inventario.core.almacen.domain.IpvVentaRegistro;
import com.jobits.pos.inventario.core.almacen.usecase.IPVService;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class IpvWCS extends BaseConnection implements IPVService {

    IpvWCI service = retrofit.create(IpvWCI.class);

    @Override
    public IpvRegistro darEntradaExistencia(String codInsumo, String codCocina, int idVenta, float cantidad) {
        return handleCall(service.darEntradaExistencia(codInsumo, codCocina, idVenta, cantidad));
    }

    @Override
    public IpvVentaRegistro darEntradaIPV(String codProductoVenta, int codVenta, float cantidad) {
        return handleCall(service.darEntradaIPV(codProductoVenta, codVenta, cantidad));
    }

    @Override
    public IpvRegistro ajustarConsumo(String codInsumo, String codCocina, int codVenta, float cantidad) {
        return handleCall(service.ajustarConsumo(codInsumo, codCocina, codVenta, cantidad));
    }

    @Override
    public IpvRegistro ajustarCosto(String codInsumo, String codCocina, int codVenta, float cantidad) {
        return handleCall(service.ajustarCosto(codInsumo, codCocina, codVenta, cantidad));
    }

    @Override
    public IpvRegistro transferirIPVRegistro(String codInsumo, String codCocina, int codVenta, String codCocinaTranferir, float cantidad) {
        return handleCall(service.transferirIPVRegistro(codInsumo, codCocina, codVenta, codCocinaTranferir, cantidad));
    }

    @Override
    public IpvRegistro transferirIPVRegistroToAlmacen(String codInsumo, String codCocina, int codVenta, String codAlmacenTransferir, float cantidad) {
        return handleCall(service.transferirIPVRegistroToAlmacen(codInsumo, codCocina, codVenta, codAlmacenTransferir, cantidad));

    }

    @Override
    public boolean reiniciarIPV(String codCocina, int codVenta) {
        return handleCall(service.reiniciarIPV(codCocina, codVenta));
    }

    @Override
    public boolean recalcularExistencias(int idVenta) {
        return handleCall(service.recalcularIPVInsumos(idVenta));
    }

    @Override
    public boolean recalcularIpvRegistros(int codVenta) {
        return handleCall(service.recalcularIPVVentas(codVenta));
    }

    @Override
    public boolean hayDisponibilidad(String codProducto, int idVenta, float cantidad) {
        return handleCall(service.hayDisponibilidad(idVenta, codProducto, cantidad));
    }

    @Override
    public List<IpvRegistro> getIpvRegistroList(String codCocina, int codVenta) {
        return handleCall(service.getIPVRegistroList(codVenta, codCocina));
    }

    @Override
    public List<IpvVentaRegistro> getIpvRegistroVentaList(String codCocina, int codVenta) {
        return handleCall(service.getIPVRegistroVentaList(codVenta, codCocina));
    }

    @Override
    public List<IpvRegistro> inicializarExistencias(int idVenta) {
        return handleCall(service.inicializarExistencias(idVenta));
    }

    @Override
    public List<IpvVentaRegistro> inicializarIpvs(int idVenta) {
        return handleCall(service.inicializarIPVS(idVenta));
    }

    @Override
    public IpvRegistro registrarIPV(String codInsumo, String codCocina, int codVenta) {
        return handleCall(service.registrarIPVS(codVenta, codCocina, codInsumo));
    }

    @Override
    public Ipv create(Ipv t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ipv edit(Ipv t) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ipv destroy(Ipv t) throws RuntimeException {
        return handleCall(service.destroyById(t.getIpvPK().getCocinacodCocina(),
                t.getIpvPK().getInsumocodInsumo()));
    }

    @Override
    public Ipv destroyById(Object o) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ipv findBy(Object o) throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ipv> findAll() throws RuntimeException {
        throw new UnsupportedOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
