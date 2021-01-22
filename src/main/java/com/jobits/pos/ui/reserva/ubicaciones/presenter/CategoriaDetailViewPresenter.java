/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.ubicaciones.presenter;

import com.jgoodies.common.collect.ArrayListModel;
import com.jobits.pos.cordinator.NavigationService;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.reserva.core.domain.Categoria;
import com.jobits.pos.reserva.core.usecase.CategoriaUseCase;
import com.jobits.pos.ui.DefaultValues;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class CategoriaDetailViewPresenter extends AbstractViewPresenter<CategoriaDetailVIewModel> {

    CategoriaUseCase categoriasUseCase = PosDesktopUiModule.getInstance().getImplementation(CategoriaUseCase.class);

    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_ACEPTAR = "Aceptar";

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
        getBean().setLista_colores(new ArrayListModel<>(colorsToRGB()));
        getBean().setColor_seleccionado(categoria.getColor());
        getBean().setNombre_categoria(categoria.getNombre());
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
            categoria.setColor(getBean().getColor_seleccionado());
            if (creatingMode) {
                categoriasUseCase.create(categoria);
            } else {
                categoriasUseCase.edit(categoria);
            }
            NavigationService.getInstance().navigateUp();
        }

    }

    private List<Integer> colorsToRGB() {
        List<Integer> ret = new ArrayList<>();
        for (Color x : DefaultValues.DEFAULT_COLOR_PALETE) {
            ret.add(x.getRGB());
        }
        return ret;
    }

}
