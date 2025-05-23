/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.venta.orden.presenter;

import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.controller.venta.OrdenService;
import com.jobits.pos.controller.venta.VentaDetailService;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.core.domain.models.Orden;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.venta.mesas.presenter.MesaListViewPresenter;
import com.root101.swing.material.standards.MaterialIcons;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;

/**
 * JoBits
 *
 * @author Jorge
 */
public class VentaOrdenListViewPresenter extends AbstractViewPresenter<VentaOrdenListViewModel>
        implements PropertyChangeListener {

    public static final String ACTION_CREAR_ORDEN = "Nueva Orden";
    public static final String ACTION_ABRIR_ORDEN = "Abrir Orden";
    public static final String ACTION_IMPRIMIR_LISTA_ORDENES = "Imprimir Ordenes";
    int codVenta;
    private VentaDetailService ventaService;
    private MesaService mesaService = PosDesktopUiModule.getInstance().getImplementation(MesaService.class);
    private OrdenDetailViewPresenter ordenPresenter;
    private ProductoVentaSelectorPresenter menuPresenter;
    private OrdenService ordenService;

    private VentaOrdenListViewPresenter(VentaDetailService ventaService) {
        super(new VentaOrdenListViewModel());
        this.ventaService = ventaService;
    }

    public VentaOrdenListViewPresenter(VentaDetailService ventaService,
                                       OrdenService ordenService, int codVenta) {
        this(ventaService);
        this.ordenService = ordenService;
        this.codVenta = codVenta;
        ordenPresenter = new OrdenDetailViewPresenter(this.ordenService);
        menuPresenter = new ProductoVentaSelectorPresenter(this.ordenService);
        menuPresenter.addPropertyChangeListener(ProductoVentaSelectorPresenter.PROP_PRODUCTO_SELECCIONADO, (PropertyChangeEvent evt) -> {
            ordenPresenter.getOperation(ACTION_REFRESH_STATE).doAction();
        });
        ordenPresenter.addBeanPropertyChangeListener(OrdenDetailViewModel.PROP_MODO_AGREGO_ACTIVADO, (PropertyChangeEvent evt) -> {
            if ((boolean) evt.getNewValue()) {
                menuPresenter.getBean().setProductoAgregar(ordenPresenter.getBean().getProducto_orden_seleccionado());
                menuPresenter.showSeccionesAgregadas();
                menuPresenter.onMostrarSeccionClick();
            } else {
                menuPresenter.getBean().setProductoAgregar(null);
                menuPresenter.getOperation(ACTION_REFRESH_STATE).doAction();
            }
        });
        ordenPresenter.addPropertyChangeListener(OrdenDetailViewPresenter.PROP_CHANGES, (PropertyChangeEvent evt) -> {
            refreshState();
        });
        addBeanPropertyChangeListener(VentaOrdenListViewModel.PROP_ELEMENTO_SELECCIONADO, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getNewValue() != null) {
                    onAbrirOrdenAction();
                    menuPresenter.onMostrarSeccionClick();
                }
            }
        });
        refreshState();
    }

    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
        refreshState();
    }

    public OrdenDetailViewPresenter getOrdenPresenter() {
        return ordenPresenter;
    }

    public ProductoVentaSelectorPresenter getMenuPresenter() {
        return menuPresenter;
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ABRIR_ORDEN) {
            @Override
            public Optional doAction() {
                onAbrirOrdenAction();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_CREAR_ORDEN) {
            @Override
            public Optional doAction() {
                onCrearOrdenAction();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_LISTA_ORDENES) {
            @Override
            public Optional doAction() {
                onImprimirOrdenesClick();
                return Optional.empty();
            }
        });
    }

    private void onCrearOrdenAction() {
        Mesa m = selectMesaNew();
        Orden newOrden = null;
        if (m != null) {
            newOrden = ventaService.createNewOrden(codVenta, m.getCodMesa());
        }
        if (newOrden != null) {
            getBean().setElemento_seleccionado(newOrden);
            onAbrirOrdenAction();
            refreshState();
        }
    }

    private void onAbrirOrdenAction() {
        if (getBean().getElemento_seleccionado() != null) {
            ordenPresenter.setCodOrden(getBean().getElemento_seleccionado().getCodOrden());
            menuPresenter.setCodMesaSeleccionada(getBean().getElemento_seleccionado().getMesacodMesa());
            menuPresenter.setCodOrdenEnlazada(getBean().getElemento_seleccionado().getCodOrden());
        }
    }

    private void onImprimirOrdenesClick() {
        throw new UnsupportedOperationException("Operacion deshabilitada permanentemente");
    }

    @Override
    protected Optional refreshState() {
        Orden o = getBean().getElemento_seleccionado();
        List<Orden> lista = ventaService.getOrdenesActivas(codVenta);
        getBean().setLista_elementos(lista);
        getBean().setElemento_seleccionado(o);

        ordenPresenter.getOperation(ACTION_REFRESH_STATE).doAction();
        menuPresenter.getOperation(ACTION_REFRESH_STATE).doAction();
        return Optional.empty();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshState();
    }

    private Mesa selectMesa(List<Mesa> list) {
        JComboBox<Mesa> jComboBox1 = new JComboBox<>();
        jComboBox1.setModel(new DefaultComboBoxModel<>(list.toArray(new Mesa[list.size()])));
        jComboBox1.setSelectedItem(list.get(0));
        Object[] options = {"Seleccionar", "Cancelar"};
        //                     yes            cancel
        int confirm = JOptionPane.showOptionDialog(
                null,
                jComboBox1,
                "Seleccione una Mesa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.YES_NO_OPTION,
                MaterialIcons.LOCATION_ON,
                options,
                options[0]);
        switch (confirm) {
            case JOptionPane.YES_OPTION:
                return (Mesa) jComboBox1.getSelectedItem();
            default:
                return null;
        }
    }

    private Mesa selectMesaNew() {
        var aux = new MesaListViewPresenter();
        return aux.selectMesa();
    }
}
