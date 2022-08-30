/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.areaventa.presenter;

//import com.jobits.pos.controller.mesa.impl.MesaController;
import com.jobits.pos.controller.areaventa.AreaVentaService;
import com.jobits.pos.controller.areaventa.MesaService;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.core.domain.models.Area;
import com.jobits.pos.core.domain.models.Mesa;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class MesaDetailViewPresenter extends AbstractViewPresenter<MesaDetailViewModel> {

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";

    private final AreaVentaService areaService = PosDesktopUiModule.getInstance().getImplementation(AreaVentaService.class);
    private final MesaService service = PosDesktopUiModule.getInstance().getImplementation(MesaService.class);
    private final boolean creatingMode;
    private Mesa mesa;
    private Area area;

    public MesaDetailViewPresenter(Area area, Mesa mesa, boolean creatingMode) {
        super(new MesaDetailViewModel());
        this.creatingMode = creatingMode;
        this.mesa = mesa;
        this.area = area;
        refreshState();
    }

    @Override
    protected Optional refreshState() {
        getBean().setNombre(mesa.getNombre());
        getBean().setCapacidad(mesa.getCapacidadMax());
        getBean().setShow_cod_mesa(creatingMode);
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR) {
            @Override
            public Optional doAction() {
                onAceptarClick();
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
    }

    private void onAceptarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea continuar?", TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            if (creatingMode) {
                Mesa m = new Mesa("M-"+getBean().getCodigo());
                m.setAreacodArea(area);
                m.setNombre(getBean().getNombre());
                m.setCapacidadMax(getBean().getCapacidad());
                m.setEstado("vacia");
                m.setEstallena(false);
                m.setOrdenList(new ArrayList<>());
                areaService.addMesa(area.getCodArea(), m);
            } else {
                mesa.setNombre(getBean().getNombre());
                mesa.setCapacidadMax(getBean().getCapacidad());
                service.edit(mesa);
            }
            NavigationService.getInstance().navigateUp();
        }
    }

    private void onCancelarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Esta seguro que desea cancelar?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            NavigationService.getInstance().navigateUp();
        }
    }

}
