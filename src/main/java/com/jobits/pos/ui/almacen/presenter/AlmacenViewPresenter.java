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
import com.jobits.pos.controller.almacen.TransaccionesListController;
import com.jobits.pos.controller.insumo.InsumoDetailController;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.ui.almacen.FacturaView;
import com.jobits.pos.ui.almacen.TransaccionListView;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.insumo.presenter.InsumoDetailViewPresenter;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.utils.utils;
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
    AlmacenManageController detailService;

    public AlmacenViewPresenter(AlmacenListController listController) {
        super(new AlmacenViewModel());
        listService = listController;
        setListToBean();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ACTUALIZAR_LISTA_ALMACEN) {
            @Override
            public Optional doAction() {
                detailService = new AlmacenManageController(getBean().getElemento_seleccionado());
                fillDetailList();
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
                                new TransaccionesListController(
                                        detailService.getInstance())), DisplayType.POPUP);
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
                return Optional.empty();
            }
        });
    }

    //ACTUALIZAR LA LISTA DE ALMACENES
    private void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(new ArrayListModel<>(listService.getItems()));
        getBean().setElemento_seleccionado(listService.getItems().get(0));
        detailService = new AlmacenManageController(getBean().getElemento_seleccionado());
        fillDetailList();
    }

    private void onAgregarInsumoFichaClick() {
          throw new UnsupportedOperationException("Operacion no disponible"); //To change body of generated methods, choose Tools | Templates.
    }

    private void onEliminarInsumoFichaClick() {
          throw new UnsupportedOperationException("Operacion no disponible"); //To change body of generated methods, choose Tools | Templates.
    }

    private void fillDetailList() {
        getBean().setValor_monetario_text(utils.setDosLugaresDecimales(detailService.getInstance().getValorMonetario()));

        getBean().getLista_insumos_contenidos().clear();
        getBean().getLista_insumos_contenidos().addAll(new ArrayListModel<>(detailService.getInsumoAlmacenList(detailService.getInstance())));
        getBean().getLista_insumos_disponibles().clear();
        getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(detailService.getInsumoList()));
    }

}
