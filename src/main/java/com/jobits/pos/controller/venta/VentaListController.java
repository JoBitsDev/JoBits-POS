/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.jobits.pos.controller.AbstractDialogController;
import com.jobits.pos.controller.login.LogInController;
import com.jobits.pos.exceptions.DevelopingOperationException;
import com.jobits.pos.exceptions.ValidatingException;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.adapters.repo.impl.VentaDAO;
import com.jobits.pos.controller.licencia.Licence;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.recursos.R;
import com.jobits.pos.utils.utils;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaListController extends AbstractDialogController<Venta>
        implements VentaListService {

    public VentaListController() {
        super(VentaDAO.getInstance());
    }

    public Venta createNewInstance() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
    }

    @Override
    public void destroy(Venta selected) {
        if (selected == null) {
            throw new IllegalArgumentException("Seleccione una venta");
        }
        if (showDeleteDialog((Container) getView(), selected) && new LogInController().constructoAuthorizationView(R.NivelAcceso.ADMINISTRADOR)) {
            this.selected = selected;
            this.destroy();
            this.selected = null;
            if (getView() != null) {
                getView().updateView();
            }
        }
    }

    public VentaDetailController createDetailResumenView(Date del, Date al) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, del.getYear());
        c.set(Calendar.MONTH, del.getMonth());
        c.set(Calendar.DAY_OF_MONTH, del.getDate());
        Venta v = new Venta();
        boolean initDateNotSet = true;
//        v.setFecha(selectedVentas.get(0).getFecha());
        v.setVentaTotal(0.0);
        v.setOrdenList(new ArrayList<>());
        v.setGastoVentaList(new ArrayList<>());
        v.setAsistenciaPersonalList(new ArrayList<>());
        v.setVentagastosEninsumos(0.0);
        v.setVentapropina((float) 0.0);
        Date current;

        while ((current = new Date(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH))).
                compareTo(al) <= 0) {
            Venta ve = getModel().find(current);
            if (ve != null) {
                v.getOrdenList().addAll(ve.getOrdenList());
                v.getGastoVentaList().addAll(ve.getGastoVentaList());
                if (ve.getVentaTotal() != null) {
                    v.setVentaTotal(v.getVentaTotal() + ve.getVentaTotal());
                }
                if (ve.getVentagastosEninsumos() != null) {
                    v.setVentagastosEninsumos(v.getVentagastosEninsumos() + ve.getVentagastosEninsumos());
                }
                if (ve.getVentapropina() != null) {
                    v.setVentapropina(v.getVentapropina() + ve.getVentapropina());
                }
                if (initDateNotSet) {
                    v.setFecha(current);
                    initDateNotSet = false;
                }

                if (ve.getAsistenciaPersonalList() != null) {
                    v.getAsistenciaPersonalList().addAll(ve.getAsistenciaPersonalList());
                }
            }

            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        if (initDateNotSet) {
            throw new ValidatingException(getView());
        }
        return new VentaDetailController();

    }

    /**
     *
     * @param month Mes tiene que ser del 1 - 12
     * @param year Anno natural ej: 2020
     * @return
     */
    @Override
    public List<List<Venta>> findVentas(int month, int year) {
        List<List<Venta>> ret = new ArrayList<>();
        LocalDate fecha = LocalDate.of(year, month, 1);
        while (fecha.getMonth().getValue() == month) {
            ret.add(VentaDAO.getInstance().find(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant())));
            fecha = fecha.plusDays(1);
        }
        return ret;
    }

    @Override
    public List<Venta> findVentasInRange(Calendar start, Calendar end) {
        ArrayList<Venta> ret = new ArrayList<>();

        getModel().findAll().stream().map((x) -> {
            int dia = x.getFecha().getDate();
            int mes = x.getFecha().getMonth();
            int anno = x.getFecha().getYear() + 1900;
            int startDia = start.get(Calendar.DAY_OF_MONTH);
            int startMes = start.get(Calendar.MONTH);
            int startAnno = start.get(Calendar.YEAR);
            int endDia = end.get(Calendar.DAY_OF_MONTH);
            int endMes = end.get(Calendar.MONTH);
            int endAnno = end.get(Calendar.YEAR);

            if ((dia >= startDia && mes >= startMes && anno >= startAnno)
                    && (dia <= endDia && mes <= endMes && anno <= endAnno)) {
                x.setFecha(utils.getZeroTimeDate(x.getFecha()));
                if (x.getVentaTotal() != null) {
                    x.setVentaTotal(utils.setDosLugaresDecimalesDouble(x.getVentaTotal()));
                }
                ret.add(x);
            }
            return x;
        }
        ).forEachOrdered(
                (Venta _item) -> {
                    Collections.sort(ret, (Venta o1, Venta o2) -> o1.getFecha().compareTo(o2.getFecha()));
                }
        );
        return ret;

    }

    @Override
    public List<Date> getFechaVentas(List<Venta> ventas) {
        List<Date> ret = new ArrayList<>();
        for (int i = 0; i < ventas.size(); i++) {
            ret.add(ventas.get(i).getFecha());
        }
        return ret;
    }

    @Override
    public List<Double> getTotalVentas(List<Venta> ventas) {
        List<Double> ret = new ArrayList<>();
        for (int i = 0; i < ventas.size(); i++) {
            ret.add(ventas.get(i).getVentaTotal());
        }
        return ret;
    }

    @Override
    public List<Float> getTotalGastos(List<Venta> ventas) {
        List<Float> ret = new ArrayList<>();
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getVentagastosGastos() == null) {
                ret.add((float) 0.0);
            } else {
                ret.add(ventas.get(i).getVentagastosGastos());
            }
        }
        return ret;
    }

    @Override
    public List<Integer> getTotalOrden(List<Venta> ventas) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getOrdenList() == null) {
                ret.add(0);
            } else {
                ret.add(ventas.get(i).getOrdenList().size());
            }
        }
        return ret;
    }

    @Override
    public boolean isYVisible() {
        LicenceController controller = new LicenceController(Licence.TipoLicencia.SECUNDARIA);
        return controller.getLicence().LICENCIA_ACTIVA && controller.getLicence().LICENCIA_VALIDA;

    }

}
