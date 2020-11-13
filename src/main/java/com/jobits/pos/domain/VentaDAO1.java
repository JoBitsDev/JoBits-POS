package com.jobits.pos.domain;

import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Personal;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.domain.models.Orden;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.adapters.repo.impl.AsistenciaPersonalDAO;

import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.utils.utils;
import com.jobits.pos.ui.venta.presenter.ResumenVentaAreaTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.ui.venta.presenter.ResumenVentaUsuarioTablaModel;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaDAO1 {

    //******************************************************************************************************************
    //******************************************************************************************************************
    // ********************************Resumenes de ventas***********************************************************
    //******************************************************************************************************************
    //******************************************************************************************************************
    public static float getResumenVentasOnTable(JTable tabla, Venta v) {
        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductovOrden> ret = getResumenVentas(v);
        //convirtiendo a rowData
        float total = convertProductoOrdenToRowData(rowData, ret);
        //llenando la tabla
        utils.AddToTable(rowData, tabla);
        return total;
    }

    public static float getResumenVentasDeLaCasaOnTable(JTable tabla, Venta v) {
        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        llenarArrayProductoVOrden(ret, aux, null, true, false);

        //convirtiendo a rowData
        float total = convertProductoOrdenToRowData(rowData, ret);

        //llenando la tabla
        utils.AddToTable(rowData, tabla);

        return total;
    }

    public static float getResumenVentasDeLaCasaXCocinaOnTable(JTable tabla, Venta v, Cocina c) {
        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        llenarArrayProductoVOrden(ret, aux, c, true, false);

        //convirtiendo a rowData
        float total = convertProductoOrdenToRowData(rowData, ret);

        //llenando la tabla
        utils.AddToTable(rowData, tabla);

        return total;

    }

    public static void getResumenVentasCamareroOnTable(JTable tabla, Venta v, Personal p) {

        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        float total = 0;
        int totalOrdenes = 0;
        float pago_por_ventas = 0;
        //llenando l array
        for (Orden o : aux) {
            if (o.getPersonalusuario() != null) {
                if (!o.getDeLaCasa() && o.getPersonalusuario().equals(p) && o.getHoraTerminada() != null) {
                    total += o.getOrdenvalorMonetario();
                    totalOrdenes++;
                    for (ProductovOrden pv : o.getProductovOrdenList()) {
                        if (pv.getProductoVenta().getPagoPorVenta() != null) {
                            pago_por_ventas += pv.getCantidad() * pv.getProductoVenta().getPagoPorVenta();
                        }
                    }
                }
            }
        }//n

        //convirtiendo a rowData
        if (total != 0) {
            rowData[0].add(p.getUsuario());
            rowData[1].add(utils.setDosLugaresDecimales(total));
            rowData[2].add(totalOrdenes);
            rowData[3].add(utils.setDosLugaresDecimales(pago_por_ventas));

            //llenando la tabla
            utils.AddToTable(rowData, tabla);
        }
    }

    /**
     * crea un resumen del total que ha vendido cda cocina
     *
     * @param tabla
     * @param v
     * @param c
     */
    public static void getResumenVentasCocinaOnTable(JTable tabla, Venta v, Cocina c) {
        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[3]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        llenarArrayProductoVOrden(ret, aux, c, false, false);

        //convirtiendo a rowData
        float total = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            total += pv.getPrecioVenta() * x.getCantidad();
        }
        if (total != 0) {
            rowData[0].add(c.getCodCocina());
            rowData[1].add(c.getNombreCocina());
            rowData[2].add(utils.setDosLugaresDecimales(total));

            //llenando la tabla
            utils.AddToTable(rowData, tabla);

        }

    }

    public static float getResumenVentasCocinaDetalladoOnTable(JTable tabla, Venta v, Cocina c) {
        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        llenarArrayProductoVOrden(ret, aux, c, false, false);

        //convirtiendo a rowData
        float total = convertProductoOrdenToRowData(rowData, ret);

        //llenando la tabla
        utils.AddToTable(rowData, tabla);

        return total;
    }

    public static void getResumenVentaPorAreaOnTable(JTable tabla, Venta v, Area a) {
        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        float totalReal = 0;
        for (Orden o : aux) {
            if (o.getMesacodMesa() != null) {
                if (o.getMesacodMesa().getAreacodArea().equals(a) && !o.getDeLaCasa()) {
                    joinListsProductovOrdenByCocina(ret, new ArrayList<>(o.getProductovOrdenList()), null);
                    totalReal += o.getOrdenvalorMonetario();
                }
            }
        }//nˆ3

        //convirtiendo a rowData
        float totalNeta = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            totalNeta += pv.getPrecioVenta() * x.getCantidad();
        }
        if (totalNeta != 0) {
            rowData[0].add(a.getCodArea());
            rowData[1].add(a.getNombre());
            rowData[2].add(utils.setDosLugaresDecimales(totalNeta));
            rowData[3].add(utils.setDosLugaresDecimales(totalReal));

            //llenando la tabla
            utils.AddToTable(rowData, tabla);

        }
    }

    public static ResumenVentaAreaTablaModel getResumenVentaPorArea(Venta v, Area a) {
        //inicializando los datos
        ResumenVentaAreaTablaModel retu = null;
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        float totalReal = 0;
        for (Orden o : aux) {
            if (o.getMesacodMesa() != null) {
                if (o.getMesacodMesa().getAreacodArea().equals(a) && !o.getDeLaCasa()) {
                    joinListsProductovOrdenByCocina(ret, new ArrayList<>(o.getProductovOrdenList()), null);
                    totalReal += o.getOrdenvalorMonetario();
                }
            }

        }//nˆ3

        //convirtiendo a rowData
        float totalNeta = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            totalNeta += pv.getPrecioVenta() * x.getCantidad();
        }
        if (totalNeta != 0) {
            retu = new ResumenVentaAreaTablaModel(a,
                    utils.setDosLugaresDecimalesFloat(totalNeta),
                    utils.setDosLugaresDecimalesFloat(totalReal));

        }
        return retu;
    }

    //******************************************************************************************************************
    //******************************************************************************************************************
    // ********************************Resumenes de gastos***********************************************************
    //******************************************************************************************************************
    //******************************************************************************************************************
    public static float getResumenGastosOnTable(JTable tabla, Venta v) {

        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            for (ProductovOrden p : o.getProductovOrdenList()) {
                joinListsProductoInsumos(ret,
                        new ArrayList<>(p.getProductoVenta().
                                getProductoInsumoList()), p.getCantidad());
            }
        }

        //ordeneando los datos alfabeticamente 
        Collections.sort(ret, (ProductoInsumo o1, ProductoInsumo o2)
                -> o1.getInsumo().getNombre().compareTo(o2.getInsumo().getNombre()));

        //convirtiendo a rowData
        float total = 0;
        for (ProductoInsumo x : ret) {
            rowData[0].add(x.getInsumo().getNombre());
            rowData[1].add(x.getInsumo().getUm());
            rowData[2].add(utils.setDosLugaresDecimalesFloat(x.getCantidad()));
            rowData[3].add(utils.setDosLugaresDecimalesFloat(x.getCosto()));
            total += x.getCosto();
        }

        //llenando la tabla
        utils.AddToTable(rowData, tabla);

        return total;

    }

    public static float getResumenGastosCocinaOnTable(JTable tabla, Venta v, Cocina c) {

        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            for (ProductovOrden p : o.getProductovOrdenList()) {
                if (p.getProductoVenta().getCocinacodCocina().equals(c)) {
                    joinListsProductoInsumos(ret,
                            new ArrayList<>(p.getProductoVenta().
                                    getProductoInsumoList()), p.getCantidad());
                }
            }
        }

        //ordeneando los datos alfabeticamente 
        Collections.sort(ret, (ProductoInsumo o1, ProductoInsumo o2)
                -> o1.getInsumo().getNombre().compareTo(o2.getInsumo().getNombre()));

        //convirtiendo a rowData
        float total = 0;
        for (ProductoInsumo x : ret) {

            rowData[0].add(x.getInsumo().getNombre());
            rowData[1].add(x.getInsumo().getUm());
            rowData[2].add(x.getCantidad());
            rowData[3].add(utils.setDosLugaresDecimalesFloat(x.getCosto()));
            total += x.getCosto();
        }

        //llenando la tabla
        utils.AddToTable(rowData, tabla);

        return total;
    }

    public static float getResumenGastosDeLaCasaOnTable(JTable tabla, Venta v) {

        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getDeLaCasa()) {
                for (ProductovOrden p : o.getProductovOrdenList()) {
                    joinListsProductoInsumos(ret,
                            new ArrayList<>(p.getProductoVenta().
                                    getProductoInsumoList()), p.getCantidad());
                }
            }
        }

        //convirtiendo a rowData
        float total = 0;
        for (ProductoInsumo x : ret) {

            rowData[0].add(x.getInsumo().getNombre());
            rowData[1].add(x.getInsumo().getUm());
            rowData[2].add(x.getCantidad());
            rowData[3].add(x.getCosto());
            total += x.getCosto();
        }

        //llenando la tabla
        utils.AddToTable(rowData, tabla);
        return total;
    }

    public static float getResumenGastosDeLaCasaCocinaOnTable(JTable tabla, Venta v, Cocina c) {

        //inicializando los datos
        ArrayList[] rowData = utils.initArray(new ArrayList[4]);
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getDeLaCasa()) {
                for (ProductovOrden p : o.getProductovOrdenList()) {
                    if (p.getProductoVenta().getCocinacodCocina().equals(c)) {
                        joinListsProductoInsumos(ret,
                                new ArrayList<>(p.getProductoVenta().
                                        getProductoInsumoList()), p.getCantidad());
                    }
                }
            }
        }

        //convirtiendo a rowData
        float total = 0;
        for (ProductoInsumo x : ret) {

            rowData[0].add(x.getInsumo().getNombre());
            rowData[1].add(x.getInsumo().getUm());
            rowData[2].add(x.getCantidad());
            rowData[3].add(x.getCosto());
            total += x.getCosto();
        }

        //llenando la tabla
        utils.AddToTable(rowData, tabla);

        return total;

    }

    //******************************************************************************************************************
    //******************************************************************************************************************
    // ********************************Resumenes en objetos**********************************************************
    //******************************************************************************************************************
    //******************************************************************************************************************
    public static ArrayList<ProductovOrden> getResumenVentas(Venta v) {
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        llenarArrayProductoVOrden(ret, aux, null, false, false);

        Collections.sort(ret, (o1, o2) -> {
            return o1.getNombreProductoVendido().compareTo(o2.getNombreProductoVendido());
        });
        return ret;
    }

    public static List<ProductovOrden> getResumenVentaPorAreaOld(Venta v, Area a) {
        //inicializando los datos
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getMesacodMesa() != null) {
                if (o.getMesacodMesa().getAreacodArea().equals(a) && !o.getDeLaCasa()) {
                    joinListsProductovOrdenByCocina(ret, new ArrayList<>(o.getProductovOrdenList()), null);
                }
            }

        }//nˆ3

        return ret;
    }

    public static ResumenVentaUsuarioTablaModel getResumenVentasCamareroB(Venta v, Personal p) {

        //inicializando los datos
        ResumenVentaUsuarioTablaModel ret = null;
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        float total = 0;
        int totalOrdenes = 0;
        float pago_por_ventas = 0;
        //llenando l array
        for (Orden o : aux) {
            if (o.getPersonalusuario() != null) {
                if (!o.getDeLaCasa() && o.getPersonalusuario().equals(p) && o.getHoraTerminada() != null) {
                    total += o.getOrdenvalorMonetario();
                    totalOrdenes++;
                    for (ProductovOrden pv : o.getProductovOrdenList()) {
                        if (pv.getProductoVenta().getPagoPorVenta() != null) {
                            pago_por_ventas += pv.getCantidad() * pv.getProductoVenta().getPagoPorVenta();
                        }
                    }
                }
            }
        }//n

        //convirtiendo a rowData
        if (total != 0) {
            ret = new ResumenVentaUsuarioTablaModel(p,
                    utils.setDosLugaresDecimales(total),
                    "" + totalOrdenes,
                    utils.setDosLugaresDecimales(pago_por_ventas));
        }
        return ret;
    }

    public static List<ProductovOrden> getResumenVentasCamarero(Venta v, Personal p) {

        //inicializando los datos
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (o.getPersonalusuario() != null) {
                if (!o.getDeLaCasa() && o.getPersonalusuario().equals(p)) {
                    joinListsProductovOrdenByCocina(ret,
                            new ArrayList(o.getProductovOrdenList()), null);
                }
            }
        }//nˆ3

        return ret;
    }

    public static ResumenVentaPtoElabTablaModel getResumenVentasCocinaOnTable(Venta v, Cocina c) {
        //inicializando los datos
        ResumenVentaPtoElabTablaModel resumen = null;
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        llenarArrayProductoVOrden(ret, aux, c, false, false);

        //convirtiendo a rowData
        float total = 0;
        for (ProductovOrden x : ret) {
            ProductoVenta pv = x.getProductoVenta();
            total += pv.getPrecioVenta() * x.getCantidad();
        }
        if (total != 0) {
            resumen = new ResumenVentaPtoElabTablaModel(c, utils.setDosLugaresDecimales(total));

        }

        return resumen;

    }

    public static List<ProductovOrden> getResumenVentasCocina(Venta v, Cocina c) {
        //inicializando los datos
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            if (!o.getDeLaCasa() || R.CONSUMO_DE_LA_CASA_EN_ESTADISTICAS) {
                joinListsProductovOrdenByCocina(ret,
                        new ArrayList<>(o.getProductovOrdenList()), c);
            }

        }//nˆ3

        return ret;
    }

    public static List<ProductovOrden> getResumenVentasCasa(Venta v) {
        //inicializando los datos
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        llenarArrayProductoVOrden(ret, aux, null, true, false);

        return ret;
    }

    //
    //Métodos referentes a los resumenes del consumo de insumos
    //
    public static List<ProductoInsumo> getResumenGastosCocina(Cocina c, Venta v) {

        //inicializando los datos
        ArrayList<ProductoInsumo> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        //llenando l array
        for (Orden o : aux) {
            for (ProductovOrden p : o.getProductovOrdenList()) {
                if (p.getProductoVenta().getCocinacodCocina().equals(c)) {
                    joinListsProductoInsumos(ret,
                            new ArrayList<>(p.getProductoVenta().
                                    getProductoInsumoList()), p.getCantidad());
                }
            }
        }
        return ret;
    }

    //******************************************************************************************************************
    //******************************************************************************************************************
    // ********************************Metodos para estadisticas de ventas*******************************************
    //******************************************************************************************************************
    //******************************************************************************************************************
    public static float getValorTotalVentas(Venta v) {
        float total = 0;
        for (Orden x : v.getOrdenList()) {
            if (!x.getDeLaCasa() && x.getHoraTerminada() != null) {
                total += x.getOrdenvalorMonetario();
            }
        }
        return total;
    }

    public static float getValorVentasCocina(Venta v, Cocina c) {
        //inicializando los datos
        ArrayList<ProductovOrden> ret = new ArrayList<>();
        ArrayList<Orden> aux = new ArrayList(v.getOrdenList());

        llenarArrayProductoVOrden(ret, aux, c, false, false);

        float valor = 0;
        for (ProductovOrden x : ret) {
            valor += x.getCantidad() * x.getPrecioVendido();
        }

        return valor;
    }

    public static List<Orden> getOrdenesActivas(Venta ventas) {

        ArrayList<Orden> ordenes = new ArrayList<>(ventas.getOrdenList());

        Collections.sort(ordenes, (Orden o1, Orden o2) -> {
            int idO1, idO2;
            idO1 = Integer.parseInt(o1.getCodOrden().substring(2));
            idO2 = Integer.parseInt(o2.getCodOrden().substring(2));
            return -1 * Integer.compare(idO1, idO2);
        });

        List<Orden> retOrd = new ArrayList<>();
        List<String> existingMesasName = new ArrayList<>();

        ordenes.forEach((o) -> {
            if (o.getMesacodMesa() != null) {
                String codMesa = o.getMesacodMesa().getCodMesa();

                if (o.getHoraTerminada() == null) {
                    retOrd.add(o);
                    existingMesasName.add(codMesa);

                } else {
                    if (!existingMesasName.contains(codMesa)) {
                        //if(o.getHoraTerminada().)
                        retOrd.add(o);
                        existingMesasName.add(codMesa);
                    }
                }
            }
        });
        return retOrd;
    }

    public static float getValorTotalGastosInsumo(Venta instance) {
        float total = 0;
        for (Orden x : instance.getOrdenList()) {
            if (x.getHoraTerminada() != null) {
                total += x.getOrdengastoEninsumos();
            }
        }
        return total;
    }

    /**
     *
     * @param v - la Venta a calcular la hora pico
     * @return un entero del 0-23 con la hora pico del dia de ventas
     */
    public static int getPickHour(Venta v) {
//        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(v.getFecha().getTime());
//        int current_day = c.get(Calendar.DAY_OF_MONTH);
        float monto_hora_pico = 0;
        int hora_pico = 0,
                hora = 0;

        Collections.sort(v.getOrdenList(), (o1, o2) -> {
            return o1.getCodOrden().compareTo(o2.getCodOrden());
        });
        while (hora < 24) {
            float aux_hora_pico = 0;
            for (Orden o : v.getOrdenList()) {
                if (o.getHoraComenzada().getHours() == hora && !o.getDeLaCasa()) {
                    aux_hora_pico += o.getOrdenvalorMonetario();
                }
            }
            if (aux_hora_pico > monto_hora_pico) {
                monto_hora_pico = aux_hora_pico;
                hora_pico = hora;
            }
            hora++;
        }
        return hora_pico;
    }

    public static int getModalPickHour(List<Venta> ventas) {
        int[] modas = new int[24];
        ventas.forEach((v) -> {
            modas[getPickHour(v)]++;
        });
        int mayor_moda = 0, cantidad_repeticiones = 0;

        for (int i = 0; i < modas.length; i++) {
            if (modas[i] > cantidad_repeticiones) {
                cantidad_repeticiones = modas[i];
                mayor_moda = i;
            }
        }
        return mayor_moda;
    }

    public static float getValorTotalVentasNeta(Venta v) {
        float total = 0;
        for (Orden x : v.getOrdenList()) {
            if (!x.getDeLaCasa() && x.getHoraTerminada() != null) {
                for (ProductovOrden p : x.getProductovOrdenList()) {
                    total += p.getCantidad() * p.getPrecioVendido();
                }
            }
        }
        return total;
    }

    public static float getValorTotalPagoTrabajadores(Venta instance) {
        float pagoTotal = 0;
        for (AsistenciaPersonal a : instance.getAsistenciaPersonalList()) {
            pagoTotal += a.getPago();
            if (a.getAMayores() != null) {
                pagoTotal += a.getAMayores();
            }
        }
        return utils.setDosLugaresDecimalesFloat(pagoTotal);
    }

    public static float getValorPagoPorVentas(Venta instance) {
        ArrayList<ProductovOrden> list = getResumenVentas(instance);
        float ret = 0;
        for (ProductovOrden x : list) {
            ProductoVenta v = x.getProductoVenta();
            ret += v.getPagoPorVenta() != null ? v.getPagoPorVenta() * x.getCantidad() : 0;
        }
        return utils.setDosLugaresDecimalesFloat(ret);
    }

    public static float getValorTotalPorcientoVenta(Venta v) {
        return getValorTotalVentas(v) - getValorTotalVentasNeta(v);
    }

    //******************************************************************************************************************
    //******************************************************************************************************************
    // ********************************Metodos privados de la clase **************************************************
    //******************************************************************************************************************
    //******************************************************************************************************************
    /**
     * agrega a un arreglo de ProductosvOrdenes una nueva orden
     *
     * @param pivot - el arreglo al que agregar la nueva orden
     * @param b - la orden a agregar
     *
     */
    private static void llenarArrayProductoVOrden(ArrayList<ProductovOrden> ret, ArrayList<Orden> aux, Cocina c, boolean condicionAutorizo, boolean aceptarOrdenesAbiertas) {
        for (Orden o : aux) {
            if (o.getDeLaCasa() == condicionAutorizo) {
                if (aceptarOrdenesAbiertas || o.getHoraTerminada() != null) {
                    joinListsProductovOrdenByCocina(ret,
                            new ArrayList(o.getProductovOrdenList()), c);
                }
            }
        }
    }

    private static float convertProductoOrdenToRowData(ArrayList[] rowData, ArrayList<ProductovOrden> ret) {
        float total = 0;
        Collections.sort(ret, (ProductovOrden o1, ProductovOrden o2) -> o1.getNombreProductoVendido().compareTo(o2.getNombreProductoVendido()));
        for (ProductovOrden x : ret) {
            rowData[0].add(x.getNombreProductoVendido());
            rowData[1].add(x.getPrecioVendido());
            rowData[2].add(utils.setDosLugaresDecimalesFloat(x.getCantidad()));
            rowData[3].add(utils.setDosLugaresDecimalesFloat(x.getPrecioVendido() * x.getCantidad()));

            total += x.getPrecioVendido() * x.getCantidad();
        }

        return total;
    }

    private static void joinListsProductovOrdenByCocina(ArrayList<ProductovOrden> pivot,
            ArrayList<ProductovOrden> b, Cocina c) {

        while (!b.isEmpty()) {
            boolean founded = false;
            for (int j = 0; j < pivot.size() && !founded; j++) {
                if (b.get(0).getProductoVenta().getCodigoProducto().equals(
                        pivot.get(j).getProductoVenta().getCodigoProducto())) {
                    founded = true;
                    pivot.get(j).setCantidad(pivot.get(j).getCantidad() + b.get(0).getCantidad());
                }
            }
            if (!founded && (c == null || b.get(0).getProductoVenta().getCocinacodCocina().equals(c))) {
                ProductovOrden po = new ProductovOrden(b.get(0).getId());
                po.setCantidad(b.get(0).getCantidad());
                po.setEnviadosacocina(b.get(0).getEnviadosacocina());
                po.setOrden(b.get(0).getOrden());
                po.setProductoVenta(b.get(0).getProductoVenta());
                pivot.add(po);
            }
            b.remove(0);

        }

    }

    /**
     * este metodo es para acumular la relacion producto insumo en un solo
     * arreglo
     *
     * @param pivot - el arreglo que se tiene para acumular
     * @param b - el nuevo productos que se va a incluir
     * @param cant - la cantidad de ese producto
     */
    public static void joinListsProductoInsumos(
            ArrayList<ProductoInsumo> pivot,
            ArrayList<ProductoInsumo> b, float cant) {

        for (ProductoInsumo x : b) {
            boolean founded = false;

            for (int j = 0; j < pivot.size() && !founded; j++) {
                if (x.getInsumo().getCodInsumo().
                        equals(pivot.get(j).getInsumo().getCodInsumo())) {
                    founded = true;
                    pivot.get(j).setCantidad(pivot.get(j).getCantidad() + (x.getCantidad() * cant));
                    pivot.get(j).setCosto(pivot.get(j).getCosto() + (x.getCosto() * cant));
                }
            }
            if (!founded) {
                ProductoInsumo pi = new ProductoInsumo(x.getProductoInsumoPK());
                pi.setCantidad(x.getCantidad() * cant);
                pi.setCosto(x.getCosto() * cant);
                pi.setInsumo(x.getInsumo());
                pi.setProductoVenta(x.getProductoVenta());
                pivot.add(pi);

            }
        }

    }

}
