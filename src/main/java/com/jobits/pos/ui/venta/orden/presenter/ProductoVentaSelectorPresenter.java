/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.core.repo.impl.SeccionDAO;
import com.jobits.pos.controller.productos.ProductoVentaListService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.ProductoVenta;
import com.jobits.pos.core.domain.models.Seccion;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.NumberPad;
import static com.jobits.pos.ui.venta.orden.presenter.VentaOrdenListViewPresenter.ACTION_ABRIR_ORDEN;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class ProductoVentaSelectorPresenter extends AbstractViewPresenter<ProductoVentaSelectorViewModel> {

    private OrdenService service;
    private Mesa mesaSeleccionada;
    private String codOrdenEnlazada;

    public static final String ACTION_MOSTRAR_SECCION = "Mostrar Seccion";
    public static final String ACTION_OCULTAR_SECCION = "Ocultar Seccion";

    public static final String PROP_PRODUCTO_SELECCIONADO = "PROP_PRODUCTO_SELECCIONADO";
    public static final String PROP_MOSTRAR_SECCIONES = "Mostrar Secciones";

    public ProductoVentaSelectorPresenter(OrdenService ordenService) {
        super(new ProductoVentaSelectorViewModel());
        this.service = ordenService;
        addBeanPropertyChangeListener(ProductoVentaSelectorViewModel.PROP_ELEMENTO_SELECCIONADO, (PropertyChangeEvent evt) -> {
            Seccion seccion = (Seccion) evt.getNewValue();
            if (seccion != null) {
                getBean().setListaProductos(seccion.getProductoVentaList());
            } else {
                getBean().setListaProductos(new ArrayList());
            }
        });
        addBeanPropertyChangeListener(ProductoVentaSelectorViewModel.PROP_PRODUCTOVENTASELECCIONADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null && codOrdenEnlazada != null) {
                Float cantidad = new NumberPad(null).showView();
                if (cantidad != null) {
                    service.addProduct(codOrdenEnlazada, (ProductoVenta) evt.getNewValue(), cantidad);
                    getBean().setProductoVentaSeleccionado(null);
                    firePropertyChange(PROP_PRODUCTO_SELECCIONADO, null, null);
                }
//                addBeanPropertyChangeListener(PROPERTY_BEAN, new PropertyChangeListener() {
//                    @Override
//                    public void propertyChange(PropertyChangeEvent evt) {
//                        firePropertyChange(evt);
//                    }
//                });
            }
        });
        addBeanPropertyChangeListener(ProductoVentaSelectorViewModel.PROP_PV_FILTRADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                List<ProductoVenta> pvList = new ArrayList<>();
                List<Seccion> secList = new ArrayList<>();
                List<Seccion> auxList = SeccionDAO.getInstance().findVisibleSecciones(mesaSeleccionada);

                auxList.forEach((x) -> {
                    boolean add = false;
                    for (ProductoVenta pv : x.getProductoVentaList()) {
                        if (pv.toString().toLowerCase().contains(getBean().getPv_filtrado().toLowerCase())) {
                            pvList.add(pv);
                            add = true;
                        }
                    }
                    if (add) {
                        secList.add(x);
                    }
                });
                getBean().setLista_elementos(secList);
                if (evt.getNewValue().equals("")) {
                    getBean().setListaProductos(new ArrayList<>());
                } else {
                    getBean().setListaProductos(pvList);
                }
            } else {
                getBean().setElemento_seleccionado(null);
                getBean().setListaProductos(new ArrayList<>());
            }
        });
        refreshState();
    }

    public Mesa getMesaSeleccionada() {
        return mesaSeleccionada;
    }

    public void setMesaSeleccionada(Mesa mesaSeleccionada) {
        this.mesaSeleccionada = mesaSeleccionada;
        getBean().setCampo_busqueda_enabled(false);
        getBean().setCampo_busqueda_enabled(mesaSeleccionada != null);
//        getBean().getListaProductos().clear();
        refreshState();
    }

    public String getCodOrdenEnlazada() {
        return codOrdenEnlazada;
    }

    public void setCodOrdenEnlazada(String codOrdenEnlazada) {
        this.codOrdenEnlazada = codOrdenEnlazada;
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_MOSTRAR_SECCION) {
            @Override
            public Optional doAction() {
                onMostrarSeccionClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_OCULTAR_SECCION) {
            @Override
            public Optional doAction() {
                onOcultarSeccionClick();
                return Optional.empty();
            }
        });

    }

    public void onMostrarSeccionClick() {
        firePropertyChange(PROP_MOSTRAR_SECCIONES, false, true);
    }

    public void onOcultarSeccionClick() {
        firePropertyChange(PROP_MOSTRAR_SECCIONES, true, false);
    }

    @Override
    public Optional refreshState() {
        if (mesaSeleccionada != null) {
            getBean().setLista_elementos(SeccionDAO.getInstance().findVisibleSecciones(mesaSeleccionada));//TODO: pifia logica en los presenters
            if (!getBean().getListaProductos().isEmpty()) {
                getBean().setElemento_seleccionado(getBean().getLista_elementos().get(0));
            }
            if (getBean().getElemento_seleccionado() != null) {
                getBean().setListaProductos(getBean().getElemento_seleccionado().getProductoVentaList());
            } else {
                getBean().setListaProductos(new ArrayList());
            }
            onMostrarSeccionClick();
        }
        return Optional.empty();
    }

    public void showSeccionesAgregadas() {
        List<Seccion> listaSecciones = SeccionDAO.getInstance().findVisibleSecciones(mesaSeleccionada);
        List<Seccion> aux = new ArrayList();
        for (Seccion x : listaSecciones) {
            if (!x.getAgregos().isEmpty()) {
                aux.add(x);
            }
        }
        getBean().setLista_elementos(aux);//TODO: pifia logica en los presenters
    }

}
