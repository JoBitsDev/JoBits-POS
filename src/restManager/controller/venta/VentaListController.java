/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.venta;

import GUI.Views.AbstractView;
import GUI.Views.venta.VentaCalendarView;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import restManager.controller.AbstractDialogController;
import restManager.controller.login.LogInController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.AsistenciaPersonal;
import restManager.persistencia.Venta;
import restManager.persistencia.models.VentaDAO;
import restManager.resources.R;

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

    public VentaListController(AbstractView parentView) {
        super(VentaDAO.getInstance());
        constructView(parentView);
    }

    public Venta createNewInstance() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void constructView(Container parent) {
        setView(new VentaCalendarView(this, (AbstractView) parent));
        getView().updateView();
        getView().setVisible(true);
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

    public void createDetailResumenView(Date del, Date al) {
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
                for (AsistenciaPersonal a : ve.getAsistenciaPersonalList()) { 
                    boolean founded = false;
                    for (AsistenciaPersonal b : v.getAsistenciaPersonalList()) {
                        if (b.getPersonal().getUsuario().equals(a.getPersonal().getUsuario())) {
                            founded = true;
                            b.setPago(a.getPago() + b.getPago());
                            if (b.getPropina() != null && a.getPropina() != null) {
                                b.setPropina(a.getPropina() + b.getPropina());
                            }
                            if (b.getAMayores() != null) {
                                b.setAMayores(a.getAMayores() + b.getAMayores());
                            }
                            break;
                        }
                    }
                    if (!founded) {
                        v.getAsistenciaPersonalList().add(a);
                    }
                }
            }

            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        if (initDateNotSet) {
            throw new ValidatingException(getView());
        }
        VentaDetailController controller = new VentaDetailController(v, getView(), al);

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

}
