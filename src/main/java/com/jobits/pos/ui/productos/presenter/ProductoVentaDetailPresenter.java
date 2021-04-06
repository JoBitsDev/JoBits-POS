/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.imagemanager.ImageManagerService;
import com.jobits.pos.controller.productos.ProductoVentaDetailService;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionListService;
import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Insumo;
import com.jobits.pos.core.domain.models.ProductoInsumo;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.imagemanager.ImageManagerPopUpContainer;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import com.jobits.pos.utils.utils;
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

    private final ProductoVentaDetailService service = PosDesktopUiModule.getInstance().getImplementation(ProductoVentaDetailService.class);
    private final ImageManagerService imageService = PosDesktopUiModule.getInstance().getImplementation(ImageManagerService.class);
    private final PuntoElaboracionListService ptoElabService = PosDesktopUiModule.getInstance().getImplementation(PuntoElaboracionListService.class);
    private final SeccionListService seccionService = PosDesktopUiModule.getInstance().getImplementation(SeccionListService.class);

    private final boolean creatingMode;
    ProductoVenta productoVenta;

    /**
     * Si es nulo es que el producto que se va a crear es nuevo
     *
     * @param productoVenta
     */
    public ProductoVentaDetailPresenter(ProductoVenta productoVenta) {
        super(new ProductoVentaDetailViewModel());
        this.creatingMode = productoVenta == null;
        if (creatingMode) {
            this.productoVenta = service.createNewInstance();
        } else {
            this.productoVenta = productoVenta;
        }
        refreshView();
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

    private void refreshView() {
        getBean().getLista_categorias().addAll(new ArrayListModel<>(this.service.getSeccionList()));
        getBean().getLista_elaborado().addAll(new ArrayListModel<>(this.service.getCocinaList()));
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(this.service.getInsumoList()));
        getBean().setCategoria_seleccionada(productoVenta.getSeccionnombreSeccion());
        getBean().setNombre_producto(productoVenta.getNombre());
        getBean().setCodigo_producto(productoVenta.getCodigoProducto());
        getBean().setTimepo_elaboracion(productoVenta.getTiempoServicioMin());
        if (productoVenta.getPagoPorVenta() != null) {
            getBean().setComision_por_venta("" + utils.setDosLugaresDecimalesFloat(productoVenta.getPagoPorVenta()));
        }
        getBean().setElaborado_seleccionado(productoVenta.getCocinacodCocina());
        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(new ArrayListModel<>(productoVenta.getProductoInsumoList()));
        getBean().setCheckbox_producto_elaborado(!getBean().getLista_insumos_contenidos().isEmpty());
        getBean().setPrecio_venta(String.valueOf(utils.setDosLugaresDecimalesFloat(productoVenta.getPrecioVenta())));
        updateCostoValue();
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(service.getInsumoList()));
        getBean().setRuta_imagen_producto(productoVenta.getDescripcion());
        getBean().setCodigo_producto(productoVenta.getCodigoProducto());
        if (productoVenta.getComisionPorcientoPorVenta() != null) {
            getBean().setComision_por_venta_porcentual(productoVenta.getComisionPorcientoPorVenta());
        }
        if (creatingMode) {
            getBean().setCrear_editar_button_text("Crear");
        } else {
            getBean().setCrear_editar_button_text("Editar");
        }
    }

    private void onAddIngredienteClick() {
        service.registrarNuevoInsumo();
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(service.getInsumoList());
    }

    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            service.fillProductoVentaData(productoVenta,
                    getBean().getNombre_producto(),
                    getBean().getPrecio_costo(),
                    getBean().getComision_por_venta(),
                    getBean().getPrecio_venta(),
                    getBean().getElaborado_seleccionado(),
                    getBean().getCategoria_seleccionada(),
                    getBean().getLista_insumos_contenidos(),
                    getBean().getRuta_imagen_producto(),
                    getBean().getTimepo_elaboracion(),
                    getBean().getComision_por_venta_porcentual());
            if (creatingMode) {
                service.create(productoVenta);
            } else {
                service.create(productoVenta);
            }
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            NavigationService.getInstance().navigateUp();//TODO: faltan los insumos
        }
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea cancelar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }

    }

    private void onAddCategoriaClick() {
        String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre de la sección a crear",
                "Nueva Sección", JOptionPane.QUESTION_MESSAGE);
        seccionService.createInstance(nombre);
        getBean().setLista_categorias(new ArrayListModel<>(service.getSeccionList()));
    }

    private void onAddElaboracionCLick() {
        String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre del Punto de Elaboracion a crear",
                "Nuevo Punto de Elaboracion", JOptionPane.QUESTION_MESSAGE);
        ptoElabService.createInstance(nombre);
        getBean().setLista_elaborado(new ArrayListModel<>(service.getCocinaList()));
    }

    private void onAgregarInsumoFichaClick() {
        Float cantidad = new NumberPad(null).showView();
        if (cantidad != null) {
            Insumo inSel = getBean().getInsumo_disponible_sel();
            service.agregarInsumoaProducto(productoVenta, inSel, cantidad);
            getBean().setInsumo_disponible_sel(null);
            getBean().getLista_insumos_contenidos().clear();
            getBean().getLista_insumos_contenidos().addAll(productoVenta.getProductoInsumoList());
            updateCostoValue();
            getBean().setInsumo_disponible_sel(null);
        }

    }

    private void updateCostoValue() {
        float total = 0;
        if (!getBean().getLista_insumos_contenidos().isEmpty()) {
            total = getBean().getLista_insumos_contenidos().stream().map(x -> x.getCosto()).reduce(total, (accumulator, _item) -> accumulator + _item);
        }
        getBean().setPrecio_costo(String.valueOf(utils.setDosLugaresDecimalesFloat(total)));
    }

    private void onEliminarInsumoFichaClick() {
        ProductoInsumo inSel = getBean().getInsumo_contenido_seleccionado();
        service.eliminarInsumoProducto(productoVenta, inSel);
        getBean().setInsumo_contenido_seleccionado(null);
        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(productoVenta.getProductoInsumoList());
        updateCostoValue();
    }

    private void onEditarImagenClick() {
        ImageManagerPopUpContainer a = new ImageManagerPopUpContainer(null, productoVenta.getCodigoProducto());
//        TODO: Arreglar Navegacion al ImageManagerPopup
        getBean().setRuta_imagen_producto(R.MEDIA_FILE_PATH + productoVenta.getCodigoProducto() + ".jpg");
    }

    private void refreshProductImage() {
        String path = getBean().getRuta_imagen_producto();
        ImageIcon image = imageService.loadImageIcon(path, new Dimension(70, 70));
        getBean().setImagen_producto(image);
    }

}
