/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.almacen.AlmacenManageController.CheckBoxType;
import com.jobits.pos.domain.TransaccionSimple;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.InsumoElaborado;
import com.jobits.pos.domain.models.TransaccionTransformacion;
import com.jobits.pos.ui.viewmodel.AbstractListViewModel;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Home
 */
public class FacturaViewModel extends AbstractListViewModel {

    //VALORES COMUNES
    private String numero_recibo;

    public static final String PROP_NUMERO_RECIBO = "numero_recibo";

    private Date fecha_factura = new Date();

    public static final String PROP_FECHA_FACTURA = "fecha_factura";

    private ArrayListModel<InsumoAlmacen> lista_insumos_disponibles = new ArrayListModel();

    public static final String PROP_LISTA_INSUMOS_DISPONIBLES = "lista_insumos_disponibles";

    private InsumoAlmacen insumo_selecionado;

    public static final String PROP_INSUMO_SELECIONADO = "insumo_selecionado";

    private float cantidad_entrada;

    public static final String PROP_CANTIDAD_ENTRADA = "cantidad_entrada";

    private float monto_entrada;

    public static final String PROP_MONTO_ENTRADA = "monto_entrada";

    private ArrayListModel<TransaccionSimple> lista_facturas = new ArrayListModel();

    public static final String PROP_LISTA_FACTURAS = "lista_facturas";

    private TransaccionSimple factura_entrada_seleccionada;

    public static final String PROP_FACTURA_ENTRADA_SELECCIONADA = "factura_entrada_seleccionada";

    private String unidad_medida_insumo = "<U/M>";

    public static final String PROP_UNIDAD_MEDIDA_INSUMO = "unidad_medida_insumo";

    //COMPONENTS VISIBILITY
    private boolean tabla_general_enabled;

    public static final String PROP_TABLA_GENERAL_ENABLED = "tabla_general_enabled";

    private boolean monto_enabled;

    public static final String PROP_MONTO_ENABLED = "monto_enabled";

    private boolean button_agregar_insumo_enabled;

    public static final String PROP_BUTTON_AGREGAR_INSUMO_ENABLED = "button_agregar_insumo_enabled";

    private boolean destino_enabled;

    public static final String PROP_DESTINO_ENABLED = "destino_enabled";

    private boolean razon_rebaja_enabled;

    public static final String PROP_RAZON_REBAJA_ENABLED = "razon_rebaja_enabled";

    private boolean tabla_insumos_transformar_enabled;

    public static final String PROP_TABLA_INSUMOS_TRANSFORMAR_ENABLED = "tabla_insumos_transformar_enabled";

    private boolean component_locked = true;

    public static final String PROP_COMPONENT_LOCKED = "component_locked";

    //OPERACIONES
    private CheckBoxType operacion_selected = CheckBoxType.ENTRADA;

    public static final String PROP_OPERACION_SELECTED = "operacion_selected";

    private ArrayListModel<CheckBoxType> lista_operaciones
            = new ArrayListModel<>(Arrays.asList(CheckBoxType.ENTRADA, CheckBoxType.REBAJA, CheckBoxType.SALIDA, CheckBoxType.TRASPASO, CheckBoxType.TRANSFORMAR));

    public static final String PROP_LISTA_OPERACIONES = "lista_operaciones";

    //MISC INPUTS
    private ArrayListModel lista_destino = new ArrayListModel();

    public static final String PROP_LISTA_DESTINO = "lista_destino";

    private Object destino_seleccionado;

    public static final String PROP_DESTINO_SELECCIONADO = "destino_seleccionado";

    private String causa_rebaja;

    public static final String PROP_CAUSA_REBAJA = "causa_rebaja";

    //ADDFROMPANEL TABLE VALUES
    private ArrayListModel<Insumo> lista_insumo_elaborado_disponible = new ArrayListModel();

    public static final String PROP_LISTA_INSUMO_ELABORADO_DISPONIBLE = "lista_insumo_elaborado_disponible";

    private Insumo insumo_elaborado_disponible_seleccionado;

    public static final String PROP_INSUMO_ELABORADO_DISPONIBLE_SELECCIONADO = "insumo_elaborado_disponible_seleccionado";

    private ArrayListModel<TransaccionTransformacion> lista_insumos_transformados_contenidos = new ArrayListModel();
    
    private TransaccionTransformacion insumo_transformado_contenido_seleccionado;

    public static final String PROP_INSUMO_TRANSFORMADO_CONTENIDO_SELECCIONADO = "insumo_transformado_contenido_seleccionado";

    /**
     * Get the value of insumo_transformado_contenido_seleccionado
     *
     * @return the value of insumo_transformado_contenido_seleccionado
     */
    public TransaccionTransformacion getInsumo_transformado_contenido_seleccionado() {
        return insumo_transformado_contenido_seleccionado;
    }

    /**
     * Set the value of insumo_transformado_contenido_seleccionado
     *
     * @param insumo_transformado_contenido_seleccionado new value of
     * insumo_transformado_contenido_seleccionado
     */
    public void setInsumo_transformado_contenido_seleccionado(TransaccionTransformacion insumo_transformado_contenido_seleccionado) {
        TransaccionTransformacion oldInsumo_transformado_contenido_seleccionado = this.insumo_transformado_contenido_seleccionado;
        this.insumo_transformado_contenido_seleccionado = insumo_transformado_contenido_seleccionado;
        firePropertyChange(PROP_INSUMO_TRANSFORMADO_CONTENIDO_SELECCIONADO, oldInsumo_transformado_contenido_seleccionado, insumo_transformado_contenido_seleccionado);
    }


    public static final String PROP_LISTA_INSUMOS_TRANSFORMADOS_CONTENIDOS = "lista_insumos_transformados_contenidos";
    
    

    /**
     * Get the value of lista_insumos_transformados_contenidos
     *
     * @return the value of lista_insumos_transformados_contenidos
     */
    public ArrayListModel<TransaccionTransformacion> getLista_insumos_transformados_contenidos() {
        return lista_insumos_transformados_contenidos;
    }

    /**
     * Set the value of lista_insumos_transformados_contenidos
     *
     * @param lista_insumos_transformados_contenidos new value of
     * lista_insumos_transformados_contenidos
     */
    public void setLista_insumos_transformados_contenidos(ArrayListModel<TransaccionTransformacion> lista_insumos_transformados_contenidos) {
        ArrayListModel<TransaccionTransformacion> oldLista_insumos_transformados_contenidos = this.lista_insumos_transformados_contenidos;
        this.lista_insumos_transformados_contenidos = lista_insumos_transformados_contenidos;
        firePropertyChange(PROP_LISTA_INSUMOS_TRANSFORMADOS_CONTENIDOS, oldLista_insumos_transformados_contenidos, lista_insumos_transformados_contenidos
        );
    }

    /**
     * Get the value of insumo_elaborado_disponible_seleccionado
     *
     * @return the value of insumo_elaborado_disponible_seleccionado
     */
    public Insumo getInsumo_elaborado_disponible_seleccionado() {
        return insumo_elaborado_disponible_seleccionado;
    }

    /**
     * Set the value of insumo_elaborado_disponible_seleccionado
     *
     * @param insumo_elaborado_disponible_seleccionado new value of
     * insumo_elaborado_disponible_seleccionado
     */
    public void setInsumo_elaborado_disponible_seleccionado(Insumo insumo_elaborado_disponible_seleccionado) {
        Insumo oldInsumo_elaborado_disponible_seleccionado = this.insumo_elaborado_disponible_seleccionado;
        this.insumo_elaborado_disponible_seleccionado = insumo_elaborado_disponible_seleccionado;
        firePropertyChange(PROP_INSUMO_ELABORADO_DISPONIBLE_SELECCIONADO, oldInsumo_elaborado_disponible_seleccionado, insumo_elaborado_disponible_seleccionado);
    }

    /**
     * Get the value of lista_insumo_elaborado_disponible
     *
     * @return the value of lista_insumo_elaborado_disponible
     */
    public ArrayListModel<Insumo> getLista_insumo_elaborado_disponible() {
        return lista_insumo_elaborado_disponible;
    }

    /**
     * Set the value of lista_insumo_elaborado_disponible
     *
     * @param lista_insumo_elaborado_disponible new value of
     * lista_insumo_elaborado_disponible
     */
    public void setLista_insumo_elaborado_disponible(ArrayListModel<Insumo> lista_insumo_elaborado_disponible) {
        ArrayListModel<Insumo> oldLista_insumo_elaborado_disponible = this.lista_insumo_elaborado_disponible;
        this.lista_insumo_elaborado_disponible = lista_insumo_elaborado_disponible;
        firePropertyChange(PROP_LISTA_INSUMO_ELABORADO_DISPONIBLE, oldLista_insumo_elaborado_disponible, lista_insumo_elaborado_disponible);
    }

    /**
     * Get the value of component_locked
     *
     * @return the value of component_locked
     */
    public boolean isComponent_locked() {
        return component_locked;
    }

    /**
     * Set the value of component_locked
     *
     * @param component_locked new value of component_locked
     */
    public void setComponent_locked(boolean component_locked) {
        boolean oldComponent_locked = this.component_locked;
        this.component_locked = component_locked;
        firePropertyChange(PROP_COMPONENT_LOCKED, oldComponent_locked, component_locked);
    }

    /**
     * Get the value of causa_rebaja
     *
     * @return the value of causa_rebaja
     */
    public String getCausa_rebaja() {
        return causa_rebaja;
    }

    /**
     * Set the value of causa_rebaja
     *
     * @param causa_rebaja new value of causa_rebaja
     */
    public void setCausa_rebaja(String causa_rebaja) {
        String oldCausa_rebaja = this.causa_rebaja;
        this.causa_rebaja = causa_rebaja;
        firePropertyChange(PROP_CAUSA_REBAJA, oldCausa_rebaja, causa_rebaja);
    }

    /**
     * Get the value of destino_seleccionado
     *
     * @return the value of destino_seleccionado
     */
    public Object getDestino_seleccionado() {
        return destino_seleccionado;
    }

    /**
     * Set the value of destino_seleccionado
     *
     * @param destino_seleccionado new value of destino_seleccionado
     */
    public void setDestino_seleccionado(Object destino_seleccionado) {
        Object oldDestino_seleccionado = this.destino_seleccionado;
        this.destino_seleccionado = destino_seleccionado;
        firePropertyChange(PROP_DESTINO_SELECCIONADO, oldDestino_seleccionado, destino_seleccionado);
    }

    /**
     * Get the value of lista_destino
     *
     * @return the value of lista_destino
     */
    public ArrayListModel getLista_destino() {
        return lista_destino;
    }

    /**
     * Set the value of lista_destino
     *
     * @param lista_destino new value of lista_destino
     */
    public void setLista_destino(ArrayListModel lista_destino) {
        ArrayListModel oldLista_destino = this.lista_destino;
        this.lista_destino = lista_destino;
        firePropertyChange(PROP_LISTA_DESTINO, oldLista_destino, lista_destino);
    }

    /**
     * Get the value of unidad_medida_insumo
     *
     * @return the value of unidad_medida_insumo
     */
    public String getUnidad_medida_insumo() {
        return unidad_medida_insumo;
    }

    /**
     * Set the value of unidad_medida_insumo
     *
     * @param unidad_medida_insumo new value of unidad_medida_insumo
     */
    public void setUnidad_medida_insumo(String unidad_medida_insumo) {
        String oldUnidad_medida_insumo = this.unidad_medida_insumo;
        this.unidad_medida_insumo = unidad_medida_insumo;
        firePropertyChange(PROP_UNIDAD_MEDIDA_INSUMO, oldUnidad_medida_insumo, unidad_medida_insumo);
    }

    /**
     * Get the value of lista_operaciones
     *
     * @return the value of lista_operaciones
     */
    public ArrayListModel getLista_operaciones() {
        return lista_operaciones;
    }

    /**
     * Set the value of lista_operaciones
     *
     * @param lista_operaciones new value of lista_operaciones
     */
    public void setLista_operaciones(ArrayListModel lista_operaciones) {
        ArrayListModel oldLista_operaciones = this.lista_operaciones;
        this.lista_operaciones = lista_operaciones;
        firePropertyChange(PROP_LISTA_OPERACIONES, oldLista_operaciones, lista_operaciones);
    }

    /**
     * Get the value of operacion_selected
     *
     * @return the value of operacion_selected
     */
    public CheckBoxType getOperacion_selected() {
        return operacion_selected;
    }

    /**
     * Set the value of operacion_selected
     *
     * @param operacion_selected new value of operacion_selected
     */
    public void setOperacion_selected(CheckBoxType operacion_selected) {
        CheckBoxType oldOperacion_selected = this.operacion_selected;
        this.operacion_selected = operacion_selected;
        firePropertyChange(PROP_OPERACION_SELECTED, oldOperacion_selected, operacion_selected);
    }

    /**
     * Get the value of tabla_insumos_transformar_enabled
     *
     * @return the value of tabla_insumos_transformar_enabled
     */
    public boolean isTabla_insumos_transformar_enabled() {
        return tabla_insumos_transformar_enabled;
    }

    /**
     * Set the value of tabla_insumos_transformar_enabled
     *
     * @param tabla_insumos_transformar_enabled new value of
     * tabla_insumos_transformar_enabled
     */
    public void setTabla_insumos_transformar_enabled(boolean tabla_insumos_transformar_enabled) {
        boolean oldTabla_insumos_transformar_enabled = this.tabla_insumos_transformar_enabled;
        this.tabla_insumos_transformar_enabled = tabla_insumos_transformar_enabled;
        firePropertyChange(PROP_TABLA_INSUMOS_TRANSFORMAR_ENABLED, oldTabla_insumos_transformar_enabled, tabla_insumos_transformar_enabled);
    }

    /**
     * Get the value of razon_rebaja_enabled
     *
     * @return the value of razon_rebaja_enabled
     */
    public boolean isRazon_rebaja_enabled() {
        return razon_rebaja_enabled;
    }

    /**
     * Set the value of razon_rebaja_enabled
     *
     * @param razon_rebaja_enabled new value of razon_rebaja_enabled
     */
    public void setRazon_rebaja_enabled(boolean razon_rebaja_enabled) {
        boolean oldRazon_rebaja_enabled = this.razon_rebaja_enabled;
        this.razon_rebaja_enabled = razon_rebaja_enabled;
        firePropertyChange(PROP_RAZON_REBAJA_ENABLED, oldRazon_rebaja_enabled, razon_rebaja_enabled);
    }

    /**
     * Get the value of destino_enabled
     *
     * @return the value of destino_enabled
     */
    public boolean isDestino_enabled() {
        return destino_enabled;
    }

    /**
     * Set the value of destino_enabled
     *
     * @param destino_enabled new value of destino_enabled
     */
    public void setDestino_enabled(boolean destino_enabled) {
        boolean oldDestino_enabled = this.destino_enabled;
        this.destino_enabled = destino_enabled;
        firePropertyChange(PROP_DESTINO_ENABLED, oldDestino_enabled, destino_enabled);
    }

    /**
     * Get the value of button_agregar_insumo_enabled
     *
     * @return the value of button_agregar_insumo_enabled
     */
    public boolean isButton_agregar_insumo_enabled() {
        return button_agregar_insumo_enabled;
    }

    /**
     * Set the value of button_agregar_insumo_enabled
     *
     * @param button_agregar_insumo_enabled new value of
     * button_agregar_insumo_enabled
     */
    public void setButton_agregar_insumo_enabled(boolean button_agregar_insumo_enabled) {
        boolean oldButton_agregar_insumo_enabled = this.button_agregar_insumo_enabled;
        this.button_agregar_insumo_enabled = button_agregar_insumo_enabled;
        firePropertyChange(PROP_BUTTON_AGREGAR_INSUMO_ENABLED, oldButton_agregar_insumo_enabled, button_agregar_insumo_enabled);
    }

    /**
     * Get the value of monto_enabled
     *
     * @return the value of monto_enabled
     */
    public boolean isMonto_enabled() {
        return monto_enabled;
    }

    /**
     * Set the value of monto_enabled
     *
     * @param monto_enabled new value of monto_enabled
     */
    public void setMonto_enabled(boolean monto_enabled) {
        boolean oldMonto_enabled = this.monto_enabled;
        this.monto_enabled = monto_enabled;
        firePropertyChange(PROP_MONTO_ENABLED, oldMonto_enabled, monto_enabled);
    }

    /**
     * Get the value of tabla_general_enabled
     *
     * @return the value of tabla_general_enabled
     */
    public boolean isTabla_general_enabled() {
        return tabla_general_enabled;
    }

    /**
     * Set the value of tabla_general_enabled
     *
     * @param tabla_general_enabled new value of tabla_general_enabled
     */
    public void setTabla_general_enabled(boolean tabla_general_enabled) {
        boolean oldTabla_general_enabled = this.tabla_general_enabled;
        this.tabla_general_enabled = tabla_general_enabled;
        firePropertyChange(PROP_TABLA_GENERAL_ENABLED, oldTabla_general_enabled, tabla_general_enabled);
    }

    /**
     * Get the value of factura_entrada_seleccionada
     *
     * @return the value of factura_entrada_seleccionada
     */
    public TransaccionSimple getFactura_entrada_seleccionada() {
        return factura_entrada_seleccionada;
    }

    /**
     * Set the value of factura_entrada_seleccionada
     *
     * @param factura_entrada_seleccionada new value of
     * factura_entrada_seleccionada
     */
    public void setFactura_entrada_seleccionada(TransaccionSimple factura_entrada_seleccionada) {
        TransaccionSimple oldFactura_entrada_seleccionada = this.factura_entrada_seleccionada;
        this.factura_entrada_seleccionada = factura_entrada_seleccionada;
        firePropertyChange(PROP_FACTURA_ENTRADA_SELECCIONADA, oldFactura_entrada_seleccionada, factura_entrada_seleccionada);
    }

    /**
     * Get the value of lista_facturas
     *
     * @return the value of lista_facturas
     */
    public ArrayListModel<TransaccionSimple> getLista_facturas() {
        return lista_facturas;
    }

    /**
     * Set the value of lista_facturas
     *
     * @param lista_factura_entrada new value of lista_facturas
     */
    public void setLista_factura_entrada(ArrayListModel<TransaccionSimple> lista_factura_entrada) {
        ArrayListModel<TransaccionSimple> oldLista_factura_entrada = this.lista_facturas;
        this.lista_facturas = lista_factura_entrada;
        firePropertyChange(PROP_LISTA_FACTURAS, oldLista_factura_entrada, lista_factura_entrada);
    }

    /**
     * Get the value of monto_entrada
     *
     * @return the value of monto_entrada
     */
    public float getMonto_entrada() {
        return monto_entrada;
    }

    /**
     * Set the value of monto_entrada
     *
     * @param monto_entrada new value of monto_entrada
     */
    public void setMonto_entrada(float monto_entrada) {
        float oldMonto_entrada = this.monto_entrada;
        this.monto_entrada = monto_entrada;
        firePropertyChange(PROP_MONTO_ENTRADA, oldMonto_entrada, monto_entrada);
    }

    /**
     * Get the value of cantidad_entrada
     *
     * @return the value of cantidad_entrada
     */
    public float getCantidad_entrada() {
        return cantidad_entrada;
    }

    /**
     * Set the value of cantidad_entrada
     *
     * @param cantidad_entrada new value of cantidad_entrada
     */
    public void setCantidad_entrada(float cantidad_entrada) {
        float oldCantidad_entrada = this.cantidad_entrada;
        this.cantidad_entrada = cantidad_entrada;
        firePropertyChange(PROP_CANTIDAD_ENTRADA, oldCantidad_entrada, cantidad_entrada);
    }

    /**
     * Get the value of insumo_selecionado
     *
     * @return the value of insumo_selecionado
     */
    public InsumoAlmacen getInsumo_selecionado() {
        return insumo_selecionado;
    }

    /**
     * Set the value of insumo_selecionado
     *
     * @param insumo_selecionado new value of insumo_selecionado
     */
    public void setInsumo_selecionado(InsumoAlmacen insumo_selecionado) {
        InsumoAlmacen oldInsumo_selecionado = this.insumo_selecionado;
        this.insumo_selecionado = insumo_selecionado;
        firePropertyChange(PROP_INSUMO_SELECIONADO, oldInsumo_selecionado, insumo_selecionado);
    }

    /**
     * Get the value of lista_insumos_disponibles
     *
     * @return the value of lista_insumos_disponibles
     */
    public ArrayListModel<InsumoAlmacen> getLista_insumos_disponibles() {
        return lista_insumos_disponibles;
    }

    /**
     * Set the value of lista_insumos_disponibles
     *
     * @param lista_insumos_disponibles new value of lista_insumos_disponibles
     */
    public void setLista_insumos_disponibles(ArrayListModel<InsumoAlmacen> lista_insumos_disponibles) {
        ArrayListModel<InsumoAlmacen> oldLista_insumos_disponibles = this.lista_insumos_disponibles;
        this.lista_insumos_disponibles = lista_insumos_disponibles;
        firePropertyChange(PROP_LISTA_INSUMOS_DISPONIBLES, oldLista_insumos_disponibles, lista_insumos_disponibles);
    }

    //
    /**
     * Get the value of fecha_factura
     *
     * @return the value of fecha_factura
     */
    public Date getFecha_factura() {
        return fecha_factura;
    }

    /**
     * Set the value of fecha_factura
     *
     * @param fecha_factura new value of fecha_factura
     */
    public void setFecha_factura(Date fecha_factura) {
        Date oldFecha_factura = this.fecha_factura;
        this.fecha_factura = fecha_factura;
        firePropertyChange(PROP_FECHA_FACTURA, oldFecha_factura, fecha_factura);
    }

    /**
     * Get the value of numero_recibo
     *
     * @return the value of numero_recibo
     */
    public String getNumero_recibo() {
        return numero_recibo;
    }

    /**
     * Set the value of numero_recibo
     *
     * @param numero_recibo new value of numero_recibo
     */
    public void setNumero_recibo(String numero_recibo) {
        String oldNumero_recibo = this.numero_recibo;
        this.numero_recibo = numero_recibo;
        firePropertyChange(PROP_NUMERO_RECIBO, oldNumero_recibo, numero_recibo);
    }

}
