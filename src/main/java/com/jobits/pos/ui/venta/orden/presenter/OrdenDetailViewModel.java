/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class OrdenDetailViewModel extends AbstractViewModel {

    private List<ProductoVenta> lista_general_productos_venta = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTOS_VENTA = "lista_general_productos_venta";

    private ProductoVenta producto_venta_seleccionado = null;

    public static final String PROP_PRODUCTO_VENTA_SELECCIONADO = "producto_venta_seleccionado";

    private List<ProductovOrden> lista_producto_orden = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTO_ORDEN = "lista_producto_orden";

    private ProductovOrden producto_orden_seleccionado = null;

    public static final String PROP_PRODUCTO_ORDEN_SELECCIONADO = "producto_orden_seleccionado";

    private List<Seccion> lista_secciones = new ArrayListModel<>();

    public static final String PROP_LISTA_SECCIONES = "lista_secciones";

    private Seccion seccion_seleccionada = null;

    public static final String PROP_SECCION_SELECCIONADA = "seccion_seleccionada";

    private List<ProductoVenta> lista_producto_venta_seccion = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTO_VENTA_SECCION = "lista_producto_venta_seccion";

    private String fecha_orden = "dd/mm/yyyy";

    public static final String PROP_FECHA_ORDEN = "fecha_orden";

    private String hora_pedido = "xx:xx AM/PM";

    public static final String PROP_HORA_PEDIDO = "hora_pedido";

    private String usuario = "<Usuario invalido>";

    public static final String PROP_USUARIO = "usuario";

    private String id_orden = "O-";

    public static final String PROP_ID_ORDEN = "id_orden";

    private String total_orden = "0.00 ";

    public static final String PROP_TOTAL_ORDEN = "total_orden";

    private String mesa_pedido = "M-";

    public static final String PROP_MESA_PEDIDO = "mesa_pedido";

    private float porciento_servicio = 0;

    public static final String PROP_PORCIENTO_SERVICIO = "porciento_servicio";

    private boolean es_autorizo = true;

    public static final String PROP_ES_AUTORIZO = "es_autorizo";

    private boolean tiene_porciento = false;

    public static final String PROP_TIENE_PORCIENTO = "tiene_porciento";

    public static final String PROP_ORDEN_STATUS_UPDATE = "status_update";

    private boolean orden_terminada = false;

    public static final String PROP_ORDEN_TERMINADA = "orden_terminada";

    private ImageIcon icono_porciento;

    public static final String PROP_ICONO_PORCIENTO = "icono_porciento";

    private ArrayListModel<Cliente> lista_clientes = new ArrayListModel<>();

    public static final String PROP_LISTA_CLIENTES = "lista_clientes";

    private Cliente cliente_seleccionado;

    public static final String PROP_CLIENTE_SELECCIONADO = "cliente_seleccionado";

    private boolean envio_cocina;

    public static final String PROP_ENVIO_COCINA = "envio_cocina";

    private boolean modo_agrego_activado = false;

    public static final String PROP_MODO_AGREGO_ACTIVADO = "modo_agrego_activado";

    private boolean botton_agrego_enabled;

    public static final String PROP_BOTTON_AGREGO_ENABLED = "botton_agrego_enabled";

    private boolean suport_panel_visible = false;

    public static final String PROP_SUPORT_PANEL_VISIBLE = "suport_panel_visible";

    /**
     * Get the value of suport_panel_visible
     *
     * @return the value of suport_panel_visible
     */
    public boolean isSuport_panel_visible() {
        return suport_panel_visible;
    }

    /**
     * Set the value of suport_panel_visible
     *
     * @param suport_panel_visible new value of suport_panel_visible
     */
    public void setSuport_panel_visible(boolean suport_panel_visible) {
        boolean oldSuport_panel_visible = this.suport_panel_visible;
        this.suport_panel_visible = suport_panel_visible;
        firePropertyChange(PROP_SUPORT_PANEL_VISIBLE, oldSuport_panel_visible, suport_panel_visible);
    }

    /**
     * Get the value of botton_agrego_enabled
     *
     * @return the value of botton_agrego_enabled
     */
    public boolean isBotton_agrego_enabled() {
        return botton_agrego_enabled;
    }

    /**
     * Set the value of botton_agrego_enabled
     *
     * @param botton_agrego_enabled new value of botton_agrego_enabled
     */
    public void setBotton_agrego_enabled(boolean botton_agrego_enabled) {
        boolean oldBotton_agrego_enabled = this.botton_agrego_enabled;
        this.botton_agrego_enabled = botton_agrego_enabled;
        firePropertyChange(PROP_BOTTON_AGREGO_ENABLED, oldBotton_agrego_enabled, botton_agrego_enabled);
    }

    /**
     * Get the value of modo_agrego_activado
     *
     * @return the value of modo_agrego_activado
     */
    public boolean isModo_agrego_activado() {
        return modo_agrego_activado;
    }

    /**
     * Set the value of modo_agrego_activado
     *
     * @param modo_agrego_activado new value of modo_agrego_activado
     */
    public void setModo_agrego_activado(boolean modo_agrego_activado) {
        boolean oldModo_agrego_activado = this.modo_agrego_activado;
        this.modo_agrego_activado = modo_agrego_activado;
        firePropertyChange(PROP_MODO_AGREGO_ACTIVADO, oldModo_agrego_activado, modo_agrego_activado);
    }

    /**
     * Get the value of envio_cocina
     *
     * @return the value of envio_cocina
     */
    public boolean isEnvio_cocina() {
        return envio_cocina;
    }

    /**
     * Set the value of envio_cocina
     *
     * @param envio_cocina new value of envio_cocina
     */
    public void setEnvio_cocina(boolean envio_cocina) {
        boolean oldEnvio_cocina = this.envio_cocina;
        this.envio_cocina = envio_cocina;
        firePropertyChange(PROP_ENVIO_COCINA, oldEnvio_cocina, envio_cocina);
    }

    /**
     * Get the value of cliente_seleccionado
     *
     * @return the value of cliente_seleccionado
     */
    public Cliente getCliente_seleccionado() {
        return cliente_seleccionado;
    }

    /**
     * Set the value of cliente_seleccionado
     *
     * @param cliente_seleccionado new value of cliente_seleccionado
     */
    public void setCliente_seleccionado(Cliente cliente_seleccionado) {
        Cliente oldCliente_seleccionado = this.cliente_seleccionado;
        this.cliente_seleccionado = cliente_seleccionado;
        firePropertyChange(PROP_CLIENTE_SELECCIONADO, oldCliente_seleccionado, cliente_seleccionado);
    }

    /**
     * Get the value of lista_clientes
     *
     * @return the value of lista_clientes
     */
    public ArrayListModel<Cliente> getLista_clientes() {
        return lista_clientes;
    }

    /**
     * Set the value of lista_clientes
     *
     * @param lista_clientes new value of lista_clientes
     */
    public void setLista_clientes(ArrayListModel<Cliente> lista_clientes) {
        ArrayListModel<Cliente> oldLista_clientes = this.lista_clientes;
        this.lista_clientes = lista_clientes;
        firePropertyChange(PROP_LISTA_CLIENTES, oldLista_clientes, lista_clientes);
    }

    /**
     * Get the value of icono_porciento
     *
     * @return the value of icono_porciento
     */
    public ImageIcon getIcono_porciento() {
        return icono_porciento;
    }

    /**
     * Set the value of icono_porciento
     *
     * @param icono_porciento new value of icono_porciento
     */
    public void setIcono_porciento(ImageIcon icono_porciento) {
        ImageIcon oldIcono_porciento = this.icono_porciento;
        this.icono_porciento = icono_porciento;
        firePropertyChange(PROP_ICONO_PORCIENTO, oldIcono_porciento, icono_porciento);
    }

    /**
     * Get the value of orden_terminada
     *
     * @return the value of orden_terminada
     */
    public boolean isOrden_terminada() {
        return orden_terminada;
    }

    /**
     * Set the value of orden_terminada
     *
     * @param orden_terminada new value of orden_terminada
     */
    public void setOrden_terminada(boolean orden_terminada) {
        boolean oldOrden_terminada = this.orden_terminada;
        this.orden_terminada = orden_terminada;
        firePropertyChange(PROP_ORDEN_TERMINADA, oldOrden_terminada, orden_terminada);
    }

    /**
     * Get the value of tiene_porciento
     *
     * @return the value of tiene_porciento
     */
    public boolean isTiene_porciento() {
        return tiene_porciento;
    }

    /**
     * Set the value of tiene_porciento
     *
     * @param tiene_porciento new value of tiene_porciento
     */
    private void setTiene_porciento(boolean tiene_porciento) {
        boolean oldTiene_porciento = this.tiene_porciento;
        this.tiene_porciento = tiene_porciento;
        firePropertyChange(PROP_TIENE_PORCIENTO, oldTiene_porciento, tiene_porciento);
    }

    /**
     * Get the value of es_autorizo
     *
     * @return the value of es_autorizo
     */
    public boolean isEs_autorizo() {
        return es_autorizo;
    }

    /**
     * Set the value of es_autorizo
     *
     * @param es_autorizo new value of es_autorizo
     */
    public void setEs_autorizo(boolean es_autorizo) {
        boolean oldEs_autorizo = this.es_autorizo;
        this.es_autorizo = es_autorizo;
        firePropertyChange(PROP_ES_AUTORIZO, oldEs_autorizo, es_autorizo);
        firePropertyChange(PROP_ORDEN_STATUS_UPDATE, oldEs_autorizo, es_autorizo);
    }

    /**
     * Get the value of porciento_servicio
     *
     * @return the value of porciento_servicio
     */
    public float getPorciento_servicio() {
        return porciento_servicio;
    }

    /**
     * Set the value of porciento_servicio
     *
     * @param porciento_servicio new value of porciento_servicio
     */
    public void setPorciento_servicio(float porciento_servicio) {
        float oldPorciento_servicio = this.porciento_servicio;
        this.porciento_servicio = porciento_servicio;
        setTiene_porciento(this.porciento_servicio != 0);
        firePropertyChange(PROP_PORCIENTO_SERVICIO, oldPorciento_servicio, porciento_servicio);
        firePropertyChange(PROP_ORDEN_STATUS_UPDATE, oldPorciento_servicio, porciento_servicio);
    }

    /**
     * Get the value of mesa_pedido
     *
     * @return the value of mesa_pedido
     */
    public String getMesa_pedido() {
        return mesa_pedido;
    }

    /**
     * Set the value of mesa_pedido
     *
     * @param mesa_pedido new value of mesa_pedido
     */
    public void setMesa_pedido(String mesa_pedido) {
        String oldMesa_pedido = this.mesa_pedido;
        this.mesa_pedido = mesa_pedido;
        firePropertyChange(PROP_MESA_PEDIDO, oldMesa_pedido, mesa_pedido);
    }

    /**
     * Get the value of total_orden
     *
     * @return the value of total_orden
     */
    public String getTotal_orden() {
        return total_orden;
    }

    /**
     * Set the value of total_orden
     *
     * @param total_orden new value of total_orden
     */
    public void setTotal_orden(String total_orden) {
        String oldTotal_orden = this.total_orden;
        this.total_orden = total_orden;
        firePropertyChange(PROP_TOTAL_ORDEN, oldTotal_orden, total_orden);
        firePropertyChange(PROP_ORDEN_STATUS_UPDATE, oldTotal_orden, total_orden);

    }

    /**
     * Get the value of id_orden
     *
     * @return the value of id_orden
     */
    public String getId_orden() {
        return id_orden;
    }

    /**
     * Set the value of id_orden
     *
     * @param id_orden new value of id_orden
     */
    public void setId_orden(String id_orden) {
        String oldId_orden = this.id_orden;
        this.id_orden = id_orden;
        firePropertyChange(PROP_ID_ORDEN, oldId_orden, id_orden);
    }

    /**
     * Get the value of usuario
     *
     * @return the value of usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Set the value of usuario
     *
     * @param usuario new value of usuario
     */
    public void setUsuario(String usuario) {
        String oldUsuario = this.usuario;
        this.usuario = usuario;
        firePropertyChange(PROP_USUARIO, oldUsuario, usuario);
    }

    /**
     * Get the value of hora_pedido
     *
     * @return the value of hora_pedido
     */
    public String getHora_pedido() {
        return hora_pedido;
    }

    /**
     * Set the value of hora_pedido
     *
     * @param hora_pedido new value of hora_pedido
     */
    public void setHora_pedido(String hora_pedido) {
        String oldHora_pedido = this.hora_pedido;
        this.hora_pedido = hora_pedido;
        firePropertyChange(PROP_HORA_PEDIDO, oldHora_pedido, hora_pedido);
    }

    /**
     * Get the value of fecha_orden
     *
     * @return the value of fecha_orden
     */
    public String getFecha_orden() {
        return fecha_orden;
    }

    /**
     * Set the value of fecha_orden
     *
     * @param fecha_orden new value of fecha_orden
     */
    public void setFecha_orden(String fecha_orden) {
        String oldFecha_orden = this.fecha_orden;
        this.fecha_orden = fecha_orden;
        firePropertyChange(PROP_FECHA_ORDEN, oldFecha_orden, fecha_orden);
    }

    /**
     * Get the value of lista_producto_venta_seccion
     *
     * @return the value of lista_producto_venta_seccion
     */
    public List<ProductoVenta> getLista_producto_venta_seccion() {
        return lista_producto_venta_seccion;
    }

    /**
     * Set the value of lista_producto_venta_seccion
     *
     * @param lista_producto_venta_seccion new value of
     * lista_producto_venta_seccion
     */
    public void setLista_producto_venta_seccion(List<ProductoVenta> lista_producto_venta_seccion) {
        List<ProductoVenta> oldLista_producto_venta_seccion = this.lista_producto_venta_seccion;
        this.lista_producto_venta_seccion.clear();
        this.lista_producto_venta_seccion.addAll(lista_producto_venta_seccion);
        firePropertyChange(PROP_LISTA_PRODUCTO_VENTA_SECCION, oldLista_producto_venta_seccion, lista_producto_venta_seccion);
    }

    /**
     * Get the value of seccion_seleccionada
     *
     * @return the value of seccion_seleccionada
     */
    public Seccion getSeccion_seleccionada() {
        return seccion_seleccionada;
    }

    /**
     * Set the value of seccion_seleccionada
     *
     * @param seccion_seleccionada new value of seccion_seleccionada
     */
    public void setSeccion_seleccionada(Seccion seccion_seleccionada) {
        Seccion oldSeccion_seleccionada = this.seccion_seleccionada;
        this.seccion_seleccionada = seccion_seleccionada;
        firePropertyChange(PROP_SECCION_SELECCIONADA, oldSeccion_seleccionada, seccion_seleccionada);
        setLista_producto_venta_seccion(this.seccion_seleccionada.getProductoVentaList());
    }

    /**
     * Get the value of lista_secciones
     *
     * @return the value of lista_secciones
     */
    public List<Seccion> getLista_secciones() {
        return lista_secciones;
    }

    /**
     * Set the value of lista_secciones
     *
     * @param lista_secciones new value of lista_secciones
     */
    public void setLista_secciones(List<Seccion> lista_secciones) {
        List<Seccion> oldLista_secciones = this.lista_secciones;
        this.lista_secciones.clear();
        this.lista_secciones.addAll(lista_secciones);
        firePropertyChange(PROP_LISTA_SECCIONES, oldLista_secciones, lista_secciones);
    }

    /**
     * Get the value of producto_orden_seleccionado
     *
     * @return the value of producto_orden_seleccionado
     */
    public ProductovOrden getProducto_orden_seleccionado() {
        return producto_orden_seleccionado;
    }

    /**
     * Set the value of producto_orden_seleccionado
     *
     * @param producto_orden_seleccionado new value of
     * producto_orden_seleccionado
     */
    public void setProducto_orden_seleccionado(ProductovOrden producto_orden_seleccionado) {
        ProductovOrden oldProducto_orden_seleccionado = this.producto_orden_seleccionado;
        this.producto_orden_seleccionado = producto_orden_seleccionado;
        firePropertyChange(PROP_PRODUCTO_ORDEN_SELECCIONADO, oldProducto_orden_seleccionado, producto_orden_seleccionado);
    }

    /**
     * Get the value of lista_producto_orden
     *
     * @return the value of lista_producto_orden
     */
    public List<ProductovOrden> getLista_producto_orden() {
        return lista_producto_orden;
    }

    /**
     * Set the value of lista_producto_orden
     *
     * @param lista_producto_orden new value of lista_producto_orden
     */
    public void setLista_producto_orden(List<ProductovOrden> lista_producto_orden) {
        List<ProductovOrden> oldLista_producto_orden = this.lista_producto_orden;
        this.lista_producto_orden.clear();
        this.lista_producto_orden.addAll(lista_producto_orden);
        firePropertyChange(PROP_LISTA_PRODUCTO_ORDEN, oldLista_producto_orden, lista_producto_orden);
    }

    /**
     * Get the value of producto_venta_seleccionado
     *
     * @return the value of producto_venta_seleccionado
     */
    public ProductoVenta getProducto_venta_seleccionado() {
        return producto_venta_seleccionado;
    }

    /**
     * Set the value of producto_venta_seleccionado
     *
     * @param producto_venta_seleccionado new value of
     * producto_venta_seleccionado
     */
    public void setProducto_venta_seleccionado(ProductoVenta producto_venta_seleccionado) {
        ProductoVenta oldProducto_venta_seleccionado = this.producto_venta_seleccionado;
        this.producto_venta_seleccionado = producto_venta_seleccionado;
        firePropertyChange(PROP_PRODUCTO_VENTA_SELECCIONADO, oldProducto_venta_seleccionado, producto_venta_seleccionado);
    }

    /**
     * Get the value of lista_general_productos_venta
     *
     * @return the value of lista_general_productos_venta
     */
    public List<ProductoVenta> getLista_general_productos_venta() {
        return lista_general_productos_venta;
    }

    /**
     * Set the value of lista_general_productos_venta
     *
     * @param lista_general_productos_venta new value of
     * lista_general_productos_venta
     */
    public void setLista_general_productos_venta(List<ProductoVenta> lista_general_productos_venta) {
        List<ProductoVenta> oldLista_productos_venta = this.lista_general_productos_venta;
        this.lista_general_productos_venta.clear();
        this.lista_general_productos_venta.addAll(lista_general_productos_venta);
        firePropertyChange(PROP_LISTA_PRODUCTOS_VENTA, oldLista_productos_venta, lista_general_productos_venta);
    }

}
