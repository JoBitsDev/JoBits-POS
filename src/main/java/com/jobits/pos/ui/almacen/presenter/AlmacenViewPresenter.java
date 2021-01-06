/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.almacen.AlmacenListController;
import com.jobits.pos.controller.almacen.AlmacenListService;
import com.jobits.pos.controller.almacen.AlmacenManageController;
import com.jobits.pos.controller.almacen.AlmacenManageService;
import com.jobits.pos.controller.almacen.TransaccionListController;
import com.jobits.pos.controller.insumo.InsumoDetailController;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.almacen.FacturaView;
import com.jobits.pos.ui.almacen.TransaccionListView;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.insumo.presenter.InsumoDetailViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Home
 */
public class AlmacenViewPresenter extends AbstractViewPresenter<AlmacenViewModel> {

    public static String ACTION_ACTUALIZAR_LISTA_ALMACEN = "Actulizar lista";
    public static String ACTION_ELIMINAR_INSUMO = "Eliminar Insumo";
    public static String ACTION_AGREGAR_INSUMO = "Agregr Insumo";

    public static final String ACTION_CREAR_ALMACEN = "Crear Almacen";
    public static final String ACTION_EDITAR_ALMACEN = "Editar Almacen";
    public static final String ACTION_ELIMINAR_ALMACEN = "Eliminar Almacen";

    public static final String ACTION_IMPRIMIR_RESUMEN = "Resumen";
    public static final String ACTION_IMPRIMIR_REPORTE = "Reporte";
    public static final String ACTION_TRANSACCIONES = "Transacciones";
    public static final String ACTION_MODIFICAR_STOCK = "Modificar Stock";
    public static final String ACTION_NUEVA_FACTURA = "Nueva Factura";

    AlmacenListService listService;
    AlmacenManageService detailService;

    public AlmacenViewPresenter(AlmacenListController listController) {
        super(new AlmacenViewModel());
        listService = listController;
        setListToBean();
        addListeners();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ACTUALIZAR_LISTA_ALMACEN) {
            @Override
            public Optional doAction() {
                detailService = new AlmacenManageController(getBean().getElemento_seleccionado());
                refreshView();
                getBean().setSearch_keyWord(null);
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CREAR_ALMACEN) {
            @Override
            public Optional doAction() {
                listService.createNewStorage();
                setListToBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EDITAR_ALMACEN) {
            @Override
            public Optional doAction() {
                listService.update(getBean().getElemento_seleccionado());
                setListToBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_ALMACEN) {
            @Override
            public Optional doAction() {
                listService.destroy(getBean().getElemento_seleccionado());
                setListToBean();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_INSUMO) {
            @Override
            public Optional doAction() {
                onAgregarInsumoFichaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_INSUMO) {
            @Override
            public Optional doAction() {
                onEliminarInsumoFichaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_RESUMEN) {
            @Override
            public Optional doAction() {
                detailService.imprimirResumenAlmacen(getBean().getElemento_seleccionado());
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_REPORTE) {
            @Override
            public Optional doAction() {
                detailService.imprimirReporteParaCompras(getBean().getElemento_seleccionado());
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TRANSACCIONES) {
            @Override
            public Optional doAction() {
                NavigationService.getInstance().navigateTo(TransaccionListView.VIEW_NAME,
                        new TransaccionListPresenter(
                                new TransaccionListController(
                                        detailService.getInstance())), DisplayType.POPUP);
                refreshView();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_MODIFICAR_STOCK) {
            @Override
            public Optional doAction() {
                if (getBean().getInsumo_contenido_seleccionado() != null) {
                    NavigationService.getInstance().navigateTo(InsumoDetailView.VIEW_NAME,
                            new InsumoDetailViewPresenter(
                                    new InsumoDetailController(
                                            getBean().getInsumo_contenido_seleccionado().getInsumo())), DisplayType.POPUP);
                    refreshView();
                } else {
                    JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un insumo primero");
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_NUEVA_FACTURA) {
            @Override
            public Optional doAction() {
                NavigationService.getInstance().navigateTo(FacturaView.VIEW_NAME,
                        new FacturaViewPresenter(detailService), DisplayType.POPUP);
                refreshView();
                return Optional.empty();
            }
        });
    }

    //ACTUALIZAR LA LISTA DE ALMACENES
    private void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(new ArrayListModel<>(listService.getItems()));
        if (!getBean().getLista_elementos().isEmpty()) {
            getBean().setElemento_seleccionado(getBean().getLista_elementos().get(0));
            if (getBean().getElemento_seleccionado() != null) {
                detailService = new AlmacenManageController(getBean().getElemento_seleccionado());
                refreshView();
                getBean().setPanel_visible(true);
            }
        } else {
            getBean().setPanel_visible(false);
        }
    }

    private void onAgregarInsumoFichaClick() {
        detailService.agregarInsumoAlmacen(getBean().getInsumo_disponible_seleccionado());
        refreshView();
    }

    private void onEliminarInsumoFichaClick() {
        detailService.removeInsumoFromStorage(getBean().getInsumo_contenido_seleccionado());
        refreshView();
    }

    private void refreshView() {
        if (getBean().getElemento_seleccionado() != null) {
            detailService.updateValorTotalAlmacen(getBean().getElemento_seleccionado());
            getBean().setValor_monetario_text(utils.setDosLugaresDecimalesDoubleString(detailService.getInstance().getValorMonetario()));
            getBean().setLista_insumos_contenidos(new ArrayListModel<>(detailService.getInsumoAlmacenList(detailService.getInstance())));
            getBean().setLista_insumos_disponibles(new ArrayListModel<>(detailService.getInsumoList()));
        }
    }

    private void addListeners() {
        addBeanPropertyChangeListener(AlmacenViewModel.PROP_SEARCH_KEYWORD, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                String keyWord = ((String) evt.getNewValue()).toLowerCase();
                List<InsumoAlmacen> insumoList = new ArrayList<>();
                List<InsumoAlmacen> temporalLIst = detailService.getInsumoAlmacenList(detailService.getInstance());
                if (evt.getNewValue().equals("")) {
                    getBean().setLista_insumos_contenidos(new ArrayListModel<>(temporalLIst));
                } else {
                    temporalLIst.stream().filter((insumoAlmacen)
                            -> (insumoAlmacen.getInsumo().getNombre().toLowerCase().contains(keyWord))).forEachOrdered((insumoAlmacen) -> {
                        insumoList.add(insumoAlmacen);
                    });
                    getBean().setLista_insumos_contenidos(new ArrayListModel<>(insumoList));
                }
            }
        });
        addBeanPropertyChangeListener(AlmacenViewModel.PROP_ELEMENTO_SELECCIONADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                detailService = new AlmacenManageController(getBean().getElemento_seleccionado());
                refreshView();
                getBean().setSearch_keyWord(null);
            }
        });

    }

}
