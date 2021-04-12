/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.presenter;

import com.jobits.pos.core.domain.venta.ResumenVentaAreaTablaModel;
import com.jobits.pos.core.domain.venta.ResumenVentaPtoElabTablaModel;
import com.jobits.pos.core.domain.venta.ResumenVentaUsuarioTablaModel;
import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.utils.StringsTreatment;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.io.File;
import java.util.List;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class VentaDetailViewModel extends AbstractViewModel {

    //
    // DashBoard
    //
    private String venta_neta;

    public static final String PROP_VENTA_NETA = "venta_neta";

    private String venta_total;

    public static final String PROP_VENTA_TOTAL = "venta_total";

    private String propina_total;

    public static final String PROP_PROPINA_TOTAL = "propina_total";

    private String total_gasto_insumos;

    public static final String PROP_TOTAL_GASTO_INSUMOS = "total_gasto_insumos";

    private String total_gasto_otros;

    public static final String PROP_TOTAL_GASTO_OTROS = "total_gasto_otros";

    private String total_gasto_salario;

    public static final String PROP_TOTAL_GASTO_SALARIO = "total_gasto_salario";

    private String total_autorizos;

    public static final String PROP_TOTAL_AUTORIZOS = "total_autorizos";

    private File file_for_export;

    public static final String PROP_FILE_FOR_EXPORT = "file_for_export";

    private boolean reabrir_ventas_enabled;

    public static final String PROP_REABRIR_VENTAS_ENABLED = "reabrir_ventas_enabled";

    //
    // Areas
    //
    private List<ResumenVentaAreaTablaModel> lista_resumen_area_venta = new ArrayListModel<>();

    public static final String PROP_LISTA_RESUMEN_AREA_VENTA = "lista_resumen_area_venta";

    private ResumenVentaAreaTablaModel resumem_area_seleccionada;

    public static final String PROP_RESUMEM_AREA_SELECCIONADA = "resumem_area_seleccionada";

    //
    // Usuarios Trabajando
    //
    private List<ResumenVentaUsuarioTablaModel> lista_resumen_usuario_venta = new ArrayListModel<>();

    public static final String PROP_LISTA_RESUMEN_PERSONAL_VENTA = "lista_resumen_usuario_venta";

    private ResumenVentaUsuarioTablaModel resumen_usuario_seleccionado;

    public static final String PROP_RESUMEN_USUARIO_SELECCIONADO = "resumen_usuario_seleccionado";

    //
    //Mesas
    //
    private ArrayListModel<Mesa> lista_mesas = new ArrayListModel<>();

    public static final String PROP_LISTA_MESAS = "lista_mesas";

    private Mesa mesa_seleccionada;

    public static final String PROP_MESA_SELECCIONADA = "mesa_seleccionada";

    private ArrayListModel lista_productos_por_mesa = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_POR_MESA = "lista_productos_por_mesa";

    private ProductovOrden producto_por_mesa_seleccionado;

    public static final String PROP_PRODUCTO_POR_MESA_SELECCIONADO = "producto_por_mesa_seleccionado";

    /**
     * Get the value of producto_por_mesa_seleccionado
     *
     * @return the value of producto_por_mesa_seleccionado
     */
    public ProductovOrden getProducto_por_mesa_seleccionado() {
        return producto_por_mesa_seleccionado;
    }

    /**
     * Set the value of producto_por_mesa_seleccionado
     *
     * @param producto_por_mesa_seleccionado new value of
     * producto_por_mesa_seleccionado
     */
    public void setProducto_por_mesa_seleccionado(ProductovOrden producto_por_mesa_seleccionado) {
        ProductovOrden oldProducto_por_mesa_seleccionado = this.producto_por_mesa_seleccionado;
        this.producto_por_mesa_seleccionado = producto_por_mesa_seleccionado;
        firePropertyChange(PROP_PRODUCTO_POR_MESA_SELECCIONADO, oldProducto_por_mesa_seleccionado, producto_por_mesa_seleccionado);
    }

    /**
     * Get the value of lista_productos_por_mesa
     *
     * @return the value of lista_productos_por_mesa
     */
    public ArrayListModel getLista_productos_por_mesa() {
        return lista_productos_por_mesa;
    }

    /**
     * Set the value of lista_productos_por_mesa
     *
     * @param lista_productos_por_mesa new value of lista_productos_por_mesa
     */
    public void setLista_productos_por_mesa(ArrayListModel lista_productos_por_mesa) {
        ArrayListModel oldLista_productos_por_mesa = this.lista_productos_por_mesa;
        this.lista_productos_por_mesa.clear();
        this.lista_productos_por_mesa.addAll(lista_productos_por_mesa);
        firePropertyChange(PROP_LISTA_PRODUCTOS_POR_MESA, oldLista_productos_por_mesa, lista_productos_por_mesa);
    }

    /**
     * Get the value of mesa_seleccionada
     *
     * @return the value of mesa_seleccionada
     */
    public Mesa getMesa_seleccionada() {
        return mesa_seleccionada;
    }

    /**
     * Set the value of mesa_seleccionada
     *
     * @param mesa_seleccionada new value of mesa_seleccionada
     */
    public void setMesa_seleccionada(Mesa mesa_seleccionada) {
        Mesa oldMesa_seleccionada = this.mesa_seleccionada;
        this.mesa_seleccionada = mesa_seleccionada;
        firePropertyChange(PROP_MESA_SELECCIONADA, oldMesa_seleccionada, mesa_seleccionada);
    }

    /**
     * Get the value of lista_mesas
     *
     * @return the value of lista_mesas
     */
    public ArrayListModel<Mesa> getLista_mesas() {
        return lista_mesas;
    }

    /**
     * Set the value of lista_mesas
     *
     * @param lista_mesas new value of lista_mesas
     */
    public void setLista_mesas(ArrayListModel<Mesa> lista_mesas) {
        ArrayListModel<Mesa> oldLista_mesas = this.lista_mesas;
        this.lista_mesas.clear();
        this.lista_mesas.addAll(lista_mesas);
        firePropertyChange(PROP_LISTA_MESAS, oldLista_mesas, lista_mesas);
    }

    //
    // Pto Elab
    //
    private List<ResumenVentaPtoElabTablaModel> lista_resumen_pto_venta = new ArrayListModel<>();

    public static final String PROP_LISTA_RESUMEN_PTO_VENTA = "lista_resumen_pto_venta";

    private ResumenVentaPtoElabTablaModel resumen_pto_seleccionado;

    public static final String PROP_RESUMEN_PTO_SELECCIONADO = "resumen_pto_seleccionado";

    //
    //Odenes
    //
    private List<Orden> lista_orden = new ArrayListModel<>();

    public static final String PROP_LISTA_ORDEN = "lista_orden";

    private Orden orden_seleccionada;

    public static final String PROP_ORDEN_SELECCIONADA = "orden_seleccionada";

    //
    //General
    //
    private String fecha;

    public static final String PROP_FECHA = "fecha";

    //Venta Instance
    public static Venta ventaInstance;

    public static Venta getVentaInstance() {
        return ventaInstance;
    }

    public static void setVentaInstance(Venta ventaInstance) {
        VentaDetailViewModel.ventaInstance = ventaInstance;
    }

    //Turnos 
    private ArrayListModel<Venta> list_ventas = new ArrayListModel<>();

    public static final String PROP_LIST_VENTAS = "list_ventas";

    private Venta venta_seleccionada;

    public static final String PROP_VENTA_SELECCIONADA = "venta_seleccionada";

    private boolean cambiar_turno_enabled;

    public static final String PROP_CAMBIAR_TURNO_ENABLED = "cambiar_turno_enabled";

    /**
     * Get the value of cambiar_turno_enabled
     *
     * @return the value of cambiar_turno_enabled
     */
    public boolean isCambiar_turno_enabled() {
        return cambiar_turno_enabled;
    }

    /**
     * Set the value of cambiar_turno_enabled
     *
     * @param cambiar_turno_enabled new value of cambiar_turno_enabled
     */
    public void setCambiar_turno_enabled(boolean cambiar_turno_enabled) {
        boolean oldCambiar_turno_enabled = this.cambiar_turno_enabled;
        this.cambiar_turno_enabled = cambiar_turno_enabled;
        firePropertyChange(PROP_CAMBIAR_TURNO_ENABLED, oldCambiar_turno_enabled, cambiar_turno_enabled);
    }

    /**
     * Get the value of venta_seleccionada
     *
     * @return the value of venta_seleccionada
     */
    public Venta getVenta_seleccionada() {
        return venta_seleccionada;
    }

    /**
     * Set the value of venta_seleccionada
     *
     * @param venta_seleccionada new value of venta_seleccionada
     */
    public void setVenta_seleccionada(Venta venta_seleccionada) {
        Venta oldVenta_seleccionada = this.venta_seleccionada;
        this.venta_seleccionada = venta_seleccionada;
        firePropertyChange(PROP_VENTA_SELECCIONADA, oldVenta_seleccionada, venta_seleccionada);
    }

    /**
     * Get the value of list_ventas
     *
     * @return the value of list_ventas
     */
    public ArrayListModel<Venta> getList_ventas() {
        return list_ventas;
    }

    /**
     * Set the value of list_ventas
     *
     * @param list_ventas new value of list_ventas
     */
    public void setList_ventas(ArrayListModel<Venta> list_ventas) {
        ArrayListModel<Venta> oldList_ventas = this.list_ventas;
        this.list_ventas.clear();
        this.list_ventas.addAll(list_ventas);
        firePropertyChange(PROP_LIST_VENTAS, oldList_ventas, list_ventas);
    }

    /**
     * Get the value of fecha
     *
     * @return the value of fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Set the value of fecha
     *
     * @param fecha new value of fecha
     */
    public void setFecha(String fecha) {
        String oldFecha = this.fecha;
        this.fecha = fecha;
        firePropertyChange(PROP_FECHA, oldFecha, fecha);
    }

    /**
     * Get the value of orden_seleccionada
     *
     * @return the value of orden_seleccionada
     */
    public Orden getOrden_seleccionada() {
        return orden_seleccionada;
    }

    /**
     * Set the value of orden_seleccionada
     *
     * @param orden_seleccionada new value of orden_seleccionada
     */
    public void setOrden_seleccionada(Orden orden_seleccionada) {
        Orden oldOrden_seleccionada = this.orden_seleccionada;
        this.orden_seleccionada = orden_seleccionada;
        firePropertyChange(PROP_ORDEN_SELECCIONADA, oldOrden_seleccionada, orden_seleccionada);
    }

    /**
     * Get the value of lista_orden
     *
     * @return the value of lista_orden
     */
    public List<Orden> getLista_orden() {
        return lista_orden;
    }

    /**
     * Set the value of lista_orden
     *
     * @param lista_orden new value of lista_orden
     */
    public void setLista_orden(List<Orden> lista_orden) {
        List<Orden> oldLista_orden = this.lista_orden;
        this.lista_orden.clear();
        this.lista_orden.addAll(lista_orden);
        firePropertyChange(PROP_LISTA_ORDEN, oldLista_orden, lista_orden);
    }

    /**
     * Get the value of resumen_pto_seleccionado
     *
     * @return the value of resumen_pto_seleccionado
     */
    public ResumenVentaPtoElabTablaModel getResumen_pto_seleccionado() {
        return resumen_pto_seleccionado;
    }

    /**
     * Set the value of resumen_pto_seleccionado
     *
     * @param resumen_pto_seleccionado new value of resumen_pto_seleccionado
     */
    public void setResumen_pto_seleccionado(ResumenVentaPtoElabTablaModel resumen_pto_seleccionado) {
        ResumenVentaPtoElabTablaModel oldResumen_pto_seleccionado = this.resumen_pto_seleccionado;
        this.resumen_pto_seleccionado = resumen_pto_seleccionado;
        firePropertyChange(PROP_RESUMEN_PTO_SELECCIONADO, oldResumen_pto_seleccionado, resumen_pto_seleccionado);
    }

    /**
     * Get the value of lista_resumen_pto_venta
     *
     * @return the value of lista_resumen_pto_venta
     */
    public List<ResumenVentaPtoElabTablaModel> getLista_resumen_pto_venta() {
        return lista_resumen_pto_venta;
    }

    /**
     * Set the value of lista_resumen_pto_venta
     *
     * @param lista_resumen_pto_venta new value of lista_resumen_pto_venta
     */
    public void setLista_resumen_pto_venta(List<ResumenVentaPtoElabTablaModel> lista_resumen_pto_venta) {
        List<ResumenVentaPtoElabTablaModel> oldLista_resumen_pto_venta = this.lista_resumen_pto_venta;
        this.lista_resumen_pto_venta.clear();
        this.lista_resumen_pto_venta.addAll(lista_resumen_pto_venta);
        firePropertyChange(PROP_LISTA_RESUMEN_PTO_VENTA, oldLista_resumen_pto_venta, lista_resumen_pto_venta);
    }

    /**
     * Get the value of resumen_usuario_seleccionado
     *
     * @return the value of resumen_usuario_seleccionado
     */
    public ResumenVentaUsuarioTablaModel getResumen_usuario_seleccionado() {
        return resumen_usuario_seleccionado;
    }

    /**
     * Set the value of resumen_usuario_seleccionado
     *
     * @param resumen_usuario_seleccionado new value of
     * resumen_usuario_seleccionado
     */
    public void setResumen_usuario_seleccionado(ResumenVentaUsuarioTablaModel resumen_usuario_seleccionado) {
        ResumenVentaUsuarioTablaModel oldResumen_usuario_seleccionado = this.resumen_usuario_seleccionado;
        this.resumen_usuario_seleccionado = resumen_usuario_seleccionado;
        firePropertyChange(PROP_RESUMEN_USUARIO_SELECCIONADO, oldResumen_usuario_seleccionado, resumen_usuario_seleccionado);
    }

    /**
     * Get the value of lista_resumen_usuario_venta
     *
     * @return the value of lista_resumen_usuario_venta
     */
    public List<ResumenVentaUsuarioTablaModel> getLista_resumen_usuario_venta() {
        return lista_resumen_usuario_venta;
    }

    /**
     * Set the value of lista_resumen_usuario_venta
     *
     * @param lista_resumen_usuario_venta new value of
     * lista_resumen_usuario_venta
     */
    public void setLista_resumen_usuario_venta(List<ResumenVentaUsuarioTablaModel> lista_resumen_usuario_venta) {
        List<ResumenVentaUsuarioTablaModel> oldLista_resumen_personal_venta = this.lista_resumen_usuario_venta;
        this.lista_resumen_usuario_venta.clear();
        this.lista_resumen_usuario_venta.addAll(lista_resumen_usuario_venta);
        firePropertyChange(PROP_LISTA_RESUMEN_PERSONAL_VENTA, oldLista_resumen_personal_venta, lista_resumen_usuario_venta);
    }

    /**
     * Get the value of resumem_area_seleccionada
     *
     * @return the value of resumem_area_seleccionada
     */
    public ResumenVentaAreaTablaModel getResumem_area_seleccionada() {
        return resumem_area_seleccionada;
    }

    /**
     * Set the value of resumem_area_seleccionada
     *
     * @param resumem_area_seleccionada new value of resumem_area_seleccionada
     */
    public void setResumem_area_seleccionada(ResumenVentaAreaTablaModel resumem_area_seleccionada) {
        ResumenVentaAreaTablaModel oldResumem_area_seleccionada = this.resumem_area_seleccionada;
        this.resumem_area_seleccionada = resumem_area_seleccionada;
        firePropertyChange(PROP_RESUMEM_AREA_SELECCIONADA, oldResumem_area_seleccionada, resumem_area_seleccionada);
    }

    /**
     * Get the value of lista_resumen_area_venta
     *
     * @return the value of lista_resumen_area_venta
     */
    public List<ResumenVentaAreaTablaModel> getLista_resumen_area_venta() {
        return lista_resumen_area_venta;
    }

    /**
     * Set the value of lista_resumen_area_venta
     *
     * @param lista_resumen_area_venta new value of lista_resumen_area_venta
     */
    public void setLista_resumen_area_venta(List<ResumenVentaAreaTablaModel> lista_resumen_area_venta) {
        List<ResumenVentaAreaTablaModel> oldLista_resumen_area_venta = this.lista_resumen_area_venta;
        this.lista_resumen_area_venta.clear();
        this.lista_resumen_area_venta.addAll(lista_resumen_area_venta);
        firePropertyChange(PROP_LISTA_RESUMEN_AREA_VENTA, oldLista_resumen_area_venta, lista_resumen_area_venta);
    }

    /**
     * Get the value of reabrir_ventas_enabled
     *
     * @return the value of reabrir_ventas_enabled
     */
    public boolean isReabrir_ventas_enabled() {
        return reabrir_ventas_enabled;
    }

    /**
     * Set the value of reabrir_ventas_enabled
     *
     * @param reabrir_ventas_enabled new value of reabrir_ventas_enabled
     */
    public void setReabrir_ventas_enabled(boolean reabrir_ventas_enabled) {
        boolean oldReabrir_ventas_enabled = this.reabrir_ventas_enabled;
        this.reabrir_ventas_enabled = reabrir_ventas_enabled;
        firePropertyChange(PROP_REABRIR_VENTAS_ENABLED, oldReabrir_ventas_enabled, reabrir_ventas_enabled);
    }

    /**
     * Get the value of file_for_export
     *
     * @return the value of file_for_export
     */
    public File getFile_for_export() {
        return file_for_export;
    }

    /**
     * Set the value of file_for_export
     *
     * @param file_for_export new value of file_for_export
     */
    public void setFile_for_export(File file_for_export) {
        File oldFile_for_export = this.file_for_export;
        this.file_for_export = file_for_export;
        firePropertyChange(PROP_FILE_FOR_EXPORT, oldFile_for_export, file_for_export);
    }

    /**
     * Get the value of total_autorizos
     *
     * @return the value of total_autorizos
     */
    public String getTotal_autorizos() {
        return total_autorizos;
    }

    /**
     * Set the value of total_autorizos
     *
     * @param total_autorizos new value of total_autorizos
     */
    public void setTotal_autorizos(String total_autorizos) {
        String totalAutorizosEspaciado = StringsTreatment.stringFiller(total_autorizos);
        String oldTotal_autorizos = this.total_autorizos;
        this.total_autorizos = totalAutorizosEspaciado;
        firePropertyChange(PROP_TOTAL_AUTORIZOS, oldTotal_autorizos, totalAutorizosEspaciado);
    }

    /**
     * Get the value of total_gasto_salario
     *
     * @return the value of total_gasto_salario
     */
    public String getTotal_gasto_salario() {
        return total_gasto_salario;
    }

    /**
     * Set the value of total_gasto_salario
     *
     * @param total_gasto_salario new value of total_gasto_salario
     */
    public void setTotal_gasto_salario(String total_gasto_salario) {
        String totalGastoSalarioEspaciado = StringsTreatment.stringFiller(total_gasto_salario);
        String oldTotal_gasto_salario = this.total_gasto_salario;
        this.total_gasto_salario = totalGastoSalarioEspaciado;
        firePropertyChange(PROP_TOTAL_GASTO_SALARIO, oldTotal_gasto_salario, totalGastoSalarioEspaciado);
    }

    /**
     * Get the value of total_gasto_otros
     *
     * @return the value of total_gasto_otros
     */
    public String getTotal_gasto_otros() {
        return total_gasto_otros;
    }

    /**
     * Set the value of total_gasto_otros
     *
     * @param total_gasto_otros new value of total_gasto_otros
     */
    public void setTotal_gasto_otros(String total_gasto_otros) {
        String totalGastoOtrosEspaciado = StringsTreatment.stringFiller(total_gasto_otros);
        String oldTotal_gasto_otros = this.total_gasto_otros;
        this.total_gasto_otros = totalGastoOtrosEspaciado;
        firePropertyChange(PROP_TOTAL_GASTO_OTROS, oldTotal_gasto_otros, totalGastoOtrosEspaciado);
    }

    /**
     * Get the value of total_gasto_insumos
     *
     * @return the value of total_gasto_insumos
     */
    public String getTotal_gasto_insumos() {
        return total_gasto_insumos;
    }

    /**
     * Set the value of total_gasto_insumos
     *
     * @param total_gasto_insumos new value of total_gasto_insumos
     */
    public void setTotal_gasto_insumos(String total_gasto_insumos) {
        String totalGastoInsumosEspaciado = StringsTreatment.stringFiller(total_gasto_insumos);
        String oldTotal_gasto_insumos = this.total_gasto_insumos;
        this.total_gasto_insumos = totalGastoInsumosEspaciado;
        firePropertyChange(PROP_TOTAL_GASTO_INSUMOS, oldTotal_gasto_insumos, totalGastoInsumosEspaciado);
    }

    /**
     * Get the value of propina_total
     *
     * @return the value of propina_total
     */
    public String getPropina_total() {
        return propina_total;
    }

    /**
     * Set the value of propina_total
     *
     * @param propina_total new value of propina_total
     */
    public void setPropina_total(String propina_total) {
        String oldPropina_total = this.propina_total;
        this.propina_total = propina_total;
        firePropertyChange(PROP_PROPINA_TOTAL, oldPropina_total, propina_total);
    }

    /**
     * Get the value of venta_total
     *
     * @return the value of venta_total
     */
    public String getVenta_total() {
        return venta_total;
    }

    /**
     * Set the value of venta_total
     *
     * @param venta_total new value of venta_total
     */
    public void setVenta_total(String venta_total) {
        String ventaTotalEspaciada = StringsTreatment.stringFiller(venta_total);
        String oldVenta_total = this.venta_total;
        this.venta_total = ventaTotalEspaciada;
        firePropertyChange(PROP_VENTA_TOTAL, oldVenta_total, ventaTotalEspaciada);
    }

    /**
     * Get the value of venta_neta
     *
     * @return the value of venta_neta
     */
    public String getVenta_neta() {
        return venta_neta;
    }

    /**
     * Set the value of venta_neta
     *
     * @param venta_neta new value of venta_neta
     */
    public void setVenta_neta(String venta_neta) {
        String ventaNetaEspaciada = StringsTreatment.stringFiller(venta_neta);
        String oldVenta_neta = this.venta_neta;
        this.venta_neta = ventaNetaEspaciada;
        firePropertyChange(PROP_VENTA_NETA, oldVenta_neta, ventaNetaEspaciada);
    }

}
