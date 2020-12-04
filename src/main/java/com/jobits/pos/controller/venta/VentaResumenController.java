/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.adapters.repo.impl.VentaDAO;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.domain.VentaDAO1;
import static com.jobits.pos.domain.VentaDAO1.*;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Orden;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.ui.utils.utils;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author Home
 */
public class VentaResumenController extends AbstractDialogController<Venta> implements VentaResumenService {

    public Venta v;
    public Cocina cocina;
    ArrayList<Cocina> cocinasList;
    ArrayList<ProductovOrden> pvOrdenList;
    ArrayList<ProductoInsumo> pInsumoList;
    float recaudacion;
    float ganancia;
    float invertido;
    float gastosDeLaCasa;

    /**
     *
     */
    public VentaResumenController() {
        super(VentaDAO.getInstance());
        v = new Venta();
    }

    @Override
    public ArrayList<ProductovOrden> getListaVentas(boolean roundedVal) {
        recaudacion = 0;
        if (cocina != null) {
            ArrayList<ProductovOrden> ret = new ArrayList<>(getResumenVentasCocina(v, cocina));
            ret.forEach(x -> {
                recaudacion += x.getPrecioVendido() * x.getCantidad();
                if (roundedVal) {
                    x.setCantidad(Math.round(x.getCantidad()));
                }
            });
            return ret;
        } else {
            ArrayList<ProductovOrden> ret = VentaDAO1.getResumenVentas(v);
            ret.forEach(x -> {
                recaudacion += x.getPrecioVendido() * x.getCantidad();
                if (roundedVal) {
                    x.setCantidad(Math.round(x.getCantidad()));
                }
            });
            return ret;
        }
    }

    @Override
    public ArrayList<ProductovOrden> getListaGastosDeLaCasa(boolean roundedVal) {
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());
        for (Orden o : aux) {
            if (o.getDeLaCasa()) {
                if (false || o.getHoraTerminada() != null) {
                    joinListsProductovOrdenByCocina(ret,
                            new ArrayList(o.getProductovOrdenList()), cocina);
                }
            }
        }
        Collections.sort(ret, (o1, o2) -> {
            return o1.getNombreProductoVendido().compareTo(o2.getNombreProductoVendido());
        });
        if (roundedVal) {
            ret.forEach(x -> {
                x.setCantidad(Math.round(x.getCantidad()));
            });
        }
        return ret;
    }

    @Override
    public ArrayList<ProductoInsumo> getListaGastos(boolean roundedVal) {
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());
        if (cocina != null) {
            for (Orden o : aux) {
                for (ProductovOrden p : o.getProductovOrdenList()) {
                    if (p.getProductoVenta().getCocinacodCocina().equals(cocina)) {
                        joinListsProductoInsumos(ret,
                                new ArrayList<>(p.getProductoVenta().
                                        getProductoInsumoList()), p.getCantidad());
                    }
                }
            }
        } else {
            for (Orden o : aux) {
                for (ProductovOrden p : o.getProductovOrdenList()) {
                    joinListsProductoInsumos(ret,
                            new ArrayList<>(p.getProductoVenta().
                                    getProductoInsumoList()), p.getCantidad());
                }
            }
        }

        Collections.sort(ret, (ProductoInsumo o1, ProductoInsumo o2)
                -> o1.getInsumo().getNombre().compareTo(o2.getInsumo().getNombre()));
        invertido = 0;
        ret.forEach(x -> {
            invertido += x.getCosto();
            if (roundedVal) {
                x.setCantidad(Math.round(x.getCantidad()));
            }
        });
        return ret;
    }

    @Override
    public ArrayList<Cocina> getListaCocinas(boolean roundedVal) {
        ArrayList<Cocina> ret = new ArrayList();
        for (ProductovOrden x : getListaVentas(roundedVal)) {
            Cocina coc = x.getProductoVenta().getCocinacodCocina();
            if (!ret.contains(coc)) {
                ret.add(coc);
            }
        }
        Collections.sort(ret);
        return ret;
    }

    @Override
    public float getTotalRecaudado() {
        return recaudacion;
    }

    @Override
    public float getGanancias() {
        return recaudacion - invertido;
    }

    @Override
    public float getDineroInvertido() {
        return invertido;
    }

    @Override
    public float getGastosDeLaCasa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createResumen(Date del, Date al, Cocina cocina) {
        this.cocina = cocina;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, del.getYear());
        c.set(Calendar.MONTH, del.getMonth());
        c.set(Calendar.DAY_OF_MONTH, del.getDate());
        v = new Venta();
        boolean initDateNotSet = true;
//        v.setFecha(selectedVentas.get(0).getFecha());
        v.setVentaTotal(0.0);
        v.setOrdenList(new ArrayList<>());
        v.setGastoVentaList(new ArrayList<>());
        v.setAsistenciaPersonalList(new ArrayList<>());
        v.setVentagastosEninsumos(0.0);
        v.setVentapropina((float) 0.0);
        Date current;

        while ((current = new Date(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH))).
                compareTo(al) <= 0) {
            Venta ve = getModel().find(current);
            if (ve != null) {
                v.getOrdenList().addAll(ve.getOrdenList());
                v.getGastoVentaList().addAll(ve.getGastoVentaList());
                if (ve.getVentaTotal() != null) {
                    v.setVentaTotal(v.getVentaTotal() + ve.getVentaTotal());
                }
                if (ve.getVentagastosEninsumos() != null) {
                    v.setVentagastosEninsumos(v.getVentagastosEninsumos() + ve.getVentagastosEninsumos());
                }
                if (ve.getVentapropina() != null) {
                    v.setVentapropina(v.getVentapropina() + ve.getVentapropina());
                }
                if (initDateNotSet) {
                    v.setFecha(current);
                    initDateNotSet = false;
                }
                if (ve.getAsistenciaPersonalList() != null) {
                    v.getAsistenciaPersonalList().addAll(ve.getAsistenciaPersonalList());
                }
            }
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @Override
    public void constructView(Container parent) {
    }

}
