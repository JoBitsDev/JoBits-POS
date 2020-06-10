/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.venta;

import com.jobits.pos.ui.OldAbstractView;
import com.jobits.pos.ui.venta.VentaCalendarView;
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
import com.jobits.pos.domain.models.AsistenciaPersonal;
import com.jobits.pos.domain.models.Venta;
import com.jobits.pos.adapters.repo.VentaDAO;
import com.jobits.pos.controller.licencia.Licence;
import com.jobits.pos.controller.licencia.LicenceController;
import com.jobits.pos.recursos.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaListController extends AbstractDialogController<Venta> {

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
        if (showDeleteDialog((Container) getView(), selected) && new LogInController().constructoAuthorizationView(getView(), R.NivelAcceso.ADMINISTRADOR)) {
            this.selected = selected;
            this.destroy();
            this.selected = null;
            if (getView() != null) {
                getView().updateView();
            }
            showSuccessDialog(getView());
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
        return new VentaDetailController(v, al);

    }

    public List<Venta> findVentas(int month, int year) {
        ArrayList<Venta> ret = new ArrayList<>();
        getModel().findAll().stream().map((x) -> {
            if (x.getFecha().getMonth() == month && x.getFecha().getYear() + 1900 == year) {
                ret.add(x);
            }
            return x;
        }).forEachOrdered((Venta _item) -> {
            Collections.sort(ret, (Venta o1, Venta o2) -> o1.getFecha().compareTo(o2.getFecha()));
        });
        return ret;
    }

    public boolean isYVisible() {
        LicenceController controller = new LicenceController(Licence.TipoLicencia.SECUNDARIA);
        return controller.getLicence().LICENCIA_ACTIVA && controller.getLicence().LICENCIA_VALIDA;

    }

}
