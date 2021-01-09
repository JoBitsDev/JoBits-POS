/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Cliente;
import com.jobits.pos.domain.models.Mesa;
import com.jobits.pos.domain.models.ProductovOrden;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;

/**
 *
 * @author Home
 */
public class ReservaDetailViewModel extends AbstractViewModel {

    private boolean componentes_enabled;

    public static final String PROP_COMPONENTES_ENABLED = "componentes_enabled";

    private boolean show_productos = false;

    public static final String PROP_SHOW_PRODUCTOS = "show_productos";

    //MESA
    private ArrayListModel<Mesa> lista_mesas = new ArrayListModel<>();

    public static final String PROP_LISTA_MESAS = "lista_mesas";

    private Mesa mesa_seleccionada;

    public static final String PROP_MESA_SELECCIONADA = "mesa_seleccionada";

    //FECHA
    private Date fecha = new Date();

    private int hora = 1;

    public static final String PROP_HORA = "hora";

    private int minutos = 0;

    public static final String PROP_MINUTOS = "minutos";

    private String pm_am = "PM";

    public static final String PROP_PM_AM = "pm_am";

    //CLIENTE
    private Cliente cliente;

    public static final String PROP_CLIENTE = "cliente";

    private ArrayListModel<Cliente> lista_clientes = new ArrayListModel<>();

    public static final String PROP_LISTA_CLIENTES = "lista_clientes";

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
    private String nombre_cliente;

    public static final String PROP_NOMBRE_CLIENTE = "nombre_cliente";

    private String apellido_cliente;

    public static final String PROP_APELLIDO_CLIENTE = "apellido_cliente";

    private String telefono_cliente;

    public static final String PROP_TELEFONO_CLIENTE = "telefono_cliente";

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
     * Get the value of pm_am
     *
     * @return the value of pm_am
     */
    public String getPm_am() {
        return pm_am;
    }

    /**
     * Set the value of pm_am
     *
     * @param pm_am new value of pm_am
     */
    public void setPm_am(String pm_am) {
        String oldPm_am = this.pm_am;
        this.pm_am = pm_am;
        firePropertyChange(PROP_PM_AM, oldPm_am, pm_am);
    }

    /**
     * Get the value of minutos
     *
     * @return the value of minutos
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Set the value of minutos
     *
     * @param minutos new value of minutos
     */
    public void setMinutos(int minutos) {
        int oldMinutos = this.minutos;
        this.minutos = minutos;
        firePropertyChange(PROP_MINUTOS, oldMinutos, minutos);
    }

    /**
     * Get the value of hora
     *
     * @return the value of hora
     */
    public int getHora() {
        return hora;
    }

    /**
     * Set the value of hora
     *
     * @param hora new value of hora
     */
    public void setHora(int hora) {
        int oldHora = this.hora;
        this.hora = hora;
        firePropertyChange(PROP_HORA, oldHora, hora);
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
