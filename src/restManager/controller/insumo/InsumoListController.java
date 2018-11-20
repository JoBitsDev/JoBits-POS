/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.insumo;

import GUI.Views.AbstractView;
import GUI.Views.Insumo.InsumoCreateEditView;
import GUI.Views.Insumo.InsumoListView;
import GUI.Views.View;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Window;
import java.util.List;
import javax.swing.JOptionPane;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Almacen;
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
public class InsumoListController extends AbstractListController<Insumo> {

    private final String PREFIX_FOR_ID = "In-";
    private InsumoCreateEditView newInsumoView;

    public InsumoListController() {
        super(new InsumoDAO());
    }

    public InsumoListController(Window frame) {
        this();
        constructView(frame);
    }

    @Override
    public void createInstance() {
        popupInsumoNewView();
    }

    @Override
    public void update(Insumo selected) {
        editInsumo();
    }

    @Override
    public void destroy(Insumo selected) {
        deleteInsumo();
    }

    @Override
    public void constructView(Window parent) {
        setView(new InsumoListView(this, (Frame) parent, true));
        getView().updateView();
        getView().setVisible(true);
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
        newInsumoView = new InsumoCreateEditView(this, getView(), true,getSelected());
        AlmacenDAO almacenConn = new AlmacenDAO();
        newInsumoView.updateView();
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
            getView().updateView();
        }
    }

    public void disposeNewEditView() {
        newInsumoView.dispose();
        newInsumoView = null;
        items = null;
        getView().updateView();
    }

    public void deleteInsumo() {
        if (selected != null) {
            int resp = JOptionPane.showConfirmDialog(getView(),
                    R.RESOURCE_BUNDLE.getString("insumo_borrar") + " " + selected.getNombre());
            if (resp == JOptionPane.YES_OPTION) {
                destroy();
                JOptionPane.showMessageDialog(getView(),
                        R.RESOURCE_BUNDLE.getString("insumo_borrado_correctamente"));
                getView().updateView();
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
            if(getView() != null){
            getView().updateView();
            }
        }
    }
    


    public List<Almacen> getAlmacenList() {
        return new AlmacenDAO().findAll();
    }

    public void crossReferenceInsumo(Insumo objectAtSelectedRow) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Insumo> getDetailControllerForNew() {
throw new DevelopingOperationException();
    }

    @Override
    public AbstractDetailController<Insumo> getDetailControllerForEdit(Insumo selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
