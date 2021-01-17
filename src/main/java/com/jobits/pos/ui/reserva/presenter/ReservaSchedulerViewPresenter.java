/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jobits.pos.reserva.core.domain.Categoria;
import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.pos.reserva.core.module.ReservaCoreModule;
import com.jobits.pos.reserva.core.usecase.CategoriaUseCase;
import com.jobits.pos.reserva.core.usecase.ReservaUseCase;
import com.jobits.pos.reserva.core.usecase.UbicacionUseCase;
import com.jobits.pos.reserva.core.usecase.impl.UbicacionUseCaseImpl;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.reserva.model.CategoriaWrapper;
import com.jobits.pos.ui.reserva.model.Category;
import com.jobits.pos.ui.reserva.model.ReservaWrapper;
import com.jobits.pos.ui.reserva.model.UbicacionWrapper;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.awt.Color;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ReservaSchedulerViewPresenter extends AbstractViewPresenter<ReservaSchedulerViewModel> {

    UbicacionUseCase ubicacionUseCase = ReservaCoreModule.getInstance().getImplementation(UbicacionUseCase.class);
    CategoriaUseCase categoriasUseCase = ReservaCoreModule.getInstance().getImplementation(CategoriaUseCase.class);
    ReservaUseCase reservasUseCase = ReservaCoreModule.getInstance().getImplementation(ReservaUseCase.class);

    public ReservaSchedulerViewPresenter() {
        super(new ReservaSchedulerViewModel());
        initTestData();
        refreshState();
    }

    @Override
    protected void registerOperations() {

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
        ubicacionUseCase.findAll().forEach(ubicacion -> {
            ret.add(new UbicacionWrapper(ubicacion));
        });
        return ret;
    }

    private List<Appointment> reservaConverter() {
        List<Appointment> ret = new ArrayList<>();
        reservasUseCase.findAll().forEach(reserva -> {
            ret.add(new ReservaWrapper(reserva,
                    categoriaConverter().get(categoriaConverter().indexOf(new CategoriaWrapper(reserva.getCategoriaidcategoria()))),
                    ubicacionConverter().get(ubicacionConverter().indexOf(new UbicacionWrapper(reserva.getUbicacionidubicacion())))));
        });
        return ret;
    }

    private void initTestData() {
        ubicacionUseCase.create(new Ubicacion("Carlos", LocalTime.of(8, 0, 0), LocalTime.of(18, 0, 0)));
        ubicacionUseCase.create(new Ubicacion("ALberto", LocalTime.of(8, 0, 0), LocalTime.of(18, 0, 0)));
        ubicacionUseCase.create(new Ubicacion("Juan", LocalTime.of(8, 0, 0), LocalTime.of(18, 0, 0)));
    }

}
