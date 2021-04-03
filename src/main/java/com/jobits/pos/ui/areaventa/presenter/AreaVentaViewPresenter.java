/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

import com.jgoodies.common.collect.ArrayListModel;
//import com.jobits.pos.controller.areaventa.impl.AreaDetailController;
import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.controller.mesa.MesaService;
import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.areaventa.AreaDetailView;
import com.jobits.pos.ui.areaventa.MesaDetailView;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.Optional;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public class AreaVentaViewPresenter extends AbstractViewPresenter<AreaVentaViewModel> {

    private AreaVentaService areaService = PosDesktopUiModule.getInstance().getImplementation(AreaVentaService.class);

    private MesaService mesaService = PosDesktopUiModule.getInstance().getImplementation(MesaService.class);

    public static final String ACTION_AGREGAR_AREA = "Nueva area";
    public static final String ACTION_EDITAR_AREA = "Editar";
    public static final String ACTION_ELIMINAR_AREA = "Eliminar area";
    public static final String ACTION_AGREGAR_MESA = "Nueva mesa";
    public static final String ACTION_ELIMINAR_MESA = "Eliminar mesa";
    public static final String ACTION_EDITAR_MESA = "Editar mesa";

    public AreaVentaViewPresenter() {
        super(new AreaVentaViewModel());
        getBean().getLista_area().addAll(areaService.getItems());
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_AREA) {
            @Override
            public Optional doAction() {
                onNuevaAreaCLick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_AREA) {
            @Override
            public Optional doAction() {
                onEliminarAreaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_AGREGAR_MESA) {
            @Override
            public Optional doAction() {
                onNuevaMesaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_ELIMINAR_MESA) {
            @Override
            public Optional doAction() {
                onEliminarMesaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EDITAR_MESA) {
            @Override
            public Optional doAction() {
                onEditarMesaClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_EDITAR_AREA) {
            @Override
            public Optional doAction() {
                onEditarAreaClick();
                return Optional.empty();
            }
        });
    }

    @Override
    protected Optional refreshState() {
        Area areaSel = getBean().getArea_seleccionada();
        if (areaSel == null) {
            getBean().getLista_area().clear();
            getBean().getLista_area().addAll(new ArrayListModel<>(areaService.getItems()));
            getBean().getLista_mesas().clear();
        } else {
            getBean().setLista_area(new ArrayListModel<>(areaService.getItems()));
            getBean().setArea_seleccionada(areaSel);
        }
        return Optional.empty();
    }

    private void onNuevaAreaCLick() {//TODO: no actualiza correctamente nada en las vistas
        Application.getInstance().getNavigator().navigateTo(AreaDetailView.VIEW_NAME,
                new AreaDetailViewPresenter(null, true), DisplayType.POPUP);
        refreshState();
    }

    private void onEliminarAreaClick() {
        Area selected = getBean().getArea_seleccionada();
        if (selected == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una area", TipoNotificacion.ERROR);
            return;
        }
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar: " + selected,
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            areaService.destroy(selected);
            getBean().setArea_seleccionada(null);
            getBean().setLista_area(areaService.getItems());//TODO: cambiar el metodo create instance para agregar solamente el que se acaba de crear
        }

    }

    private void onEditarAreaClick() {
        if (getBean().getArea_seleccionada() == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una area", TipoNotificacion.ERROR);
            return;
        }
        Application.getInstance().getNavigator().navigateTo(
                AreaDetailView.VIEW_NAME,
                new AreaDetailViewPresenter(getBean().getArea_seleccionada(), false), DisplayType.POPUP);

        refreshState();
    }

    private void onEditarMesaClick() {
        if (getBean().getMesa_seleccionada() != null) {
            Application.getInstance().getNavigator().navigateTo(
                    MesaDetailView.VIEW_NAME,
                    new MesaDetailViewPresenter(null, getBean().getMesa_seleccionada(), false),
                    DisplayType.POPUP);
        } else {
            Application.getInstance().getNotificationService().
                    notify("Seleccione un Area", TipoNotificacion.ERROR);
        }
        getBean().getLista_mesas().clear();
        getBean().setArea_seleccionada(getBean().getArea_seleccionada());
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onNuevaMesaClick() {
        if (getBean().getArea_seleccionada() != null) {
            Application.getInstance().getNavigator().navigateTo(
                    MesaDetailView.VIEW_NAME,
                    new MesaDetailViewPresenter(getBean().getArea_seleccionada(), new Mesa(), true),
                    DisplayType.POPUP);
        } else {
            Application.getInstance().getNotificationService().
                    notify("Seleccione un Area", TipoNotificacion.ERROR);
        }
        getBean().getLista_mesas().clear();
        getBean().setArea_seleccionada(getBean().getArea_seleccionada());
        Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
    }

    private void onEliminarMesaClick() {
        Mesa selected = getBean().getMesa_seleccionada();
        if (selected == null) {
            Application.getInstance().getNotificationService().notify("Debe seleccionar una mesa", TipoNotificacion.ERROR);
            return;
        }
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea eliminar: " + selected,
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            mesaService.destroy(selected);
            getBean().setArea_seleccionada(getBean().getArea_seleccionada());
            Application.getInstance().getNotificationService().notify(R.RESOURCE_BUNDLE.getString("accion_realizada_correctamente"), TipoNotificacion.SUCCESS);
        }
    }

}
