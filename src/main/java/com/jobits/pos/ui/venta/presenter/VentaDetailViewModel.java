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
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Cocina;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.core.domain.models.Personal;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.core.domain.models.Venta;
import com.jobits.pos.recursos.R;
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

    private String propina_total = "0";

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
    private ArrayListModel<String> lista_mesas = new ArrayListModel<>();

    public static final String PROP_LISTA_MESAS = "lista_mesas";

    private String mesa_seleccionada;

    public static final String PROP_MESA_SELECCIONADA = "mesa_seleccionada";

    private ArrayListModel lista_productos_por_mesa = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_POR_MESA = "lista_productos_por_mesa";

    private ProductovOrden producto_por_mesa_seleccionado;

    public static final String PROP_PRODUCTO_POR_MESA_SELECCIONADO = "producto_por_mesa_seleccionado";

    //    
    //Punto Elaboracion    
    //    
    private ArrayListModel<String> lista_cocinas = new ArrayListModel<>();

    public static final String PROP_LISTA_COCINAS = "lista_cocinas";

    private String cocina_seleccionada;

    public static final String PROP_COCINA_SELECCIONADA = "cocina_seleccionada";

    private ArrayListModel<ProductovOrden> lista_productos_por_cocina = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_POR_COCINA = "lista_productos_por_cocina";

    private ProductovOrden producto_por_cocina_seleccionado;

    public static final String PROP_PRODUCTO_POR_COCINA_SELECCIONADO = "producto_por_cocina_seleccionado";

    //
    //Area
    //
    private ArrayListModel<String> lista_areas = new ArrayListModel<>();

    public static final String PROP_LISTA_AREAS = "lista_areas";

    private String area_seleccionada;

    public static final String PROP_AREA_SELECCIONADA = "area_seleccionada";

    private ArrayListModel<ProductovOrden> lista_productos_por_area = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_POR_AREA = "lista_productos_por_area";

    private ProductovOrden producto_por_area_seleccionado;

    public static final String PROP_PRODUCTO_POR_AREA_SELECCIONADO = "producto_por_area_seleccionado";

    //
    //Dependientes
    //
    private ArrayListModel<String> lista_dependientes = new ArrayListModel<>();

    public static final String PROP_LISTA_DEPENDIENTES = "lista_dependientes";

    private String personal_seleccionado;

    public static final String PROP_PERSONAL_SELECCIONADO = "personal_seleccionado";

    private ArrayListModel<ProductovOrden> lista_productos_por_dependientes = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_POR_DEPENDIENTES = "lista_productos_por_dependientes";

    private ProductovOrden producto_por_dependiente_seleccionado;

    public static final String PROP_PRODUCTO_POR_DEPENDIENTE_SELECCIONADO = "producto_por_dependiente_seleccionado";

    /**
     * Get the value of producto_por_dependiente_seleccionado
     *
     * @return the value of producto_por_dependiente_seleccionado
     */
    public ProductovOrden getProducto_por_dependiente_seleccionado() {
        return producto_por_dependiente_seleccionado;
    }

    /**
     * Set the value of producto_por_dependiente_seleccionado
     *
     * @param producto_por_dependiente_seleccionado new value of
     * producto_por_dependiente_seleccionado
     */
    public void setProducto_por_dependiente_seleccionado(ProductovOrden producto_por_dependiente_seleccionado) {
        ProductovOrden oldProducto_por_dependiente_seleccionado = this.producto_por_dependiente_seleccionado;
        this.producto_por_dependiente_seleccionado = producto_por_dependiente_seleccionado;
        firePropertyChange(PROP_PRODUCTO_POR_DEPENDIENTE_SELECCIONADO, oldProducto_por_dependiente_seleccionado, producto_por_dependiente_seleccionado);
    }

    /**
     * Get the value of lista_productos_por_dependientes
     *
     * @return the value of lista_productos_por_dependientes
     */
    public ArrayListModel<ProductovOrden> getLista_productos_por_dependientes() {
        return lista_productos_por_dependientes;
    }

    /**
     * Set the value of lista_productos_por_dependientes
     *
     * @param lista_productos_por_dependientes new value of
     * lista_productos_por_dependientes
     */
    public void setLista_productos_por_dependientes(ArrayListModel<ProductovOrden> lista_productos_por_dependientes) {
        ArrayListModel<ProductovOrden> oldLista_productos_por_dependientes = this.lista_productos_por_dependientes;
        this.lista_productos_por_dependientes.clear();
        this.lista_productos_por_dependientes.addAll(lista_productos_por_dependientes);
        firePropertyChange(PROP_LISTA_PRODUCTOS_POR_DEPENDIENTES, oldLista_productos_por_dependientes, lista_productos_por_dependientes);
    }

    /**
     * Get the value of personal_seleccionado
     *
     * @return the value of personal_seleccionado
     */
    public String getPersonal_seleccionado() {
        return personal_seleccionado;
    }

    /**
     * Set the value of personal_seleccionado
     *
     * @param personal_seleccionado new value of personal_seleccionado
     */
    public void setPersonal_seleccionado(String personal_seleccionado) {
        String oldPersonal_seleccionado = this.personal_seleccionado;
        this.personal_seleccionado = personal_seleccionado;
        firePropertyChange(PROP_PERSONAL_SELECCIONADO, oldPersonal_seleccionado, personal_seleccionado);
    }

    /**
     * Get the value of lista_dependientes
     *
     * @return the value of lista_dependientes
     */
    public ArrayListModel<String> getLista_dependientes() {
        return lista_dependientes;
    }

    /**
     * Set the value of lista_dependientes
     *
     * @param lista_dependientes new value of lista_dependientes
     */
    public void setLista_dependientes(ArrayListModel<String> lista_dependientes) {
        ArrayListModel<String> oldLista_dependientes = this.lista_dependientes;
        this.lista_dependientes.clear();
        this.lista_dependientes.addAll(lista_dependientes);
        firePropertyChange(PROP_LISTA_DEPENDIENTES, oldLista_dependientes, lista_dependientes);
    }

    /**
     * Get the value of producto_por_area_seleccionado
     *
     * @return the value of producto_por_area_seleccionado
     */
    public ProductovOrden getProducto_por_area_seleccionado() {
        return producto_por_area_seleccionado;
    }

    /**
     * Set the value of producto_por_area_seleccionado
     *
     * @param producto_por_area_seleccionado new value of
     * producto_por_area_seleccionado
     */
    public void setProducto_por_area_seleccionado(ProductovOrden producto_por_area_seleccionado) {
        ProductovOrden oldProducto_por_area_seleccionado = this.producto_por_area_seleccionado;
        this.producto_por_area_seleccionado = producto_por_area_seleccionado;
        firePropertyChange(PROP_PRODUCTO_POR_AREA_SELECCIONADO, oldProducto_por_area_seleccionado, producto_por_area_seleccionado);
    }

    /**
     * Get the value of lista_productos_por_area
     *
     * @return the value of lista_productos_por_area
     */
    public ArrayListModel<ProductovOrden> getLista_productos_por_area() {
        return lista_productos_por_area;
    }

    /**
     * Set the value of lista_productos_por_area
     *
     * @param lista_productos_por_area new value of lista_productos_por_area
     */
    public void setLista_productos_por_area(ArrayListModel<ProductovOrden> lista_productos_por_area) {
        ArrayListModel<ProductovOrden> oldLista_productos_por_area = this.lista_productos_por_area;
        this.lista_productos_por_area.clear();
        this.lista_productos_por_area.addAll(lista_productos_por_area);
        firePropertyChange(PROP_LISTA_PRODUCTOS_POR_AREA, oldLista_productos_por_area, lista_productos_por_area);
    }

    /**
     * Get the value of area_seleccionada
     *
     * @return the value of area_seleccionada
     */
    public String getArea_seleccionada() {
        return area_seleccionada;
    }

    /**
     * Set the value of area_seleccionada
     *
     * @param area_seleccionada new value of area_seleccionada
     */
    public void setArea_seleccionada(String area_seleccionada) {
        String oldArea_seleccionada = this.area_seleccionada;
        this.area_seleccionada = area_seleccionada;
        firePropertyChange(PROP_AREA_SELECCIONADA, oldArea_seleccionada, area_seleccionada);
    }

    /**
     * Get the value of lista_areas
     *
     * @return the value of lista_areas
     */
    public ArrayListModel<String> getLista_areas() {
        return lista_areas;
    }

    /**
     * Set the value of lista_areas
     *
     * @param lista_areas new value of lista_areas
     */
    public void setLista_areas(ArrayListModel<String> lista_areas) {
        ArrayListModel<String> oldLista_areas = this.lista_areas;
        this.lista_areas.clear();
        this.lista_areas.addAll(lista_areas);
        firePropertyChange(PROP_LISTA_AREAS, oldLista_areas, lista_areas);
    }

    //
    //Totales Resumen
    //
    private String total_resumen_area = "xx.xx " + R.COIN_SUFFIX;

    public static final String PROP_TOTAL_RESUMEN_AREA = "total_resumen_area";

    private String total_resumen_cocina = "xx.xx " + R.COIN_SUFFIX;

    public static final String PROP_TOTAL_RESUMEN_COCINA = "total_resumen_cocina";

    private String total_resumen_dependiente = "xx.xx " + R.COIN_SUFFIX;

    public static final String PROP_TOTAL_RESUMEN_DEPENDIENTE = "total_resumen_dependiente";

    private String total_resumen_mesa = "xx.xx " + R.COIN_SUFFIX;

    public static final String PROP_TOTAL_RESUMEN_MESA = "total_resumen_mesa";

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

    //Turnos 
    private int id_venta_seleccionada = -1;

    public static final String PROP_VENTA_SELECCIONADA = "id_venta_seleccionada";

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
    public int getVenta_seleccionada() {
        return id_venta_seleccionada;
    }

    /**
     * Set the value of venta_seleccionada
     *
     * @param venta_seleccionada new value of venta_seleccionada
     */
    public void setVenta_seleccionada(int venta_seleccionada) {
        int oldVenta_seleccionada = this.id_venta_seleccionada;
        this.id_venta_seleccionada = venta_seleccionada;
        firePropertyChange(PROP_VENTA_SELECCIONADA, oldVenta_seleccionada, venta_seleccionada);
    }

    /**
     * Get the value of total_resumen_mesa
     *
     * @return the value of total_resumen_mesa
     */
    public String getTotal_resumen_mesa() {
        return total_resumen_mesa;
    }

    /**
     * Set the value of total_resumen_mesa
     *
     * @param total_resumen_mesa new value of total_resumen_mesa
     */
    public void setTotal_resumen_mesa(String total_resumen_mesa) {
        String oldTotal_resumen_mesa = this.total_resumen_mesa;
        this.total_resumen_mesa = total_resumen_mesa;
        firePropertyChange(PROP_TOTAL_RESUMEN_MESA, oldTotal_resumen_mesa, total_resumen_mesa);
    }

    /**
     * Get the value of total_resumen_dependiente
     *
     * @return the value of total_resumen_dependiente
     */
    public String getTotal_resumen_dependiente() {
        return total_resumen_dependiente;
    }

    /**
     * Set the value of total_resumen_dependiente
     *
     * @param total_resumen_dependiente new value of total_resumen_dependiente
     */
    public void setTotal_resumen_dependiente(String total_resumen_dependiente) {
        String oldTotal_resumen_dependiente = this.total_resumen_dependiente;
        this.total_resumen_dependiente = total_resumen_dependiente;
        firePropertyChange(PROP_TOTAL_RESUMEN_DEPENDIENTE, oldTotal_resumen_dependiente, total_resumen_dependiente);
    }

    /**
     * Get the value of total_resumen_cocina
     *
     * @return the value of total_resumen_cocina
     */
    public String getTotal_resumen_cocina() {
        return total_resumen_cocina;
    }

    /**
     * Set the value of total_resumen_cocina
     *
     * @param total_resumen_cocina new value of total_resumen_cocina
     */
    public void setTotal_resumen_cocina(String total_resumen_cocina) {
        String oldTotal_resumen_cocina = this.total_resumen_cocina;
        this.total_resumen_cocina = total_resumen_cocina;
        firePropertyChange(PROP_TOTAL_RESUMEN_COCINA, oldTotal_resumen_cocina, total_resumen_cocina);
    }

    /**
     * Get the value of total_resumen_area
     *
     * @return the value of total_resumen_area
     */
    public String getTotal_resumen_area() {
        return total_resumen_area;
    }

    /**
     * Set the value of total_resumen_area
     *
     * @param total_resumen_area new value of total_resumen_area
     */
    public void setTotal_resumen_area(String total_resumen_area) {
        String oldTotal_resumen_area = this.total_resumen_area;
        this.total_resumen_area = total_resumen_area;
        firePropertyChange(PROP_TOTAL_RESUMEN_AREA, oldTotal_resumen_area, total_resumen_area);
    }

    /**
     * Get the value of producto_por_cocina_seleccionado
     *
     * @return the value of producto_por_cocina_seleccionado
     */
    public ProductovOrden getProducto_por_cocina_seleccionado() {
        return producto_por_cocina_seleccionado;
    }

    /**
     * Set the value of producto_por_cocina_seleccionado
     *
     * @param producto_por_cocina_seleccionado new value of
     * producto_por_cocina_seleccionado
     */
    public void setProducto_por_cocina_seleccionado(ProductovOrden producto_por_cocina_seleccionado) {
        ProductovOrden oldProducto_por_cocina_seleccionado = this.producto_por_cocina_seleccionado;
        this.producto_por_cocina_seleccionado = producto_por_cocina_seleccionado;
        firePropertyChange(PROP_PRODUCTO_POR_COCINA_SELECCIONADO, oldProducto_por_cocina_seleccionado, producto_por_cocina_seleccionado);
    }

    /**
     * Get the value of lista_productos_por_cocina
     *
     * @return the value of lista_productos_por_cocina
     */
    public ArrayListModel<ProductovOrden> getLista_productos_por_cocina() {
        return lista_productos_por_cocina;
    }

    /**
     * Set the value of lista_productos_por_cocina
     *
     * @param lista_productos_por_cocina new value of lista_productos_por_cocina
     */
    public void setLista_productos_por_cocina(ArrayListModel<ProductovOrden> lista_productos_por_cocina) {
        ArrayListModel<ProductovOrden> oldLista_productos_por_cocina = this.lista_productos_por_cocina;
        this.lista_productos_por_cocina.clear();
        this.lista_productos_por_cocina.addAll(lista_productos_por_cocina);
        firePropertyChange(PROP_LISTA_PRODUCTOS_POR_COCINA, oldLista_productos_por_cocina, lista_productos_por_cocina);
    }

    /**
     * Get the value of cocina_seleccionada
     *
     * @return the value of cocina_seleccionada
     */
    public String getCocina_seleccionada() {
        return cocina_seleccionada;
    }

    /**
     * Set the value of cocina_seleccionada
     *
     * @param cocina_seleccionada new value of cocina_seleccionada
     */
    public void setCocina_seleccionada(String cocina_seleccionada) {
        String oldCocina_seleccionada = this.cocina_seleccionada;
        this.cocina_seleccionada = cocina_seleccionada;
        firePropertyChange(PROP_COCINA_SELECCIONADA, oldCocina_seleccionada, cocina_seleccionada);
    }

    /**
     * Get the value of lista_cocinas
     *
     * @return the value of lista_cocinas
     */
    public ArrayListModel<String> getLista_cocinas() {
        return lista_cocinas;
    }

    /**
     * Set the value of lista_cocinas
     *
     * @param lista_cocinas new value of lista_cocinas
     */
    public void setLista_cocinas(ArrayListModel<String> lista_cocinas) {
        ArrayListModel<String> oldLista_cocinas = this.lista_cocinas;
        this.lista_cocinas.clear();
        this.lista_cocinas.addAll(lista_cocinas);
        firePropertyChange(PROP_LISTA_COCINAS, oldLista_cocinas, lista_cocinas);
    }

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
    public String getMesa_seleccionada() {
        return mesa_seleccionada;
    }

    /**
     * Set the value of mesa_seleccionada
     *
     * @param mesa_seleccionada new value of mesa_seleccionada
     */
    public void setMesa_seleccionada(String mesa_seleccionada) {
        String oldMesa_seleccionada = this.mesa_seleccionada;
        this.mesa_seleccionada = mesa_seleccionada;
        firePropertyChange(PROP_MESA_SELECCIONADA, oldMesa_seleccionada, mesa_seleccionada);
    }

    /**
     * Get the value of lista_mesas
     *
     * @return the value of lista_mesas
     */
    public ArrayListModel<String> getLista_mesas() {
        return lista_mesas;
    }

    /**
     * Set the value of lista_mesas
     *
     * @param lista_mesas new value of lista_mesas
     */
    public void setLista_mesas(ArrayListModel<String> lista_mesas) {
        ArrayListModel<String> oldLista_mesas = this.lista_mesas;
        this.lista_mesas.clear();
        this.lista_mesas.addAll(lista_mesas);
        firePropertyChange(PROP_LISTA_MESAS, oldLista_mesas, lista_mesas);
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
        if (propina_total == null || propina_total.isBlank()) {
            return;
        }
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
