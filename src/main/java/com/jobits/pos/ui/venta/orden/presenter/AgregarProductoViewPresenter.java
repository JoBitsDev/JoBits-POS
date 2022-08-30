/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.seccion.SeccionListService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.SeccionAgregada;
import com.jobits.pos.core.domain.models.temporal.ProductoVentaWrapper;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.app.services.utils.TipoNotificacion;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.jobits.pos.ui.venta.orden.presenter.AgregarProductoViewModel.PROP_PRODUCTO_DISPONIBLE_SELECCIONADO;

/**
 * @author Home
 */
public class AgregarProductoViewPresenter extends AbstractViewPresenter<AgregarProductoViewModel> {

    public static String ACTION_AGREGAR = "Agregar";
    public static String ACTION_CANCELAR = "Cancelar";
    public static String ACTION_AGREGAR_AGREGO = "Agregar Agrego";
    public static String ACTION_ELIMINAR_AGREGO = "Eliminar Agrego";
    public static String ACTION_SECCION_SIGUIENTE = "Seccion Siguiente";
    public static String ACTION_SECCION_ANTERIOR = "Seccion Anterior";

    private final String codOrden;
    private final ProductoVenta producto;
    private final float cantidad;
    OrdenService service = PosDesktopUiModule.getInstance().getImplementation(OrdenService.class);
    SeccionListService seccionService = PosDesktopUiModule.getInstance().getImplementation(SeccionListService.class);
    private int currentIndex = 0;
    private List<SeccionAgregada> list_secciones_agregadas = new ArrayList();

    public AgregarProductoViewPresenter(String codOrden, ProductoVenta producto, float cantidad) {
        super(new AgregarProductoViewModel());
        this.codOrden = codOrden;
        this.producto = producto;
        this.cantidad = cantidad;
        initListeners();
        refreshState();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR) {
            @Override
            public Optional doAction() {
                onAgregarAction();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarAction();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_AGREGO) {
            @Override
            public Optional doAction() {
                onAgregarAgregoAction();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_AGREGO) {
            @Override
            public Optional doAction() {
                onEliminarAgregoAction();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_SECCION_SIGUIENTE) {
            @Override
            public Optional doAction() {
                onSeccionSiguienteClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_SECCION_ANTERIOR) {
            @Override
            public Optional doAction() {
                onSeccionAnteriorClick();
                return Optional.empty();
            }
        });

    }

    @Override
    protected Optional refreshState() {
        list_secciones_agregadas = seccionService.findBy(producto.getSeccionnombreSeccion()).getAgregadoEn();
        currentIndex = 0;
        setProductosFromSeccion();
        getBean().setNavegacion_habilitada(list_secciones_agregadas.size() > 1);
        getBean().setNombre_producto(utils.setDosLugaresDecimalesFloat(cantidad) + " x " + producto.getNombre());
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void onAgregarAction() {
        service.addProductoCompuesto(codOrden, producto.getCodigoProducto(), cantidad, getBean().getLista_productos_contenidos());
        Application.getInstance().getNavigator().navigateUp();
    }

    private void onCancelarAction() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea cancelar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            Application.getInstance().getNavigator().navigateUp();
        }
    }

    private void onAgregarAgregoAction() {
        ProductoVenta p = getBean().getProducto_disponible_seleccionado();
        Float cant = new NumberPad().showView();
        if (cant != null && p != null) {
            ProductoVentaWrapper wrapper = new ProductoVentaWrapper(p, cant);
            getBean().getLista_productos_contenidos().add(wrapper);
        }
    }

    private void onEliminarAgregoAction() {
        if (getBean().getProducto_contenido_seleccionado() == null) {
            throw new IllegalStateException("Seleccione un producto primero");
        }
        getBean().getLista_productos_contenidos().remove(getBean().getProducto_contenido_seleccionado());
    }

    private void initListeners() {
        getBean().addPropertyChangeListener(PROP_PRODUCTO_DISPONIBLE_SELECCIONADO, (PropertyChangeEvent evt) -> {
            ProductoVenta p = (ProductoVenta) evt.getNewValue();
            if (p != null) {
                Float cantidad1 = new NumberPad().showView();
                if (cantidad1 != null) {
                    ProductoVentaWrapper wrapper = new ProductoVentaWrapper(p, cantidad1);
                    getBean().getLista_productos_contenidos().add(wrapper);
                }
            }
        });
    }

    private void onSeccionAnteriorClick() {
        if (currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = list_secciones_agregadas.size() - 1;
        }
        setProductosFromSeccion();
    }

    private void onSeccionSiguienteClick() {
        if (currentIndex < list_secciones_agregadas.size() - 1) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
        setProductosFromSeccion();
    }

    private void setProductosFromSeccion() {
        SeccionAgregada seccion = list_secciones_agregadas.get(currentIndex);
        getBean().setNombre_seccion_mostrada(seccion.getSeccion().getNombreSeccion());
        getBean().setLista_productos_disponibles(
                new ArrayListModel<>(seccion.getSeccion().getProductoVentaList()));
        getBean().setProducto_disponible_seleccionado(null);
    }
}
