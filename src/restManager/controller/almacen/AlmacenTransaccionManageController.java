/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.Almacen.AlmacenTransaccionEditView;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import restManager.controller.AbstractDetailController;
import restManager.persistencia.Insumo;
import restManager.persistencia.Transaccion;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.TransaccionDAO;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
class AlmacenTransaccionManageController extends AbstractDetailController<Transaccion> {

    public AlmacenTransaccionManageController() {
        super(new TransaccionDAO());
    }

    public AlmacenTransaccionManageController(Transaccion instance) {
        super(instance,new TransaccionDAO());
    }

    public AlmacenTransaccionManageController(Window parent) {
        super(parent, new TransaccionDAO());
    }

    public AlmacenTransaccionManageController(Transaccion instance, Window parent) {
        super(instance, parent, new TransaccionDAO());
    }

    @Override
    public void constructView(Window parent) {
        ArrayList<Insumo> insumoList = new ArrayList<>(new InsumoDAO().findAll());
        insumoList.sort((Insumo o1, Insumo o2) -> o1.getNombre().compareTo(o2.getNombre()));

        if (parent instanceof JDialog) {
            setView(new AlmacenTransaccionEditView(instance, insumoList, this, (JDialog) parent, true));
        } else {
            setView(new AlmacenTransaccionEditView(instance, insumoList, this, (Frame) parent, true));
        }
        getView().updateView();
        getView().setVisible(true);
    }

    @Override
    public void create(Transaccion selected) {
        if (validateData(selected)) {
            if (showConfirmDialog(null)) {
                this.selected = selected;
                this.selected.getAlmacencodAlmacen().
                        setValorMonetario(selected.getAlmacencodAlmacen().getValorMonetario() + selected.getValorTotalTransacciones());
                create();
                this.selected = null;
                showSuccessDialog(null);
                getView().dispose();
                setView(null);
            }

        }
    }

    private boolean validateData(Transaccion trans) {
        for (int i = 0; i < trans.getInsumoTransaccionList().size() - 1; i++) {
            String transType = trans.getInsumoTransaccionList().get(i).getTipoTransaccion();
            String nombre = trans.getInsumoTransaccionList().get(i).getInsumo().getNombre();
            for (int j = i + 1; j < trans.getInsumoTransaccionList().size(); j++) {
                if (transType.equals(trans.getInsumoTransaccionList().get(j).getTipoTransaccion())
                        && nombre.equals(trans.getInsumoTransaccionList().get(j).getInsumo().getNombre())) {
                    showErrorDialog(getView(), R.RESOURCE_BUNDLE.getString("error_duplicacion") + "(" + nombre + ")");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Transaccion createNewInstance() {
        Transaccion trans = new Transaccion();
        trans.setFechaTransaccion(new Date());
        trans.setValorTotalTransacciones(Float.parseFloat("0"));
        trans.setValorMerma(Float.parseFloat("0"));
      //  f.setAlmacencodAlmacen(a);
        trans.setInsumoTransaccionList(new ArrayList<>());
        return trans;
    }

}
