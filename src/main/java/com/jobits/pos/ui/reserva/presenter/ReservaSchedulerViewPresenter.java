/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.reserva.presenter;

import com.jobits.pos.cordinator.DisplayType;
import com.jobits.pos.core.repo.impl.ConfiguracionDAO;
import com.jobits.pos.main.Application;
import com.jobits.pos.notification.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.reserva.core.domain.Reserva;
import com.jobits.pos.reserva.core.domain.ReservaEstado;
import com.jobits.pos.reserva.core.domain.Ubicacion;
import com.jobits.pos.reserva.core.usecase.CategoriaUseCase;
import com.jobits.pos.reserva.core.usecase.ReservaUseCase;
import com.jobits.pos.reserva.core.usecase.UbicacionUseCase;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import com.jobits.pos.ui.reserva.ReservasDetailView;
import com.jobits.pos.ui.reserva.model.CategoriaWrapper;
import com.jobits.pos.ui.reserva.model.Category;
import com.jobits.pos.ui.reserva.model.ReservaWrapper;
import com.jobits.pos.ui.reserva.model.UbicacionWrapper;
import com.jobits.ui.scheduler.Appointment;
import com.jobits.ui.scheduler.Resource;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public static final String ACTION_REFRESH = "Refresh";
    public static final String PROP_SHOW_SCHEDULE = "Show Scheduler";

    UbicacionUseCase ubicacionUseCase = PosDesktopUiModule.getInstance().getImplementation(UbicacionUseCase.class);
    CategoriaUseCase categoriasUseCase = PosDesktopUiModule.getInstance().getImplementation(CategoriaUseCase.class);
    ReservaUseCase reservasUseCase = PosDesktopUiModule.getInstance().getImplementation(ReservaUseCase.class);

    private final int amountToShow = 10;
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
        registerOperation(new AbstractViewAction(ACTION_REFRESH) {
            @Override
            public Optional doAction() {
                setTotalIndex();
                refreshState();
                return Optional.empty();
            }

        });
    }

    private void onNextClick() {
        currentIndex++;
        if (currentIndex > totalIndex) {
            currentIndex = 1;
        }
        getBean().setIndice_actual(String.valueOf(currentIndex));
        refreshState();
    }

    private void onBackClick() {
        currentIndex--;
        if (currentIndex <= 0) {
            currentIndex = totalIndex;
        }
        getBean().setIndice_actual(String.valueOf(currentIndex));
        refreshState();
    }

    @Override
    protected Optional refreshState() {
        Date d = getBean().getDia_seleccionado();
        getBean().setSelected_date(LocalDate.of(d.getYear() + 1900, d.getMonth() + 1, d.getDate()));
        getBean().setList_categorias(categoriaConverter());
        getBean().setLista_ubicaciones(ubicacionConverter());
        getBean().setLista_reservas(reservaConverter());
        firePropertyChange(PROP_SHOW_SCHEDULE, null, null);
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
        } else {
            totalIndex = 0;
            currentIndex = 0;
        }
        getBean().setIndice_actual(String.valueOf(currentIndex));
        getBean().setTotal_indices(String.valueOf(totalIndex));
    }

    private void addListeners() {
        addBeanPropertyChangeListener(ReservaSchedulerViewModel.PROP_DIA_SELECCIONADO, (PropertyChangeEvent evt) -> {
            if (evt.getNewValue() != null) {
                setTotalIndex();
                refreshState();
            }
        });
    }

    public void handleAddReserva(Resource resource, LocalDateTime time) {
        LocalTime inicio = ((UbicacionWrapper) resource).getUbicacion().getDisponibledesde(),
                cierre = ((UbicacionWrapper) resource).getUbicacion().getDisponiblehasta(),
                current = time.toLocalTime();
        if (current.isBefore(cierre) && current.isAfter(inicio)) {
            Ubicacion u = ((UbicacionWrapper) resource).getUbicacion();
            Reserva reserva = new Reserva(time.toLocalDate(), time.toLocalTime(), 30, u); // 6 pm
            reserva.setDuracionMinutos(ConfiguracionDAO.getInstance().find(R.SettingID.HORARIO_TIEMPO_MIN_SERVICIO).getValor());
            Application.getInstance().getNavigator().navigateTo(
                    ReservasDetailView.VIEW_NAME, new ReservaDetailViewPresenter(reserva, true), DisplayType.POPUP);
            refreshState();
            Application.getInstance().getNotificationService().notify("Reserva creada", TipoNotificacion.SUCCESS);
        }
    }

    public void handleEditReserva(Appointment appointment) {
        Reserva reserva = ((ReservaWrapper) appointment).getReserva();
        Application.getInstance().getNavigator().navigateTo(
                ReservasDetailView.VIEW_NAME, new ReservaDetailViewPresenter(reserva, false), DisplayType.POPUP);
        refreshState();
        Application.getInstance().getNotificationService().notify("Reserva editada", TipoNotificacion.SUCCESS);
    }

    public void handleChekInReserva(Appointment appointment) {
        Reserva reserva = ((ReservaWrapper) appointment).getReserva();
        if (reserva.getCheckin() != null) {
            throw new IllegalStateException("Ya se hizo CheckIn a la reserva seleccionada");
        } else {
            reservasUseCase.checkIn(reserva.getIdreserva(), LocalDateTime.of(reserva.getFechareserva(), reserva.getHorareserva()));
        }
        refreshState();
        Application.getInstance().getNotificationService().notify("Check In realizado", TipoNotificacion.SUCCESS);
    }

    public void handleChekOutReserva(Appointment appointment) {
        Reserva reserva = ((ReservaWrapper) appointment).getReserva();
        if (reserva.getCheckout() != null) {
            throw new IllegalStateException("Ya se hizo CheckOut a la reserva seleccionada");
        } else {
            reservasUseCase.checkOut(reserva.getIdreserva(), LocalDateTime.of(reserva.getFechareserva(), reserva.getHorareserva()));
        }
        refreshState();
        Application.getInstance().getNotificationService().notify("Check Out realizado", TipoNotificacion.SUCCESS);
    }

    public void handleCancelarReserva(Appointment appointment) {
        Reserva reserva = ((ReservaWrapper) appointment).getReserva();
        if (reserva.getEstado().equals(ReservaEstado.CANCELADA.toString())) {
            throw new IllegalStateException("Ya la reseserva fue cancelada");
        } else {
            reservasUseCase.cancelar(reserva.getIdreserva());
        }
        refreshState();
        Application.getInstance().getNotificationService().notify("Check Out realizado", TipoNotificacion.SUCCESS);
    }

}
