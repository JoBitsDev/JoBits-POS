/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.almacen;

import com.jobits.pos.controller.almacen.AlmacenManageController.CheckBoxType;
import com.jobits.pos.domain.TransaccionSimple;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.Operacion;
import com.jobits.pos.domain.models.Transaccion;
import com.jobits.pos.domain.models.TransaccionEntrada;
import com.jobits.pos.domain.models.TransaccionMerma;
import com.jobits.pos.domain.models.TransaccionSalida;
import com.jobits.pos.domain.models.TransaccionTransformacion;
import com.jobits.pos.domain.models.TransaccionTraspaso;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Home
 */
public interface AlmacenManageService {

    void agregarInsumoAlmacen(Insumo i);

    //
    // Operaciones del controlador
    //
    /**
     *
     * @param parent the value of parent
     */
    void constructView(Container parent);

    boolean crearOperacion(ArrayList<TransaccionSimple> transacciones, CheckBoxType tipoOperacion, Date date, String recibo, Date fechaFactura);

    /**
     *
     * @param o
     * @param ins
     * @param tipo 0-entrada, 1- salida, 2-merma, 3 traspaso
     * @param destino sino es de tipo destino este parametro es nulo
     * @param destinoTraspaso
     * @param cantidad
     * @param importe
     * @param causaRebaja
     */
    void crearTransaccion(Operacion o, InsumoAlmacen ins, int tipo, Cocina destino, Almacen destinoTraspaso, float cantidad, float importe, String causaRebaja, boolean showSuccesDialog, Integer idVenta);

    void crearTransformacion(InsumoAlmacen selected, float cantidad, List<TransaccionTransformacion> items, Almacen destino);

    void createInsumo(InsumoAlmacen newInsumo);

    Almacen createNewInstance();

    InsumoAlmacen findInsumo(Insumo ins);

    List<Cocina> getCocinaList();

    List<InsumoAlmacen> getInsumoAlmacenList(Almacen a);

    List<Insumo> getInsumoList();

    public Almacen getInstance();

    public List<Almacen> getItems();

    public void darEntradaAInsumo(TransaccionEntrada x);

    public void darSalidaAInsumo(TransaccionSalida x, int idVenta);

    public void darMermaInsumo(TransaccionMerma x);

    public void darTransformacionAInsumo(Transaccion t, Almacen a);

    public void darTraspasoInsumo(TransaccionTraspaso x);

    void imprimirReporteParaCompras(Almacen a);

    void imprimirResumenAlmacen(Almacen a);

    void modificarStock(Insumo i);

    void removeInsumoFromStorage(InsumoAlmacen objectAtSelectedRow);

    Integer selectIdFecha(Date fecha);

    void setCentroElaboracion(boolean selected);

    void updateValorTotalAlmacen(Almacen instance);

    void verTransacciones(Almacen a);

}
