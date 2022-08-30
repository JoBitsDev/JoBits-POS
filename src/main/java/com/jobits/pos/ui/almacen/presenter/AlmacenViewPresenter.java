/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.almacen.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.controller.insumo.InsumoService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.inventario.core.almacen.domain.Almacen;
import com.jobits.pos.inventario.core.almacen.domain.InsumoAlmacen;
import com.jobits.pos.inventario.core.almacen.usecase.AlmacenManageService;
import com.jobits.pos.inventario.core.almacen.usecase.TransaccionListService;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.RegularExpressions;
import com.jobits.pos.ui.almacen.FacturaView;
import com.jobits.pos.ui.almacen.PendingOperationsListView;
import com.jobits.pos.ui.almacen.TransaccionListView;
import com.jobits.pos.ui.insumo.InsumoDetailView;
import com.jobits.pos.ui.insumo.presenter.InsumoDetailViewPresenter;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.utils.utils;
import com.root101.clean.core.domain.services.ResourceHandler;
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
    public static final String ACTION_PENDIENTES = "Pendientes";

    AlmacenManageService detailService;
    InsumoService insumoService = PosDesktopUiModule.getInstance().getImplementation(InsumoService.class);

    public AlmacenViewPresenter(AlmacenManageService detailService) {
        super(new AlmacenViewModel());
        this.detailService = detailService;
        setListToBean();
        addListeners();
    }

    private void onActualizarListaAlmacen() {
//        detailService.setInstance(getBean().getElemento_seleccionado());
        refreshState();
        getBean().setSearch_keyWord("");
    }

    private void onCrearAlmacen() {
        String storageName = JOptionPane.showInputDialog(ResourceHandler.getString("dialogo_agregar_almacen"));
        if (storageName != null && !storageName.isBlank()) {
            if (storageName.matches(RegularExpressions.ONLY_WORDS_SEPARATED_WITH_SPACES)) {
                Almacen selected = new Almacen();
                selected.setInsumoAlmacenList(new ArrayList<>());
                selected.setCantidadInsumos(0);
                selected.setValorMonetario(0.0);
                selected.setNombre(storageName);
                detailService.create(selected);
                Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
                setListToBean();
            } else {
                throw new IllegalArgumentException("Nombre no permitido");
            }
        }
    }

    private void onEditarAlmacen() {
        detailService.edit(getBean().getElemento_seleccionado());
        setListToBean();
    }

    private void onEliminarAlmacen() {
        Almacen selected = getBean().getElemento_seleccionado();
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar: " + selected.getNombre(),
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            detailService.destroy(selected);
            Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
            setListToBean();
        }
    }

    private void onImprimirResumen() {
        String[] options = {"Impresora Regular", "Impresora Ticket", "Cancelar"};
        int selection = JOptionPane.showOptionDialog(null,
                ResourceHandler.getString("dialog_seleccionar_manera_imprimir"),
                ResourceHandler.getString("label_impresion"), JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        switch (selection) {
            case 0:
                firePropertyChange("IMPRIMIR_TABLA_ALMACEN", null, null);
                break;//impresion normal
            case 1:
                detailService.imprimirResumenAlmacen(getBean().getElemento_seleccionado().getCodAlmacen());
                break;//impresion ticket
            default:
                break;//cancelado
        }

    }

    private void onImprimirReporte() {
        String[] options = {"Impresora Regular", "Impresora Ticket", "Cancelar"};
        int selection = JOptionPane.showOptionDialog(null,
                ResourceHandler.getString("dialog_seleccionar_manera_imprimir"),
                ResourceHandler.getString("label_impresion"), JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        detailService.imprimirReporteParaCompras(getBean().getElemento_seleccionado().getCodAlmacen(), selection);
    }

    private void onTransacciones() {
        TransaccionListService transaccionService = PosDesktopUiModule.getInstance().getImplementation(TransaccionListService.class);
        NavigationService.getInstance().navigateTo(TransaccionListView.VIEW_NAME,
                new TransaccionListPresenter(transaccionService, getBean().getElemento_seleccionado()), DisplayType.POPUP);
        refreshState();
    }

    private void onModificarStock() {
        if (getBean().getInsumo_contenido_seleccionado() != null) {
            NavigationService.getInstance().navigateTo(InsumoDetailView.VIEW_NAME,
                    new InsumoDetailViewPresenter(
                            getBean().getInsumo_contenido_seleccionado().getInsumo()), DisplayType.POPUP);
            refreshState();
        } else {
            JOptionPane.showMessageDialog(Application.getInstance().getMainWindow(), "Seleccione un insumo primero");
        }
    }

    private void onNuevaFactura() {
        NavigationService.getInstance().navigateTo(FacturaView.VIEW_NAME,
                new FacturaViewPresenter(detailService, getBean().getElemento_seleccionado()), DisplayType.POPUP);
        refreshState();
    }

    private void onPendienteClick() {
        NavigationService.getInstance().navigateTo(PendingOperationsListView.VIEW_NAME,
                new OperacionesListPresenter(getBean().getElemento_seleccionado()), DisplayType.POPUP);
        refreshState();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ACTUALIZAR_LISTA_ALMACEN) {
            @Override
            public Optional doAction() {
                onActualizarListaAlmacen();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CREAR_ALMACEN) {
            @Override
            public Optional doAction() {
                onCrearAlmacen();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EDITAR_ALMACEN) {
            @Override
            public Optional doAction() {
                onEditarAlmacen();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_ALMACEN) {
            @Override
            public Optional doAction() {
                onEliminarAlmacen();
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
                onImprimirResumen();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_IMPRIMIR_REPORTE) {
            @Override
            public Optional doAction() {
                onImprimirReporte();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_TRANSACCIONES) {
            @Override
            public Optional doAction() {
                onTransacciones();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_MODIFICAR_STOCK) {
            @Override
            public Optional doAction() {
                onModificarStock();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_NUEVA_FACTURA) {
            @Override
            public Optional doAction() {
                onNuevaFactura();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_PENDIENTES) {
            @Override
            public Optional doAction() {
                onPendienteClick();
                return Optional.empty();
            }
        });
    }

    //ACTUALIZAR LA LISTA DE ALMACENES
    private void setListToBean() {
        getBean().getLista_elementos().clear();
        getBean().getLista_elementos().addAll(new ArrayListModel<>(detailService.findAll()));
        if (!getBean().getLista_elementos().isEmpty()) {
            getBean().setElemento_seleccionado(getBean().getLista_elementos().get(0));
            if (getBean().getElemento_seleccionado() != null) {
//                detailService.setInstance(getBean().getElemento_seleccionado());
                refreshState();
                getBean().setPanel_visible(true);
            }
        } else {
            getBean().setPanel_visible(false);
        }
    }

    private void onAgregarInsumoFichaClick() {
        detailService.agregarInsumoAlmacen(getBean().getInsumo_disponible_seleccionado().getCodInsumo(), getBean().getElemento_seleccionado().getCodAlmacen());
        Application.getInstance().getNotificationService().notify(ResourceHandler.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        refreshState();
    }

    private void onEliminarInsumoFichaClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea eliminar las existencias del insumo seleccionado del almacen",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            var codAlmcen = getBean().getInsumo_contenido_seleccionado().getInsumoAlmacenPK().getAlmacencodAlmacen();
            var codInsumo = getBean().getInsumo_contenido_seleccionado().getInsumoAlmacenPK().getInsumocodInsumo();
            detailService.removeInsumoFromStorage(codAlmcen,codInsumo);
            refreshState();
        }
    }

    @Override
    protected Optional refreshState() {
        if (getBean().getElemento_seleccionado() != null) {
            getBean().setElemento_seleccionado(detailService.findBy(getBean().getElemento_seleccionado().getCodAlmacen()));
            getBean().setValor_monetario_text(utils.setDosLugaresDecimalesDoubleString(getBean().getElemento_seleccionado().getValorMonetario()));
            getBean().setLista_insumos_contenidos(new ArrayListModel<>(getBean().getElemento_seleccionado().getInsumoAlmacenList()));
            getBean().getLista_insumos_disponibles().clear();
            getBean().getLista_insumos_disponibles().addAll(new ArrayListModel<>(insumoService.findAll()));
            getBean().setSearch_keyWord(getBean().getSearch_keyWord());
            onKeywordChange(getBean().getSearch_keyWord());
        }
        return Optional.empty();
    }

    private void addListeners() {
        addBeanPropertyChangeListener(AlmacenViewModel.PROP_SEARCH_KEYWORD, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                onKeywordChange(evt.getNewValue().toString());
            }
        });
        addBeanPropertyChangeListener(AlmacenViewModel.PROP_ELEMENTO_SELECCIONADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
//                detailService.setInstance(getBean().getElemento_seleccionado());
                refreshState();
                getBean().setSearch_keyWord(null);
            }
        });

    }

    private void onKeywordChange(String search_keyWord) {
        if (search_keyWord != null) {
            List<InsumoAlmacen> insumoList = new ArrayList<>();
            List<InsumoAlmacen> temporalLIst = getBean().getElemento_seleccionado().getInsumoAlmacenList();
            if (search_keyWord.equals("")) {
                getBean().setLista_insumos_contenidos(new ArrayListModel<>(temporalLIst));
            } else {
                temporalLIst.stream().filter((insumoAlmacen)
                        -> (insumoAlmacen.getInsumo().getNombre().toLowerCase().contains(search_keyWord))).forEachOrdered((insumoAlmacen) -> {
                    insumoList.add(insumoAlmacen);
                });
                getBean().setLista_insumos_contenidos(new ArrayListModel<>(insumoList));
            }
        }
    }

}
