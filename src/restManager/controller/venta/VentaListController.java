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
import java.util.Date;
import restManager.controller.AbstractDialogController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.Venta;
import restManager.persistencia.models.VentaDAO;
import restManager.util.LoadingWindow;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class VentaListController extends AbstractDialogController<Venta> {

    private int SELECCION = 0;

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
        getView().setVisible(true);
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
        v.setVentagastosEninsumos(0.0);
        Date current;

        Object[] selections = {"Turno Dia", "Turno Noche", "Ambos Turnos"};
        Object seleccion = showInputDialog(getView(), "Seleccione el turno para realizar estadisticas", "Seleccion", selections, selections[0]);
        switch (seleccion.toString()) {
            case "Turno Dia":
                SELECCION = 0;
                break;
            case "Turno Noche":
                SELECCION = 1;
                break;
            case "Ambos Turnos":
                SELECCION = 2;
                break;

        }
        while ((current = new Date(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH))).
                compareTo(al) <= 0) {
            Venta ve = getModel().find(current);
                if (initDateNotSet) {
                    v.setFecha(current);
                    initDateNotSet = false;
                }
            if (ve != null) {
                switch (SELECCION) {
                    case 0:
                        v.getOrdenList().add(ve.getOrdenList().get(0));
                        v.setVentaTotal(v.getVentaTotal() + ve.getOrdenList().get(0).getOrdenvalorMonetario());
                        v.setVentagastosEninsumos(v.getVentagastosEninsumos() + ve.getOrdenList().get(0).getOrdengastoEninsumos());
                        break;
                    case 1:
                        v.getOrdenList().add(ve.getOrdenList().get(1));
                        v.setVentaTotal(v.getVentaTotal() + ve.getOrdenList().get(1).getOrdenvalorMonetario());
                        v.setVentagastosEninsumos(v.getVentagastosEninsumos() + ve.getOrdenList().get(1).getOrdengastoEninsumos());
                        break;
                    case 2:
                        v.getOrdenList().addAll(ve.getOrdenList());
                        v.setVentaTotal(v.getVentaTotal() + ve.getVentaTotal());
                        v.setVentagastosEninsumos(v.getVentagastosEninsumos() + ve.getVentagastosEninsumos());
                        break;
                }
            }
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        if (initDateNotSet) {
            throw new ValidatingException(getView());
        }
        VentaDetailController controller = new VentaDetailController(v, getView(), al);

    }

}
