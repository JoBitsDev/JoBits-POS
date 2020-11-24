/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.imagemanager.ImageManagerController;
import com.jobits.pos.controller.productos.ProductoVentaDetailService;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListController;
import com.jobits.pos.controller.seccion.SeccionListController;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.Insumo;
import com.jobits.pos.domain.models.ProductoInsumo;
import com.jobits.pos.domain.models.ProductoVenta;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.imagemanager.ImageManagerPopUpContainer;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.utils;
import java.awt.Dimension;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaDetailPresenter extends AbstractViewPresenter<ProductoVentaDetailViewModel> {

    public static final String ACTION_AGREGAR_PUNTO_ELABORACION = "Nuevo Pto Elaboración";
    public static final String ACTION_AGREGAR_CATEGORIA = "Nueva Categoría";
    public static final String ACTION_AGREGAR_INSUMO = "Nuevo Insumo";
    public static final String ACTION_AGREGAR = "Aceptar";
    public static final String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR_INSUMO_FICHA = "Registrar";
    public static String ACTION_ELIMINAR_INSUMO_FICHA = "Eliminar";
    public static String ACTION_EDITAR_IMAGEN = "Editar Imagen";

    private ProductoVentaDetailService service;
    private boolean creatingMode = true;
    ProductoVenta p;

    /**
     * Si es nulo es que el producto que se va a crear es nuevo
     *
     * @param controller
     * @param productoSeleccionado
     */
    public ProductoVentaDetailPresenter(ProductoVentaDetailService controller, ProductoVenta productoSeleccionado) {
        super(new ProductoVentaDetailViewModel());
        this.service = controller;
        getBean().getLista_categorias().addAll(new ArrayListModel<>(this.service.getSeccionList()));
        getBean().getLista_elaborado().addAll(new ArrayListModel<>(this.service.getCocinaList()));
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(this.service.getInsumoList()));
        if (productoSeleccionado != null) {
            refreshState(productoSeleccionado);
        }
        creatingMode = productoSeleccionado == null;
        if (creatingMode) {
            p = service.createNewInstance();
            getBean().setCrear_editar_button_text("Crear");
        } else {
            p = service.getInstance();
            getBean().setCrear_editar_button_text("Editar");
        }
        getBean().setCodigo_producto(p.getCodigoProducto());
        refreshProductImage();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR) {
            @Override
            public Optional doAction() {
                onAceptarClick();
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_AGREGAR_CATEGORIA) {
            @Override
            public Optional doAction() {
                onAddCategoriaClick();
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_AGREGAR_PUNTO_ELABORACION) {
            @Override
            public Optional doAction() {
                onAddElaboracionCLick();
                return Optional.empty();
            }
        });

        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_INSUMO) {
            @Override
            public Optional doAction() {
                onAddIngredienteClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_INSUMO_FICHA) {
            @Override
            public Optional doAction() {
                onEliminarInsumoFichaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_INSUMO_FICHA) {
            @Override
            public Optional doAction() {
                onAgregarInsumoFichaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EDITAR_IMAGEN) {
            @Override
            public Optional doAction() {
                onEditarImagenClick();
                refreshProductImage();
                return Optional.empty();
            }
        });
    }

    private void refreshState(ProductoVenta productoSeleccionado) {
        getBean().setCategoria_seleccionada(productoSeleccionado.getSeccionnombreSeccion());
        getBean().setNombre_producto(productoSeleccionado.getNombre());
        getBean().setCodigo_producto(productoSeleccionado.getCodigoProducto());
        if (productoSeleccionado.getPagoPorVenta() != null) {
            getBean().setComision_por_venta("" + utils.setDosLugaresDecimalesFloat(productoSeleccionado.getPagoPorVenta()));
        }
        getBean().setElaborado_seleccionado(productoSeleccionado.getCocinacodCocina());
        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(new ArrayListModel<>(productoSeleccionado.getProductoInsumoList()));
        getBean().setCheckbox_producto_elaborado(!getBean().getLista_insumos_contenidos().isEmpty());
        getBean().setPrecio_venta("" + R.formatoMoneda.format(productoSeleccionado.getPrecioVenta()));
        updateCostoValue();
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(service.getInsumoList()));
        getBean().setRuta_imagen_producto(productoSeleccionado.getDescripcion());
    }

    private void onAddIngredienteClick() {
        service.registrarNuevoInsumo();
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(service.getInsumoList());
    }

    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea guardar los cambios",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            if (getBean().getNombre_producto() != null) {
                p.setNombre(getBean().getNombre_producto());
            } else {
                JOptionPane.showMessageDialog(null, "Introduzca el nombre del producto");
            }
            if (getBean().getPrecio_costo() == null || getBean().getPrecio_costo().equals("")) {
                p.setGasto(0f);
            } else {
                p.setGasto(Float.valueOf(getBean().getPrecio_costo()));
            }
            if (getBean().getComision_por_venta() != null) {
                p.setPagoPorVenta(Float.parseFloat(getBean().getComision_por_venta()));
            } else {
                p.setPagoPorVenta((float) 0);
            }
            p.setPrecioVenta(Float.parseFloat(getBean().getPrecio_venta()));
            p.setCocinacodCocina(getBean().getElaborado_seleccionado());
            p.setSeccionnombreSeccion(getBean().getCategoria_seleccionada());
            p.setProductoInsumoList(getBean().getLista_insumos_contenidos());
            p.setDescripcion(getBean().getRuta_imagen_producto());
            p.setVisible(true);
            if (creatingMode) {
                service.create(p);
            } else {
                service.update(p);
            }
            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        }

    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea descartar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.discardChanges();
            NavigationService.getInstance().navigateUp();
        }

    }

    private void onAddCategoriaClick() {
        new SeccionListController().createInstance();
        getBean().setLista_categorias(new ArrayListModel<>(service.getSeccionList()));

    }

    private void onAddElaboracionCLick() {
        new PuntoElaboracionListController().createInstance();
        getBean().setLista_elaborado(new ArrayListModel<>(service.getCocinaList()));

    }

    private void onAgregarInsumoFichaClick() {
        Optional<String> opt = Application.getInstance().getNotificationService().showDialog("Introduzca la cantidad de " + getBean().getInsumo_disponible_sel(), TipoNotificacion.DIALOG_INPUT);
        if (opt.isPresent()) {
            try {
                float cantidad = Float.parseFloat(opt.get());
                Insumo inSel = getBean().getInsumo_disponible_sel();
                service.agregarInsumoaProducto(inSel, cantidad);
                getBean().setInsumo_disponible_sel(null);
                getBean().getLista_insumos_contenidos().clear();
                getBean().getLista_insumos_contenidos().addAll(service.getInstance().getProductoInsumoList());
                updateCostoValue();
                getBean().setInsumo_disponible_sel(null);
            } catch (NumberFormatException ex) {
                Application.getInstance().getNotificationService().showDialog("Valores Incorrectos", TipoNotificacion.ERROR);
            }
        }

    }

    private void updateCostoValue() {
        if (!getBean().getLista_insumos_contenidos().isEmpty()) {
            float total = 0;
            total = getBean().getLista_insumos_contenidos().stream().map(x -> x.getCosto()).reduce(total, (accumulator, _item) -> accumulator + _item);
            getBean().setPrecio_costo(R.formatoMoneda.format(total));
        } else {
            getBean().setPrecio_costo(R.formatoMoneda.format(0));
        }
    }

    private void onEliminarInsumoFichaClick() {
        ProductoInsumo inSel = getBean().getInsumo_contenido_seleccionado();
        service.eliminarInsumoProducto(inSel);
        getBean().setInsumo_contenido_seleccionado(null);
        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(service.getInstance().getProductoInsumoList());
        updateCostoValue();
    }

    private void onEditarImagenClick() {
        new ImageManagerPopUpContainer(null, p.getCodigoProducto());
//        TODO: Arreglar Navegacion al ImageManagerPopup
        getBean().setRuta_imagen_producto(R.MEDIA_FILE_PATH + p.getCodigoProducto() + ".jpg");
    }

    private void refreshProductImage() {
        String path = getBean().getRuta_imagen_producto();
        ImageIcon image = ImageManagerController.loadImageIcon(path, new Dimension(70, 70));
        getBean().setImagen_producto(image);
    }

}
