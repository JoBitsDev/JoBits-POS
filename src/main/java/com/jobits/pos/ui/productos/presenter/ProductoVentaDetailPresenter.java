/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.productos.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.configuracion.ConfiguracionService;
import com.jobits.pos.controller.insumo.InsumoService;
import com.jobits.pos.controller.productos.ProductoVentaService;
import com.jobits.pos.controller.puntoelaboracion.PuntoElaboracionService;
import com.jobits.pos.controller.seccion.CartaListService;
import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.*;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.imagemanager.ImageManagerPopUpContainer;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.swing.material.standards.MaterialIcons;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.jobits.pos.ui.productos.presenter.ProductoVentaDetailViewModel.PROP_RUTA_IMAGEN_PRODUCTO;

/**
 * JoBits
 *
 * @author Jorge
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

    private final ProductoVentaService service = PosDesktopUiModule.getInstance().getImplementation(ProductoVentaService.class);
    //private final ImageManagerService imageService = PosDesktopUiModule.getInstance().getImplementation(ImageManagerService.class);
    private final PuntoElaboracionService ptoElabService = PosDesktopUiModule.getInstance().getImplementation(PuntoElaboracionService.class);
    private final SeccionListService seccionService = PosDesktopUiModule.getInstance().getImplementation(SeccionListService.class);
    private final InsumoService insumoService = PosDesktopUiModule.getInstance().getImplementation(InsumoService.class);
    private final CartaListService cartaService = PosDesktopUiModule.getInstance().getImplementation(CartaListService.class);

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
            int value = PosDesktopUiModule.getInstance().getImplementation(ConfiguracionService.class).getConfiguracion(R.SettingID.HORARIO_TIEMPO_MIN_SERVICIO).getValor();
            this.productoVenta = new ProductoVenta();
            this.productoVenta.setTiempoServicioMin(value);
            this.productoVenta.setProductoInsumoList(new ArrayList<>());
//            this.productoVenta.setProductovOrdenList(new ArrayList<>());
        } else {
            this.productoVenta = productoVenta;
        }
        addListteners();
        refreshState();
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

    @Override
    protected Optional refreshState() {
        getBean().getLista_categorias().addAll(new ArrayListModel<>(seccionService.findAll()));
        getBean().getLista_elaborado().addAll(new ArrayListModel<>(ptoElabService.findAll()));
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(insumoService.findAll()));
        getBean().setCategoria_seleccionada(productoVenta.getSeccionnombreSeccion());
        getBean().setNombre_producto(productoVenta.getNombre());
        getBean().setCodigo_producto(productoVenta.getCodigoProducto());
        getBean().setTimepo_elaboracion(productoVenta.getTiempoServicioMin());
        if (productoVenta.getPagoPorVenta() != null) {
            getBean().setComision_por_venta("" + utils.setDosLugaresDecimalesFloat(productoVenta.getPagoPorVenta()));
        }
        if (productoVenta.getCocinacodCocina() != null) {
            getBean().setElaborado_seleccionado(ptoElabService.findBy(productoVenta.getCocinacodCocina()));
        }
        fillInsumoProductoInfo(productoVenta, getBean().getLista_insumos_disponibles());
        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(new ArrayListModel<>(productoVenta.getProductoInsumoList()));
        getBean().setCheckbox_producto_elaborado(!getBean().getLista_insumos_contenidos().isEmpty());
        getBean().setPrecio_venta(String.valueOf(utils.setDosLugaresDecimalesFloat(productoVenta.getPrecioVenta())));
        updateCostoValue();
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(insumoService.findAll()));
        getBean().setRuta_imagen_producto(productoVenta.getDescripcion());
        getBean().setCodigo_producto(productoVenta.getCodigoProducto());
        getBean().setCheckbox_producto_libre_impuestos(productoVenta.getLibreDeImpuestos());
        if (productoVenta.getComisionPorcientoPorVenta() != null) {
            getBean().setComision_por_venta_porcentual(productoVenta.getComisionPorcientoPorVenta());
        }
        if (creatingMode) {
            getBean().setCrear_editar_button_text("Crear");
        } else {
            getBean().setCrear_editar_button_text("Editar");
        }
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void onAddIngredienteClick() {
        throw new UnsupportedOperationException("En Desarrollo");
        //service.registrarNuevoInsumo();
        //getBean().getLista_insumos_disponibles().clear();
        //getBean().getLista_insumos_disponibles().addAll(insumoService.findAll());
    }

    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {

            String precioCosto = getBean().getPrecio_costo();
            String precioVenta = getBean().getPrecio_venta();
            String pagoPorVenta = getBean().getComision_por_venta();

            if (precioVenta == null || precioVenta.equals("")) {
                productoVenta.setPrecioVenta(0f);
            } else {
                productoVenta.setPrecioVenta(Float.valueOf(precioVenta));
            }
            if (pagoPorVenta == null || pagoPorVenta.isEmpty()) {
                productoVenta.setPagoPorVenta(0f);
            } else {
                productoVenta.setPagoPorVenta(Float.parseFloat(pagoPorVenta));
            }
            productoVenta.setNombre(getBean().getNombre_producto());
            productoVenta.setCocinacodCocina(getBean().getElaborado_seleccionado().getCodCocina());
            productoVenta.setSeccionnombreSeccion(getBean().getCategoria_seleccionada().getNombreSeccion());
            productoVenta.setProductoInsumoList(getBean().getLista_insumos_contenidos());
            productoVenta.setTiempoServicioMin(getBean().getTimepo_elaboracion());
            productoVenta.setDescripcion(getBean().getRuta_imagen_producto());
            productoVenta.setVisible(true);
            productoVenta.setComisionPorcientoPorVenta(getBean().getComision_por_venta_porcentual());
            productoVenta.setLibreDeImpuestos(getBean().isCheckbox_producto_libre_impuestos());
            if (creatingMode) {
                service.create(productoVenta);
            } else {
                service.edit(productoVenta);
            }
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
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
        List<Carta> list = cartaService.findAll();
        if (list.isEmpty()) {
            throw new IllegalStateException("No hay cartas creadas donde registrar la sección");
        }
        JComboBox<Carta> jComboBox1 = new JComboBox<>();
        Carta[] values = cartaService.findAll().toArray(new Carta[0]);
        jComboBox1.setModel(new DefaultComboBoxModel<>(values));
        jComboBox1.setSelectedItem(values[0]);
        Object[] options = {"Seleccionar", "Cancelar"};
        //                     yes        no       cancel
        int confirm = JOptionPane.showOptionDialog(
                null,
                jComboBox1,
                "Seleccione una carta donde registrar la sección",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.YES_NO_OPTION,
                MaterialIcons.CARD_GIFTCARD,
                options,
                options[0]);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre de la sección a crear",
                        "Nueva Sección", JOptionPane.QUESTION_MESSAGE);
                if (nombre != null) {
                    Seccion newSeccion = new Seccion();
                    newSeccion.setDescripcion("");
                    newSeccion.setNombreSeccion(nombre);
                    newSeccion.setProductoVentaList(new ArrayList<>());
                    newSeccion.setCartacodCarta(((Carta) jComboBox1.getSelectedItem()).getCodCarta());
                    cartaService.addSeccion(((Carta) jComboBox1.getSelectedItem()).getCodCarta(), newSeccion);
                    getBean().setLista_categorias(new ArrayListModel<>(seccionService.findAll()));
                }
                break;
            default:
                break;
        }

    }

    private void onAddElaboracionCLick() {
        String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre del Punto de Elaboracion a crear",
                "Nuevo Punto de Elaboracion", JOptionPane.QUESTION_MESSAGE);
        Cocina c = new Cocina();
        c.setNombreCocina(nombre);
        ptoElabService.create(c);
        getBean().setLista_elaborado(new ArrayListModel<>(ptoElabService.findAll()));
    }

    private void onAgregarInsumoFichaClick() {
        Float cantidad = new NumberPad().showView();
        if (cantidad != null) {
            Insumo inSel = getBean().getInsumo_disponible_sel();
            productoVenta.agregarInsumo(inSel, cantidad);
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
        productoVenta.eliminarInsumo(inSel);
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
        //ImageIcon image = imageService.loadImageIcon(path, new Dimension(70, 70));
        //getBean().setImagen_producto(image);
    }

    private void addListteners() {
        getBean().addPropertyChangeListener(PROP_RUTA_IMAGEN_PRODUCTO, (PropertyChangeEvent evt) -> {
            refreshProductImage();
        });

    }

    private void fillInsumoProductoInfo(ProductoVenta producto, ArrayListModel<Insumo> lista_productos_disponibles) {
        for (ProductoInsumo p : producto.getProductoInsumoList()) {
            var aux = new Insumo(p.getProductoInsumoPK().getInsumocodInsumo());
            p.setInsumo(lista_productos_disponibles.get(lista_productos_disponibles.indexOf(aux)));
            p.setProductoVenta(producto);
        }
    }
}
