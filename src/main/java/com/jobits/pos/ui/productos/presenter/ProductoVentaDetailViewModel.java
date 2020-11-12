/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.domain.models.Cocina;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.Seccion;
import com.jobits.pos.ui.viewmodel.AbstractViewModel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaDetailViewModel extends AbstractViewModel {

    //
    //Basico
    //
    @NotBlank
    private String nombre_producto;

    public static final String PROP_NOMBRE_PRODUCTO = "nombre_producto";

    @NotBlank
    private String precio_venta;

    public static final String PROP_PRECIO_VENTA = "precio_venta";

    private String codigo_producto;

    public static final String PROP_CODIGO_PRODUCTO = "codigo_producto";

    private ArrayListModel<Seccion> lista_categorias = new ArrayListModel<>();

    public static final String PROP_LISTA_CATEGORIAS = "lista_categorias";

    @NotNull
    private Seccion categoria_seleccionada;

    public static final String PROP_CATEGORIA_SELECCIONADA = "categoria_seleccionada";

    private ArrayListModel<Cocina> lista_elaborado = new ArrayListModel<>();

    public static final String PROP_LISTA_ELABORADO = "lista_elaborado";

    @NotNull
    private Cocina elaborado_seleccionado;

    public static final String PROP_ELABORADO_SELECCIONADO = "elaborado_seleccionado";

    @NotEmpty
    private String precio_costo;

    public static final String PROP_PRECIO_COSTO = "precio_costo";

    //
    // Inventario
    //
    private boolean checkbox_inventariar_producto;

    public static final String PROP_CHECKBOX_INVENTARIAR_PRODUCTO = "checkbox_inventariar_producto";

    private boolean checkbox_producto_elaborado;

    public static final String PROP_CHECKBOX_PRODUCTO_ELABORADO = "checkbox_producto_elaborado";

    private ArrayListModel<Insumo> lista_insumos_disponibles = new ArrayListModel<>();

    public static final String PROP_LISTA_INSUMOS_DISPONIBLES = "lista_insumos_disponibles";

    private Insumo insumo_disponible_sel;

    public static final String PROP_INSUMO_DISPONIBLE_SEL = "insumo_disponible_sel";

    private ArrayListModel<ProductoInsumo> lista_insumos_contenidos = new ArrayListModel<>();

    public static final String PROP_LISTA_INSUMOS_CONTENIDOS = "lista_insumos_contenidos";

    private ProductoInsumo insumo_contenido_seleccionado;

    public static final String PROP_INSUMO_SELECCIONADO = "insumo_contenido_seleccionado";

    /**
     * Get the value of insumo_disponible_sel
     *
     * @return the value of insumo_disponible_sel
     */
    public Insumo getInsumo_disponible_sel() {
        return insumo_disponible_sel;
    }

    /**
     * Set the value of insumo_disponible_sel
     *
     * @param insumo_disponible_sel new value of insumo_disponible_sel
     */
    public void setInsumo_disponible_sel(Insumo insumo_disponible_sel) {
        Insumo oldInsumo_disponible_sel = this.insumo_disponible_sel;
        this.insumo_disponible_sel = insumo_disponible_sel;
        firePropertyChange(PROP_INSUMO_DISPONIBLE_SEL, oldInsumo_disponible_sel, insumo_disponible_sel, false);
    }

    /**
     * Get the value of insumo_contenido_seleccionado
     *
     * @return the value of insumo_contenido_seleccionado
     */
    public ProductoInsumo getInsumo_contenido_seleccionado() {
        return insumo_contenido_seleccionado;
    }

    /**
     * Set the value of insumo_contenido_seleccionado
     *
     * @param insumo_contenido_seleccionado new value of
     * insumo_contenido_seleccionado
     */
    public void setInsumo_contenido_seleccionado(ProductoInsumo insumo_contenido_seleccionado) {
        ProductoInsumo oldInsumo_seleccionado = this.insumo_contenido_seleccionado;
        this.insumo_contenido_seleccionado = insumo_contenido_seleccionado;
        firePropertyChange(PROP_INSUMO_SELECCIONADO, oldInsumo_seleccionado, insumo_contenido_seleccionado, false);
    }

    //
    // Otros
    //
    private String comision_por_venta;

    public static final String PROP_COMISION_POR_VENTA = "comision_por_venta";

    private ImageIcon imagen_producto;

    public static final String PROP_IMAGEN_PRODUCTO = "imagen_producto";

    private String ruta_imagen_producto;

    public static final String PROP_RUTA_IMAGEN_PRODUCTO = "ruta_imagen_producto";

    private boolean image_product_visible = true;

    public static final String PROP_IMAGE_PRODUCT_VISIBLE = "image_product_visible";

    private String crear_editar_button_text;

    public static final String PROP_CREAR_EDITAR_BUTTON_TEXT = "crear_editar_button_text";

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
     * Get the value of image_product_visible
     *
     * @return the value of image_product_visible
     */
    public boolean isImage_product_visible() {
        return image_product_visible;
    }

    /**
     * Set the value of image_product_visible
     *
     * @param image_product_visible new value of image_product_visible
     */
    public void setImage_product_visible(boolean image_product_visible) {
        boolean oldImage_product_visible = this.image_product_visible;
        this.image_product_visible = image_product_visible;
        firePropertyChange(PROP_IMAGE_PRODUCT_VISIBLE, oldImage_product_visible, image_product_visible);
    }

    /**
     * Get the value of ruta_imagen_producto
     *
     * @return the value of ruta_imagen_producto
     */
    public String getRuta_imagen_producto() {
        return ruta_imagen_producto;
    }

    /**
     * Set the value of ruta_imagen_producto
     *
     * @param ruta_imagen_producto new value of ruta_imagen_producto
     */
    public void setRuta_imagen_producto(String ruta_imagen_producto) {
        String oldRuta_imagen_producto = this.ruta_imagen_producto;
        this.ruta_imagen_producto = ruta_imagen_producto;
        firePropertyChange(PROP_RUTA_IMAGEN_PRODUCTO, oldRuta_imagen_producto, ruta_imagen_producto);
    }

    /**
     * Get the value of imagen_producto
     *
     * @return the value of imagen_producto
     */
    public ImageIcon getImagen_producto() {
        return imagen_producto;
    }

    /**
     * Set the value of imagen_producto
     *
     * @param imagen_producto new value of imagen_producto
     */
    public void setImagen_producto(ImageIcon imagen_producto) {
        ImageIcon oldImagen_producto = this.imagen_producto;
        this.imagen_producto = imagen_producto;
        firePropertyChange(PROP_IMAGEN_PRODUCTO, oldImagen_producto, imagen_producto);
    }

    /**
     * Get the value of lista_insumos_contenidos
     *
     * @return the value of lista_insumos_contenidos
     */
    public ArrayListModel<ProductoInsumo> getLista_insumos_contenidos() {
        return lista_insumos_contenidos;
    }

    /**
     * Set the value of lista_insumos_contenidos
     *
     * @param lista_insumos_contenidos new value of lista_insumos_contenidos
     */
    public void setLista_insumos_contenidos(ArrayListModel<ProductoInsumo> lista_insumos_contenidos) {
        ArrayListModel<ProductoInsumo> oldLista_insumos_contenidos = this.lista_insumos_contenidos;
        this.lista_insumos_contenidos = lista_insumos_contenidos;
        firePropertyChange(PROP_LISTA_INSUMOS_CONTENIDOS, oldLista_insumos_contenidos, lista_insumos_contenidos, false);
    }

    /**
     * Get the value of lista_insumos_disponibles
     *
     * @return the value of lista_insumos_disponibles
     */
    public ArrayListModel<Insumo> getLista_insumos_disponibles() {
        return lista_insumos_disponibles;
    }

    /**
     * Set the value of lista_insumos_disponibles
     *
     * @param lista_insumos_disponibles new value of lista_insumos_disponibles
     */
    public void setLista_insumos_disponibles(ArrayListModel<Insumo> lista_insumos_disponibles) {
        ArrayListModel<Insumo> oldLista_insumos_disponibles = this.lista_insumos_disponibles;
        this.lista_insumos_disponibles = lista_insumos_disponibles;
        firePropertyChange(PROP_LISTA_INSUMOS_DISPONIBLES, oldLista_insumos_disponibles, lista_insumos_disponibles, false);
    }

    /**
     * Get the value of comision_por_venta
     *
     * @return the value of comision_por_venta
     */
    public String getComision_por_venta() {
        return comision_por_venta;
    }

    /**
     * Set the value of comision_por_venta
     *
     * @param comision_por_venta new value of comision_por_venta
     */
    public void setComision_por_venta(String comision_por_venta) {
        String oldComision_por_venta = this.comision_por_venta;
        this.comision_por_venta = comision_por_venta;
        firePropertyChange(PROP_COMISION_POR_VENTA, oldComision_por_venta, comision_por_venta, false);
    }

    /**
     * Get the value of checkbox_producto_elaborado
     *
     * @return the value of checkbox_producto_elaborado
     */
    public boolean isCheckbox_producto_elaborado() {
        return checkbox_producto_elaborado;
    }

    /**
     * Set the value of checkbox_producto_elaborado
     *
     * @param checkbox_producto_elaborado new value of
     * checkbox_producto_elaborado
     */
    public void setCheckbox_producto_elaborado(boolean checkbox_producto_elaborado) {
        boolean oldCheckbox_producto_elaborado = this.checkbox_producto_elaborado;
        this.checkbox_producto_elaborado = checkbox_producto_elaborado;
        firePropertyChange(PROP_CHECKBOX_PRODUCTO_ELABORADO, oldCheckbox_producto_elaborado, checkbox_producto_elaborado, true);
    }

    /**
     * Get the value of precio_costo
     *
     * @return the value of precio_costo
     */
    public String getPrecio_costo() {
        return precio_costo;
    }

    /**
     * Set the value of precio_costo
     *
     * @param precio_costo new value of precio_costo
     */
    public void setPrecio_costo(String precio_costo) {
        String oldPrecio_costo = this.precio_costo;
        this.precio_costo = precio_costo;
        firePropertyChange(PROP_PRECIO_COSTO, oldPrecio_costo, precio_costo, false);
    }

    /**
     * Get the value of checkbox_inventariar_producto
     *
     * @return the value of checkbox_inventariar_producto
     */
    public boolean isCheckbox_inventariar_producto() {
        return checkbox_inventariar_producto;
    }

    /**
     * Set the value of checkbox_inventariar_producto
     *
     * @param checkbox_inventariar_producto new value of
     * checkbox_inventariar_producto
     */
    public void setCheckbox_inventariar_producto(boolean checkbox_inventariar_producto) {
        boolean oldCheckbox_inventariar_producto = this.checkbox_inventariar_producto;
        this.checkbox_inventariar_producto = checkbox_inventariar_producto;
        firePropertyChange(PROP_CHECKBOX_INVENTARIAR_PRODUCTO, oldCheckbox_inventariar_producto, checkbox_inventariar_producto, true);
    }

    /**
     * Get the value of elaborado_seleccionado
     *
     * @return the value of elaborado_seleccionado
     */
    public Cocina getElaborado_seleccionado() {
        return elaborado_seleccionado;
    }

    /**
     * Set the value of elaborado_seleccionado
     *
     * @param elaborado_seleccionado new value of elaborado_seleccionado
     */
    public void setElaborado_seleccionado(Cocina elaborado_seleccionado) {
        Cocina oldElaborado_seleccionado = this.elaborado_seleccionado;
        this.elaborado_seleccionado = elaborado_seleccionado;
        firePropertyChange(PROP_ELABORADO_SELECCIONADO, oldElaborado_seleccionado, elaborado_seleccionado, false);
    }

    /**
     * Get the value of lista_elaborado
     *
     * @return the value of lista_elaborado
     */
    public ArrayListModel<Cocina> getLista_elaborado() {
        return lista_elaborado;
    }

    /**
     * Set the value of lista_elaborado
     *
     * @param lista_elaborado new value of lista_elaborado
     */
    public void setLista_elaborado(ArrayListModel<Cocina> lista_elaborado) {
        ArrayListModel<Cocina> oldLista_elaborado = this.lista_elaborado;
        this.lista_elaborado = lista_elaborado;
        firePropertyChange(PROP_LISTA_ELABORADO, oldLista_elaborado, lista_elaborado, false);
    }

    /**
     * Get the value of categoria_seleccionada
     *
     * @return the value of categoria_seleccionada
     */
    public Seccion getCategoria_seleccionada() {
        return categoria_seleccionada;
    }

    /**
     * Set the value of categoria_seleccionada
     *
     * @param categoria_seleccionada new value of categoria_seleccionada
     */
    public void setCategoria_seleccionada(Seccion categoria_seleccionada) {
        Seccion oldCategoria_seleccionada = this.categoria_seleccionada;
        this.categoria_seleccionada = categoria_seleccionada;
        firePropertyChange(PROP_CATEGORIA_SELECCIONADA, oldCategoria_seleccionada, categoria_seleccionada, false);
    }

    /**
     * Get the value of lista_categorias
     *
     * @return the value of lista_categorias
     */
    public ArrayListModel<Seccion> getLista_categorias() {
        return lista_categorias;
    }

    /**
     * Set the value of lista_categorias
     *
     * @param lista_categorias new value of lista_categorias
     */
    public void setLista_categorias(ArrayListModel<Seccion> lista_categorias) {
        ArrayListModel<Seccion> oldLista_categorias = this.lista_categorias;
        this.lista_categorias = lista_categorias;
        firePropertyChange(PROP_LISTA_CATEGORIAS, oldLista_categorias, lista_categorias, false);
    }

    /**
     * Get the value of codigo_producto
     *
     * @return the value of codigo_producto
     */
    public String getCodigo_producto() {
        return codigo_producto;
    }

    /**
     * Set the value of codigo_producto
     *
     * @param codigo_producto new value of codigo_producto
     */
    public void setCodigo_producto(String codigo_producto) {
        String oldCodigo_producto = this.codigo_producto;
        this.codigo_producto = codigo_producto;
        firePropertyChange(PROP_CODIGO_PRODUCTO, oldCodigo_producto, codigo_producto, false);
    }

    /**
     * Get the value of precio_venta
     *
     * @return the value of precio_venta
     */
    public String getPrecio_venta() {
        return precio_venta;
    }

    /**
     * Set the value of precio_venta
     *
     * @param precio_venta new value of precio_venta
     */
    public void setPrecio_venta(String precio_venta) {
        String oldPrecio_venta = this.precio_venta;
        this.precio_venta = precio_venta;
        firePropertyChange(PROP_PRECIO_VENTA, oldPrecio_venta, precio_venta, false);
    }

    /**
     * Get the value of nombre_producto
     *
     * @return the value of nombre_producto
     */
    public String getNombre_producto() {
        return nombre_producto;
    }

    /**
     * Set the value of nombre_producto
     *
     * @param nombre_producto new value of nombre_producto
     */
    public void setNombre_producto(String nombre_producto) {
        String oldNombre_producto = this.nombre_producto;
        this.nombre_producto = nombre_producto;
        firePropertyChange(PROP_NOMBRE_PRODUCTO, oldNombre_producto, nombre_producto, false);
    }

}
