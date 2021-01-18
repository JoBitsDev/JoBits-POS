/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jobits.pos.reserva.core.usecase.CategoriaUseCase;
import com.jobits.pos.reserva.core.usecase.ReservaUseCase;
import com.jobits.pos.reserva.core.usecase.UbicacionUseCase;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.reserva.model.CategoriaWrapper;
import com.jobits.pos.ui.reserva.model.Category;
import com.jobits.pos.ui.reserva.model.ReservaWrapper;
import com.jobits.pos.ui.reserva.model.UbicacionWrapper;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ReservaSchedulerViewPresenter extends AbstractViewPresenter<ReservaSchedulerViewModel> {

    public static final String ACTION_NEXT = "Next";
    public static final String ACTION_BACK = "Back";
    public static final String PROP_SHOW_SCHEDULE = "Show Scheduler";

    UbicacionUseCase ubicacionUseCase = PosDesktopUiModule.getInstance().getImplementation(UbicacionUseCase.class);
    CategoriaUseCase categoriasUseCase = PosDesktopUiModule.getInstance().getImplementation(CategoriaUseCase.class);
    ReservaUseCase reservasUseCase = PosDesktopUiModule.getInstance().getImplementation(ReservaUseCase.class);

    private int amountToShow = 10;
    private int totalIndex = 0, currentIndex = 0;

    public ReservaSchedulerViewPresenter() {
        super(new ReservaSchedulerViewModel());
        setTotalIndex();
        refreshState();
        addListeners();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_NEXT) {
            @Override
            public Optional doAction() {
                onNextClick();
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_BACK) {
            @Override
            public Optional doAction() {
                onBackClick();
                return Optional.empty();
            }

        });
    }

    private void onNextClick() {
        currentIndex++;
        if (currentIndex > totalIndex) {
            currentIndex = 1;
        }
        getBean().setLista_ubicaciones(ubicacionConverter());
        getBean().setIndice_actual(String.valueOf(currentIndex));
        firePropertyChange(PROP_SHOW_SCHEDULE, null, null);
    }

    private void onBackClick() {
        currentIndex--;
        if (currentIndex <= 0) {
            currentIndex = totalIndex;
        }
        getBean().setLista_ubicaciones(ubicacionConverter());
        getBean().setIndice_actual(String.valueOf(currentIndex));
        firePropertyChange(PROP_SHOW_SCHEDULE, null, null);
    }

    @Override
    protected Optional refreshState() {
        getBean().setList_categorias(categoriaConverter());
        getBean().setLista_ubicaciones(ubicacionConverter());
        getBean().setLista_reservas(reservaConverter());
        return super.refreshState(); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Category> categoriaConverter() {
        List<Category> ret = new ArrayList<>();
        categoriasUseCase.findAll().forEach(categoria -> {
            ret.add(new CategoriaWrapper(categoria));
        });
        return ret;
    }

    private List<Resource> ubicacionConverter() {
        List<Resource> ret = new ArrayList<>();
        ubicacionUseCase.getUbicacaionesActivas(amountToShow, currentIndex).forEach(ubicacion -> {
            ret.add(new UbicacionWrapper(ubicacion));
        });
        return ret;
    }

    private List<Appointment> reservaConverter() {
        List<Appointment> ret = new ArrayList<>();
        reservasUseCase.getReservasDisponibles(getBean().getSelected_date()).forEach(reserva -> {
            ret.add(new ReservaWrapper(reserva,
                    new CategoriaWrapper(reserva.getCategoriaidcategoria()),
                    new UbicacionWrapper(reserva.getUbicacionidubicacion())));
        });
        return ret;
    }

    private void setTotalIndex() {
        int total = ubicacionUseCase.findAll().size();
        if (total != 0) {
            totalIndex = (int) Math.ceil((float) total / amountToShow);
            currentIndex = 1;
        }
        getBean().setIndice_actual(String.valueOf(currentIndex));
        getBean().setTotal_indices(String.valueOf(totalIndex));
        Date d = getBean().getDia_seleccionado();
        getBean().setSelected_date( LocalDate.of(d.getYear() + 1900, d.getMonth() + 1, d.getDate()));
    }

    private void addListeners() {
        addBeanPropertyChangeListener(ReservaSchedulerViewModel.PROP_DIA_SELECCIONADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                setTotalIndex();
                refreshState();
                firePropertyChange(PROP_SHOW_SCHEDULE, null, null);
            }
        });
    }

}
