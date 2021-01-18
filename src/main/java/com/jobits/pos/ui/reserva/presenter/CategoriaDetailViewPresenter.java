/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.reserva.core.domain.Categoria;
import com.jobits.pos.reserva.core.domain.UbicacionEstado;
import com.jobits.pos.reserva.core.module.ReservaCoreModule;
import com.jobits.pos.reserva.core.usecase.CategoriaUseCase;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.root101.clean.core.domain.services.ResourceHandler;
import java.awt.Color;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class CategoriaDetailViewPresenter extends AbstractViewPresenter<CategoriaDetailVIewModel> {

    CategoriaUseCase categoriasUseCase = ReservaCoreModule.getInstance().getImplementation(CategoriaUseCase.class);

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";

    private static final Color[] Colors = new Color[]{
        new Color(Integer.parseInt(ResourceHandler.getString("com.jobits.pos.reserva.default_color"))),
        DefaultValues.PRIMARY_COLOR_DARK,
        DefaultValues.PRIMARY_COLOR,
        DefaultValues.PRIMARY_COLOR_LIGHT,
        DefaultValues.SECONDARY_DARK,
        DefaultValues.SECONDARY_COLOR,
        DefaultValues.SECONDARY_COLOR_LIGHT,
        new Color(51, 204, 255),
        new Color(55, 102, 205),
        new Color(204, 255, 51),
        new Color(251, 198, 12, 200),
        new Color(12, 251, 160, 200),
        new Color(166, 251, 12, 200),
        new Color(66, 151, 12, 200)
    };

    Categoria categoria;

    boolean creatingMode;

    public CategoriaDetailViewPresenter(Categoria categoria, boolean creatingMode) {
        super(new CategoriaDetailVIewModel());
        this.categoria = categoria;
        this.creatingMode = creatingMode;
        refreshState();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CANCELAR) {
            @Override
            public Optional doAction() {
                onCancelarClick();
                return Optional.empty();
            }

        });
        registerOperation(new AbstractViewAction(ACTION_ACEPTAR) {
            @Override
            public Optional doAction() {
                onAgregarClick();
                return Optional.empty();
            }

        });
    }

    @Override
    protected Optional refreshState() {
        getBean().setLista_colores(new ArrayListModel<>(Arrays.asList(Colors)));
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private void onCancelarClick() {
        NavigationService.getInstance().navigateUp();
    }

    private void onAgregarClick() {
        if ((boolean) Application.getInstance().getNotificationService().
                showDialog("Desea confirmar los cambios?",
                        TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
            categoria.setNombre(getBean().getNombre_categoria());
            categoria.setColor(getBean().getColor_seleccionado().getRGB());
            if (creatingMode) {
                categoriasUseCase.create(categoria);
            }else{
                categoriasUseCase.edit(categoria);
            }
            NavigationService.getInstance().navigateUp();
        }

    }

}
