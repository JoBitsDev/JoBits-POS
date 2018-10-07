/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.insumo;

import GUI.Views.Insumo.InsumoCreateEditView;
import GUI.Views.Insumo.InsumoListView;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JOptionPane;
import restManager.controller.AbstractController;
import restManager.persistencia.Insumo;
import restManager.persistencia.models.AlmacenDAO;
import restManager.persistencia.models.InsumoDAO;
import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class InsumoListController extends AbstractController<Insumo> {

    private InsumoListView view;
    private final String PREFIX_FOR_ID = "In-";
    private InsumoCreateEditView newInsumoView;

    public InsumoListController() {
        super(new InsumoDAO());
    }

    public InsumoListController(Frame frame) {
        this();
        constructView(frame);
    }

    @Override
    public void createInstance() {
        popupInsumoNewView();
    }

    @Override
    public void editInstance(Insumo selected) {
        editInsumo();
    }

    @Override
    public void deleteInstance(Insumo selected) {
        deleteInsumo();
    }

    @Override
    public void constructView(Window parent) {
        view = new InsumoListView(this, (Frame) parent, true);
        view.updateView(super.getItems());
        view.setVisible(true);
    }

    public void popupInsumoNewView() {
        selected = new Insumo(super.getModel().generateStringCode(PREFIX_FOR_ID));
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
        newInsumoView = null;
    }

    public void createInsumo(Insumo insumo) {
        int resp = JOptionPane.showConfirmDialog(newInsumoView,
                R.RESOURCE_BUNDLE.getString("insumo_nuevo") + " " + insumo.getNombre());
        if (resp == JOptionPane.YES_OPTION) {
            create();
            JOptionPane.showMessageDialog(newInsumoView,
                    R.RESOURCE_BUNDLE.getString("insumo_creado_correctamente"));
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
            int resp = JOptionPane.showConfirmDialog(view,
                    R.RESOURCE_BUNDLE.getString("insumo_borrar") + " " + selected.getNombre());
            if (resp == JOptionPane.YES_OPTION) {
                destroy();
                JOptionPane.showMessageDialog(view,
                        R.RESOURCE_BUNDLE.getString("insumo_borrado_correctamente"));
                view.updateView(getItems());
            }
        }
    }

    public void editInsumo() {
        popupDialog();
    }

    public void updateInsumo(Insumo insumo) {
        int resp = JOptionPane.showConfirmDialog(newInsumoView,
                R.RESOURCE_BUNDLE.getString("insumo_act") + " " + insumo.getNombre());
        if (resp == JOptionPane.YES_OPTION) {
            update();
            JOptionPane.showMessageDialog(newInsumoView,
                    R.RESOURCE_BUNDLE.getString("insumo_actualizado_correctamente"));
            newInsumoView.dispose();
            newInsumoView = null;
            if(view != null){
            view.updateView(getItems());
            }
        }
    }

}
