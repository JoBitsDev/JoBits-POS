/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.insumo;

import GUI.Views.Insumo.InsumoCreateEditView;
import GUI.Views.Insumo.InsumoListView;
import java.awt.Frame;
import javax.swing.JOptionPane;
import restManager.controller.AbstractController;
import restManager.persistencia.Insumo;
import restManager.persistencia.beans.AlmacenDAO;
import restManager.persistencia.beans.InsumoDAO;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoListController extends AbstractController<Insumo> {

    private final InsumoListView view;
    private final String PREFIX_FOR_ID = "In-";
    private InsumoCreateEditView newInsumoView;

    public InsumoListController(Frame frame) {
        super(new InsumoDAO());
        view = new InsumoListView(this, frame, true);
        view.updateView(super.getItems());
        view.setVisible(true);
    }

    public void popupInsumoNewView() {
        selected = new Insumo(super.getFacade().generateStringCode(PREFIX_FOR_ID));
        selected.setNombre("");
        selected.setElaborado(false);
        selected.setCantidadExistente(Float.valueOf("0"));
        selected.setCostoPorUnidad(Float.valueOf("0"));
        selected.setStockEstimation(Float.valueOf("0"));
        selected.setUm(R.UM.U.toString());
        popupDialog();

    }

    private void popupDialog() {
        newInsumoView = new InsumoCreateEditView(this, view, true);
        AlmacenDAO almacenConn = new AlmacenDAO();
        newInsumoView.updateView(super.getItems(), selected, almacenConn.findAll());
        newInsumoView.setVisible(true);
    }

    public void createInsumo(Insumo insumo) {
        int resp = JOptionPane.showConfirmDialog(newInsumoView, R.RESOURCE_BUNDLE.getString("insumo_nuevo") + " " + insumo.getNombre());
        if (resp == JOptionPane.YES_OPTION) {
            selected = insumo;
            super.getFacade().startTransaction();
            create();
            super.getFacade().commitTransaction();
            JOptionPane.showMessageDialog(newInsumoView, R.RESOURCE_BUNDLE.getString("insumo_creado_correctamente"));
            newInsumoView.dispose();
            newInsumoView = null;
            view.updateView(getItems());
        }
    }

    public void disposeNewEditView() {
        newInsumoView.dispose();
        newInsumoView = null;
    }

    public void deleteInsumo() {
        if (selected != null) {
            int resp = JOptionPane.showConfirmDialog(view, R.RESOURCE_BUNDLE.getString("insumo_borrar" ) + " " + selected.getNombre());
            if (resp == JOptionPane.YES_OPTION) {
                super.getFacade().startTransaction();
                destroy();
                super.getFacade().commitTransaction();
                JOptionPane.showMessageDialog(view, R.RESOURCE_BUNDLE.getString("insumo_borrado_correctamente"));
                view.updateView(getItems());
            }
        }
    }

    public void editInsumo() {
        popupDialog();
    }

}
