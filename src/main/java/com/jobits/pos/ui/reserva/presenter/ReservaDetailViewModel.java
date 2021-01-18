/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.ProductovOrden;
import com.jobits.pos.reserva.core.domain.Categoria;
import com.jobits.pos.reserva.core.domain.Cliente;
import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Home
 */
public class ReservaDetailViewModel extends AbstractViewModel {

    private String nombre_reserva;

    public static final String PROP_NOMBRE_RESERVA = "nombre_reserva";

    //FECHA
    private Date fecha = new Date();

    private int duracion = 0;

    public static final String PROP_DURACION = "duracion";

    private LocalTime hora_seleccionada = LocalTime.MIDNIGHT;

    public static final String PROP_HORA_SELECCIONADA = "hora_seleccionada";

    private ArrayListModel<LocalTime> lista_horas = new ArrayListModel<>();

    public static final String PROP_LISTA_HORAS = "lista_horas";

    private LocalTime minuto_seleccionado = LocalTime.MIDNIGHT;

    public static final String PROP_MINUTO_SELECCIONADO = "minuto_seleccionado";

    private ArrayListModel<LocalTime> lista_minutos = new ArrayListModel<>();

    public static final String PROP_LISTA_MINUTOS = "lista_minutos";

    private LocalTime am_pm_seleccionado = LocalTime.MIDNIGHT;

    public static final String PROP_AM_PM_SELECCIONADO = "am_pm_seleccionado";

    private ArrayListModel<LocalTime> lista_am_pm = new ArrayListModel<>(Arrays.asList(LocalTime.MIDNIGHT, LocalTime.NOON));

    public static final String PROP_LISTA_AM_PM = "lista_am_pm";

    //CATEGORIA
    private Categoria categoria_seleccionada;

    public static final String PROP_CATEGORIA_SELECCIONADA = "categoria_seleccionada";

    private ArrayListModel<Categoria> lista_categorias = new ArrayListModel<>();

    public static final String PROP_LISTA_CATEGORIAS = "lista_categorias";

    //LISTA DE PRODUCTOS RESERVADOS
    private ArrayListModel<ProductovOrden> lista_producto = new ArrayListModel<>();

    public static final String PROP_LISTA_PRODUCTO = "lista_producto";

    private ProductovOrden producto_seleccionado = null;

    public static final String PROP_PRODUCTO_SELECCIONADO = "producto_seleccionado";

    //MODO AGREGO
    private boolean modo_agrego = false;

    public static final String PROP_MODO_AGREGO = "modo_agrego";

    private boolean botton_agrego_enabled = false;

    public static final String PROP_BOTTON_AGREGO_ENABLED = "botton_agrego_enabled";

    //CLIENTE
    private Cliente cliente;

    public static final String PROP_CLIENTE = "cliente";

    private ArrayListModel<Cliente> lista_clientes = new ArrayListModel<>();

    public static final String PROP_LISTA_CLIENTES = "lista_clientes";

    private String nombre_cliente;

    public static final String PROP_NOMBRE_CLIENTE = "nombre_cliente";

    private String apellido_cliente;

    public static final String PROP_APELLIDO_CLIENTE = "apellido_cliente";

    private String telefono_cliente;

    public static final String PROP_TELEFONO_CLIENTE = "telefono_cliente";

    //UBICACIONES
    private Ubicacion ubicacion_seleccionada;

    public static final String PROP_UBICACION_SELECCIONADA = "ubicacion_seleccionada";

    private ArrayListModel<Ubicacion> lista_ubicaciones = new ArrayListModel<>();

    public static final String PROP_LISTA_UBICACIONES = "lista_ubicaciones";

    //OTROS
    private boolean componentes_enabled;

    public static final String PROP_COMPONENTES_ENABLED = "componentes_enabled";

    private boolean show_productos = false;

    public static final String PROP_SHOW_PRODUCTOS = "show_productos";

    /**
     * Get the value of lista_categorias
     *
     * @return the value of lista_categorias
     */
    public ArrayListModel<Categoria> getLista_categorias() {
        return lista_categorias;
    }

    /**
     * Set the value of lista_categorias
     *
     * @param lista_categorias new value of lista_categorias
     */
    public void setLista_categorias(ArrayListModel<Categoria> lista_categorias) {
        ArrayListModel<Categoria> oldLista_categorias = this.lista_categorias;
        this.lista_categorias = lista_categorias;
        firePropertyChange(PROP_LISTA_CATEGORIAS, oldLista_categorias, lista_categorias);
    }

    /**
     * Get the value of categoria_seleccionada
     *
     * @return the value of categoria_seleccionada
     */
    public Categoria getCategoria_seleccionada() {
        return categoria_seleccionada;
    }

    /**
     * Set the value of categoria_seleccionada
     *
     * @param categoria_seleccionada new value of categoria_seleccionada
     */
    public void setCategoria_seleccionada(Categoria categoria_seleccionada) {
        Categoria oldCategoria_seleccionada = this.categoria_seleccionada;
        this.categoria_seleccionada = categoria_seleccionada;
        firePropertyChange(PROP_CATEGORIA_SELECCIONADA, oldCategoria_seleccionada, categoria_seleccionada);
    }

    /**
     * Get the value of nombre_reserva
     *
     * @return the value of nombre_reserva
     */
    public String getNombre_reserva() {
        return nombre_reserva;
    }

    /**
     * Set the value of nombre_reserva
     *
     * @param nombre_reserva new value of nombre_reserva
     */
    public void setNombre_reserva(String nombre_reserva) {
        String oldNombre_reserva = this.nombre_reserva;
        this.nombre_reserva = nombre_reserva;
        firePropertyChange(PROP_NOMBRE_RESERVA, oldNombre_reserva, nombre_reserva);
    }

    /**
     * Get the value of lista_ubicaciones
     *
     * @return the value of lista_ubicaciones
     */
    public ArrayListModel<Ubicacion> getLista_ubicaciones() {
        return lista_ubicaciones;
    }

    /**
     * Set the value of lista_ubicaciones
     *
     * @param lista_ubicaciones new value of lista_ubicaciones
     */
    public void setLista_ubicaciones(ArrayListModel<Ubicacion> lista_ubicaciones) {
        ArrayListModel<Ubicacion> oldLista_ubicaciones = this.lista_ubicaciones;
        this.lista_ubicaciones = lista_ubicaciones;
        firePropertyChange(PROP_LISTA_UBICACIONES, oldLista_ubicaciones, lista_ubicaciones);
    }

    /**
     * Get the value of ubicacion_seleccionada
     *
     * @return the value of ubicacion_seleccionada
     */
    public Ubicacion getUbicacion_seleccionada() {
        return ubicacion_seleccionada;
    }

    /**
     * Set the value of ubicacion_seleccionada
     *
     * @param ubicacion_seleccionada new value of ubicacion_seleccionada
     */
    public void setUbicacion_seleccionada(Ubicacion ubicacion_seleccionada) {
        Ubicacion oldUbicacion_seleccionada = this.ubicacion_seleccionada;
        this.ubicacion_seleccionada = ubicacion_seleccionada;
        firePropertyChange(PROP_UBICACION_SELECCIONADA, oldUbicacion_seleccionada, ubicacion_seleccionada);
    }

    /**
     * Get the value of lista_am_pm
     *
     * @return the value of lista_am_pm
     */
    public ArrayListModel<LocalTime> getLista_am_pm() {
        return lista_am_pm;
    }

    /**
     * Set the value of lista_am_pm
     *
     * @param lista_am_pm new value of lista_am_pm
     */
    public void setLista_am_pm(ArrayListModel<LocalTime> lista_am_pm) {
        ArrayListModel<LocalTime> oldLista_am_pm = this.lista_am_pm;
        this.lista_am_pm = lista_am_pm;
        firePropertyChange(PROP_LISTA_AM_PM, oldLista_am_pm, lista_am_pm);
    }

    /**
     * Get the value of am_pm_seleccionado
     *
     * @return the value of am_pm_seleccionado
     */
    public LocalTime getAm_pm_seleccionado() {
        return am_pm_seleccionado;
    }

    /**
     * Set the value of am_pm_seleccionado
     *
     * @param am_pm_seleccionado new value of am_pm_seleccionado
     */
    public void setAm_pm_seleccionado(LocalTime am_pm_seleccionado) {
        LocalTime oldAm_pm_seleccionado = this.am_pm_seleccionado;
        this.am_pm_seleccionado = am_pm_seleccionado;
        firePropertyChange(PROP_AM_PM_SELECCIONADO, oldAm_pm_seleccionado, am_pm_seleccionado);
    }

    /**
     * Get the value of lista_minutos
     *
     * @return the value of lista_minutos
     */
    public ArrayListModel<LocalTime> getLista_minutos() {
        return lista_minutos;
    }

    /**
     * Set the value of lista_minutos
     *
     * @param lista_minutos new value of lista_minutos
     */
    public void setLista_minutos(ArrayListModel<LocalTime> lista_minutos) {
        ArrayListModel<LocalTime> oldLista_minutos = this.lista_minutos;
        this.lista_minutos = lista_minutos;
        firePropertyChange(PROP_LISTA_MINUTOS, oldLista_minutos, lista_minutos);
    }

    /**
     * Get the value of minuto_seleccionado
     *
     * @return the value of minuto_seleccionado
     */
    public LocalTime getMinuto_seleccionado() {
        return minuto_seleccionado;
    }

    /**
     * Set the value of minuto_seleccionado
     *
     * @param minuto_seleccionado new value of minuto_seleccionado
     */
    public void setMinuto_seleccionado(LocalTime minuto_seleccionado) {
        LocalTime oldMinuto_seleccionado = this.minuto_seleccionado;
        this.minuto_seleccionado = minuto_seleccionado;
        firePropertyChange(PROP_MINUTO_SELECCIONADO, oldMinuto_seleccionado, minuto_seleccionado);
    }

    /**
     * Get the value of lista_horas
     *
     * @return the value of lista_horas
     */
    public ArrayListModel<LocalTime> getLista_horas() {
        return lista_horas;
    }

    /**
     * Set the value of lista_horas
     *
     * @param lista_horas new value of lista_horas
     */
    public void setLista_horas(ArrayListModel<LocalTime> lista_horas) {
        ArrayListModel<LocalTime> oldLista_horas = this.lista_horas;
        this.lista_horas = lista_horas;
        firePropertyChange(PROP_LISTA_HORAS, oldLista_horas, lista_horas);
    }

    /**
     * Get the value of hora_seleccionada
     *
     * @return the value of hora_seleccionada
     */
    public LocalTime getHora_seleccionada() {
        return hora_seleccionada;
    }

    /**
     * Set the value of hora_seleccionada
     *
     * @param hora_seleccionada new value of hora_seleccionada
     */
    public void setHora_seleccionada(LocalTime hora_seleccionada) {
        LocalTime oldHora_seleccionada = this.hora_seleccionada;
        this.hora_seleccionada = hora_seleccionada;
        firePropertyChange(PROP_HORA_SELECCIONADA, oldHora_seleccionada, hora_seleccionada);
    }

    /**
     * Get the value of telefono_cliente
     *
     * @return the value of telefono_cliente
     */
    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    /**
     * Set the value of telefono_cliente
     *
     * @param telefono_cliente new value of telefono_cliente
     */
    public void setTelefono_cliente(String telefono_cliente) {
        String oldTelefono_cliente = this.telefono_cliente;
        this.telefono_cliente = telefono_cliente;
        firePropertyChange(PROP_TELEFONO_CLIENTE, oldTelefono_cliente, telefono_cliente);
    }

    /**
     * Get the value of apellido_cliente
     *
     * @return the value of apellido_cliente
     */
    public String getApellido_cliente() {
        return apellido_cliente;
    }

    /**
     * Set the value of apellido_cliente
     *
     * @param apellido_cliente new value of apellido_cliente
     */
    public void setApellido_cliente(String apellido_cliente) {
        String oldApellido_cliente = this.apellido_cliente;
        this.apellido_cliente = apellido_cliente;
        firePropertyChange(PROP_APELLIDO_CLIENTE, oldApellido_cliente, apellido_cliente);
    }

    /**
     * Get the value of nombre_cliente
     *
     * @return the value of nombre_cliente
     */
    public String getNombre_cliente() {
        return nombre_cliente;
    }

    /**
     * Set the value of nombre_cliente
     *
     * @param nombre_cliente new value of nombre_cliente
     */
    public void setNombre_cliente(String nombre_cliente) {
        String oldNombre_cliente = this.nombre_cliente;
        this.nombre_cliente = nombre_cliente;
        firePropertyChange(PROP_NOMBRE_CLIENTE, oldNombre_cliente, nombre_cliente);
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
     * Get the value of modo_agrego
     *
     * @return the value of modo_agrego
     */
    public boolean isModo_agrego() {
        return modo_agrego;
    }

    /**
     * Set the value of modo_agrego
     *
     * @param modo_agrego new value of modo_agrego
     */
    public void setModo_agrego(boolean modo_agrego) {
        boolean oldModo_agrego = this.modo_agrego;
        this.modo_agrego = modo_agrego;
        firePropertyChange(PROP_MODO_AGREGO, oldModo_agrego, modo_agrego);
    }

    /**
     * Get the value of producto_seleccionado
     *
     * @return the value of producto_seleccionado
     */
    public ProductovOrden getProducto_seleccionado() {
        return producto_seleccionado;
    }

    /**
     * Set the value of producto_seleccionado
     *
     * @param producto_seleccionado new value of producto_seleccionado
     */
    public void setProducto_seleccionado(ProductovOrden producto_seleccionado) {
        ProductovOrden oldProducto_seleccionado = this.producto_seleccionado;
        this.producto_seleccionado = producto_seleccionado;
        firePropertyChange(PROP_PRODUCTO_SELECCIONADO, oldProducto_seleccionado, producto_seleccionado);
    }

    /**
     * Get the value of lista_producto
     *
     * @return the value of lista_producto
     */
    public ArrayListModel<ProductovOrden> getLista_producto() {
        return lista_producto;
    }

    /**
     * Set the value of lista_producto
     *
     * @param lista_producto new value of lista_producto
     */
    public void setLista_producto(ArrayListModel<ProductovOrden> lista_producto) {
        ArrayListModel<ProductovOrden> oldLista_producto = this.lista_producto;
        this.lista_producto.clear();
        this.lista_producto.addAll(lista_producto);
        firePropertyChange(PROP_LISTA_PRODUCTO, oldLista_producto, lista_producto);
    }

    /**
     * Get the value of cliente
     *
     * @return the value of cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Set the value of cliente
     *
     * @param cliente new value of cliente
     */
    public void setCliente(Cliente cliente) {
        Cliente oldCliente = this.cliente;
        this.cliente = cliente;
        firePropertyChange(PROP_CLIENTE, oldCliente, cliente);
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
        this.lista_clientes.clear();
        this.lista_clientes.addAll(lista_clientes);
        firePropertyChange(PROP_LISTA_CLIENTES, oldLista_clientes, lista_clientes);
    }

    /**
     * Get the value of duracion
     *
     * @return the value of duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Set the value of duracion
     *
     * @param duracion
     */
    public void setDuracion(int duracion) {
        int oldDuracion = this.duracion;
        this.duracion = duracion;
        firePropertyChange(PROP_DURACION, oldDuracion, duracion);
    }

    public static final String PROP_FECHA = "fecha";

    /**
     * Get the value of fecha
     *
     * @return the value of fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Set the value of fecha
     *
     * @param fecha new value of fecha
     */
    public void setFecha(Date fecha) {
        Date oldFecha = this.fecha;
        this.fecha = fecha;
        firePropertyChange(PROP_FECHA, oldFecha, fecha);
    }

    /**
     * Get the value of show_productos
     *
     * @return the value of show_productos
     */
    public boolean isShow_productos() {
        return show_productos;
    }

    /**
     * Set the value of show_productos
     *
     * @param show_productos new value of show_productos
     */
    public void setShow_productos(boolean show_productos) {
        boolean oldShow_productos = this.show_productos;
        this.show_productos = show_productos;
        firePropertyChange(PROP_SHOW_PRODUCTOS, oldShow_productos, show_productos);
    }

    /**
     * Get the value of componentes_enabled
     *
     * @return the value of componentes_enabled
     */
    public boolean isComponentes_enabled() {
        return componentes_enabled;
    }

    /**
     * Set the value of componentes_enabled
     *
     * @param componentes_enabled new value of componentes_enabled
     */
    public void setComponentes_enabled(boolean componentes_enabled) {
        boolean oldComponentes_enabled = this.componentes_enabled;
        this.componentes_enabled = componentes_enabled;
        firePropertyChange(PROP_COMPONENTES_ENABLED, oldComponentes_enabled, componentes_enabled);
    }

}
