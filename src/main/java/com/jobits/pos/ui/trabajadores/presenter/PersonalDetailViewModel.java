/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.trabajadores.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.domain.models.PuestoTrabajo;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.util.Date;

/**
 *
 * @author Home
 */
public class PersonalDetailViewModel extends AbstractViewModel {

    private String nombre_trabajador;

    public static final String PROP_NOMBRE_TRABAJADOR = "nombre_trabajador";

    private String apellidos_trabajador;

    public static final String PROP_APELLIDOS_TRABAJADOR = "apellidos_trabajador";

    private ArrayListModel puestos_trabajo_list = new ArrayListModel<>();

    public static final String PROP_PUESTOS_TRABAJO_LIST = "puestos_trabajo_list";

    private PuestoTrabajo puesto_trabajo_seleccionado;

    public static final String PROP_PUESTO_TRABAJO_SELECCIONADO = "puesto_trabajo_seleccionado";

    private String usuario_trabajador;

    public static final String PROP_USUARIO_TRABAJADOR = "usuario_trabajador";

    private String contrasena_antigua;

    public static final String PROP_CONTRASENA_ANTIGUA = "contrasena_antigua";

    private String contrasena_nueva;

    public static final String PROP_CONTRASENA_NUEVA = "contrasena_nueva";

    private String contrasena_nueva_repetida;

    public static final String PROP_CONTRASENA_NUEVA_REPETIDA = "contrasena_nueva_repetida";

    private ArrayListModel sexo_list = new ArrayListModel<>();

    public static final String PROP_SEXO_LIST = "sexo_list";

    private String sexo_seleccionado;

    public static final String PROP_SEXO_SELECCIONADO = "sexo_seleccionado";

    private Date fecha_nacimiento;

    public static final String PROP_FECHA_NACIMIENTO = "fecha_nacimiento";

    private String telefono_fijo;

    public static final String PROP_TELEFONO_FIJO = "telefono_fijo";

    private String telefono_movil;

    public static final String PROP_TELEFONO_MOVIL = "telefono_movil";

    private String crear_editar_button_text;

    public static final String PROP_CREAR_EDITAR_BUTTON_TEXT = "crear_editar_button_text";

    private String direccion;

    public static final String PROP_DIRECCION = "direccion";

    private String carnet_identidad;

    public static final String PROP_CARNET_IDENTIDAD = "carnet_identidad";

    /**
     * Get the value of carnet_identidad
     *
     * @return the value of carnet_identidad
     */
    public String getCarnet_identidad() {
        return carnet_identidad;
    }

    /**
     * Set the value of carnet_identidad
     *
     * @param carnet_identidad new value of carnet_identidad
     */
    public void setCarnet_identidad(String carnet_identidad) {
        String oldCarnet_identidad = this.carnet_identidad;
        this.carnet_identidad = carnet_identidad;
        firePropertyChange(PROP_CARNET_IDENTIDAD, oldCarnet_identidad, carnet_identidad);
    }

    /**
     * Get the value of direccion
     *
     * @return the value of direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set the value of direccion
     *
     * @param direccion new value of direccion
     */
    public void setDireccion(String direccion) {
        String oldDireccion = this.direccion;
        this.direccion = direccion;
        firePropertyChange(PROP_DIRECCION, oldDireccion, direccion);
    }

    /**
     * Get the value of crear_editar_button_text
     *
     * @return the value of crear_editar_button_text
     */
    public String getCrear_editar_button_text() {
        return crear_editar_button_text;
    }

    /**
     * Set the value of crear_editar_button_text
     *
     * @param crear_editar_button_text new value of crear_editar_button_text
     */
    public void setCrear_editar_button_text(String crear_editar_button_text) {
        String oldCrear_editar_button_text = this.crear_editar_button_text;
        this.crear_editar_button_text = crear_editar_button_text;
        firePropertyChange(PROP_CREAR_EDITAR_BUTTON_TEXT, oldCrear_editar_button_text, crear_editar_button_text);
    }

    /**
     * Get the value of telefono_movil
     *
     * @return the value of telefono_movil
     */
    public String getTelefono_movil() {
        return telefono_movil;
    }

    /**
     * Set the value of telefono_movil
     *
     * @param telefono_movil new value of telefono_movil
     */
    public void setTelefono_movil(String telefono_movil) {
        String oldTelefono_movil = this.telefono_movil;
        this.telefono_movil = telefono_movil;
        firePropertyChange(PROP_TELEFONO_MOVIL, oldTelefono_movil, telefono_movil);
    }

    /**
     * Get the value of telefono_fijo
     *
     * @return the value of telefono_fijo
     */
    public String getTelefono_fijo() {
        return telefono_fijo;
    }

    /**
     * Set the value of telefono_fijo
     *
     * @param telefono_fijo new value of telefono_fijo
     */
    public void setTelefono_fijo(String telefono_fijo) {
        String oldTelefono_fijo = this.telefono_fijo;
        this.telefono_fijo = telefono_fijo;
        firePropertyChange(PROP_TELEFONO_FIJO, oldTelefono_fijo, telefono_fijo);
    }

    /**
     * Get the value of fecha_nacimiento
     *
     * @return the value of fecha_nacimiento
     */
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Set the value of fecha_nacimiento
     *
     * @param fecha_nacimiento new value of fecha_nacimiento
     */
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        Date oldFecha_nacimiento = this.fecha_nacimiento;
        this.fecha_nacimiento = fecha_nacimiento;
        firePropertyChange(PROP_FECHA_NACIMIENTO, oldFecha_nacimiento, fecha_nacimiento);
    }

    /**
     * Get the value of sexo_seleccionado
     *
     * @return the value of sexo_seleccionado
     */
    public String getSexo_seleccionado() {
        return sexo_seleccionado;
    }

    /**
     * Set the value of sexo_seleccionado
     *
     * @param sexo_seleccionado new value of sexo_seleccionado
     */
    public void setSexo_seleccionado(String sexo_seleccionado) {
        String oldSexo_seleccionado = this.sexo_seleccionado;
        this.sexo_seleccionado = sexo_seleccionado;
        firePropertyChange(PROP_SEXO_SELECCIONADO, oldSexo_seleccionado, sexo_seleccionado);
    }

    /**
     * Get the value of sexo_list
     *
     * @return the value of sexo_list
     */
    public ArrayListModel getSexo_list() {
        return sexo_list;
    }

    /**
     * Set the value of sexo_list
     *
     * @param sexo_list new value of sexo_list
     */
    public void setSexo_list(ArrayListModel sexo_list) {
        ArrayListModel oldSexo_list = this.sexo_list;
        this.sexo_list = sexo_list;
        firePropertyChange(PROP_SEXO_LIST, oldSexo_list, sexo_list);
    }

    /**
     * Get the value of contrasena_nueva_repetida
     *
     * @return the value of contrasena_nueva_repetida
     */
    public String getContrasena_nueva_repetida() {
        return contrasena_nueva_repetida;
    }

    /**
     * Set the value of contrasena_nueva_repetida
     *
     * @param contrasena_nueva_repetida new value of contrasena_nueva_repetida
     */
    public void setContrasena_nueva_repetida(String contrasena_nueva_repetida) {
        String oldContrasena_nueva_repetida = this.contrasena_nueva_repetida;
        this.contrasena_nueva_repetida = contrasena_nueva_repetida;
        firePropertyChange(PROP_CONTRASENA_NUEVA_REPETIDA, oldContrasena_nueva_repetida, contrasena_nueva_repetida);
    }

    /**
     * Get the value of contrasena_nueva
     *
     * @return the value of contrasena_nueva
     */
    public String getContrasena_nueva() {
        return contrasena_nueva;
    }

    /**
     * Set the value of contrasena_nueva
     *
     * @param contrasena_nueva new value of contrasena_nueva
     */
    public void setContrasena_nueva(String contrasena_nueva) {
        String oldContrasena_nueva = this.contrasena_nueva;
        this.contrasena_nueva = contrasena_nueva;
        firePropertyChange(PROP_CONTRASENA_NUEVA, oldContrasena_nueva, contrasena_nueva);
    }

    /**
     * Get the value of contrasena_antigua
     *
     * @return the value of contrasena_antigua
     */
    public String getContrasena_antigua() {
        return contrasena_antigua;
    }

    /**
     * Set the value of contrasena_antigua
     *
     * @param contrasena_antigua new value of contrasena_antigua
     */
    public void setContrasena_antigua(String contrasena_antigua) {
        String oldContrasena_antigua = this.contrasena_antigua;
        this.contrasena_antigua = contrasena_antigua;
        firePropertyChange(PROP_CONTRASENA_ANTIGUA, oldContrasena_antigua, contrasena_antigua);
    }

    /**
     * Get the value of usuario_trabajador
     *
     * @return the value of usuario_trabajador
     */
    public String getUsuario_trabajador() {
        return usuario_trabajador;
    }

    /**
     * Set the value of usuario_trabajador
     *
     * @param usuario_trabajador new value of usuario_trabajador
     */
    public void setUsuario_trabajador(String usuario_trabajador) {
        String oldUsuario_trabajador = this.usuario_trabajador;
        this.usuario_trabajador = usuario_trabajador;
        firePropertyChange(PROP_USUARIO_TRABAJADOR, oldUsuario_trabajador, usuario_trabajador);
    }

    /**
     * Get the value of puesto_trabajo_seleccionado
     *
     * @return the value of puesto_trabajo_seleccionado
     */
    public PuestoTrabajo getPuesto_trabajo_seleccionado() {
        return puesto_trabajo_seleccionado;
    }

    /**
     * Set the value of puesto_trabajo_seleccionado
     *
     * @param puesto_trabajo_seleccionado new value of
     * puesto_trabajo_seleccionado
     */
    public void setPuesto_trabajo_seleccionado(PuestoTrabajo puesto_trabajo_seleccionado) {
        PuestoTrabajo oldPuesto_trabajo_seleccionado = this.puesto_trabajo_seleccionado;
        this.puesto_trabajo_seleccionado = puesto_trabajo_seleccionado;
        firePropertyChange(PROP_PUESTO_TRABAJO_SELECCIONADO, oldPuesto_trabajo_seleccionado, puesto_trabajo_seleccionado);
    }

    /**
     * Get the value of puestos_trabajo_list
     *
     * @return the value of puestos_trabajo_list
     */
    public ArrayListModel getPuestos_trabajo_list() {
        return puestos_trabajo_list;
    }

    /**
     * Set the value of puestos_trabajo_list
     *
     * @param puestos_trabajo_list new value of puestos_trabajo_list
     */
    public void setPuestos_trabajo_list(ArrayListModel puestos_trabajo_list) {
        ArrayListModel oldPuestos_trabajo_list = this.puestos_trabajo_list;
        this.puestos_trabajo_list = puestos_trabajo_list;
        firePropertyChange(PROP_PUESTOS_TRABAJO_LIST, oldPuestos_trabajo_list, puestos_trabajo_list);
    }

    /**
     * Get the value of apellidos_trabajador
     *
     * @return the value of apellidos_trabajador
     */
    public String getApellidos_trabajador() {
        return apellidos_trabajador;
    }

    /**
     * Set the value of apellidos_trabajador
     *
     * @param apellidos_trabajador new value of apellidos_trabajador
     */
    public void setApellidos_trabajador(String apellidos_trabajador) {
        String oldApellidos_trabajador = this.apellidos_trabajador;
        this.apellidos_trabajador = apellidos_trabajador;
        firePropertyChange(PROP_APELLIDOS_TRABAJADOR, oldApellidos_trabajador, apellidos_trabajador);
    }

    /**
     * Get the value of nombre_trabajador
     *
     * @return the value of nombre_trabajador
     */
    public String getNombre_trabajador() {
        return nombre_trabajador;
    }

    /**
     * Set the value of nombre_trabajador
     *
     * @param nombre_trabajador new value of nombre_trabajador
     */
    public void setNombre_trabajador(String nombre_trabajador) {
        String oldNombre_trabajador = this.nombre_trabajador;
        this.nombre_trabajador = nombre_trabajador;
        firePropertyChange(PROP_NOMBRE_TRABAJADOR, oldNombre_trabajador, nombre_trabajador);
    }

}
