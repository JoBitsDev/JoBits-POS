/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.Almacen.AlmacenListView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.exceptions.NoSelectedException;
import restManager.persistencia.Almacen;
import restManager.persistencia.models.AlmacenDAO;
import restManager.resources.R;
import restManager.resources.RegularExpressions;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AlmacenListController extends AbstractListController<Almacen> {

    private final String PREFIX_FOR_ID = "A-";

    public AlmacenListController() {
        super(AlmacenDAO.getInstance());
    }

    public AlmacenListController(Window parent) {
        this();
        constructView(parent);
    }

    @Override
    public void createInstance() {
        createNewStorage();
    }

    @Override
    public void destroy(Almacen selected) {
        setSelected(selected);
        deleteSelectedStorage();
    }

    @Override
    public void update(Almacen selected) {
        setSelected(selected);
        openSelectedStorage();
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new AlmacenListView(this, (Dialog) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    public void createNewStorage() {

        String storageName = JOptionPane.showInputDialog(R.RESOURCE_BUNDLE.getString("dialogo_agregar_almacen"));
        if (storageName != null) {
            if (storageName.matches(RegularExpressions.ONLY_WORDS_SEPARATED_WITH_SPACES)) {
                selected = new Almacen();
                selected.setInsumoAlmacenList(new ArrayList<>());
                selected.setCantidadInsumos(0);
                selected.setValorMonetario(Float.parseFloat("0"));
                selected.setCodAlmacen(super.getModel().generateStringCode(PREFIX_FOR_ID));
                selected.setNombre(storageName);

                create();
                showSuccessDialog(getView());
                getView().updateView();
            } else {
                //TODO: implementar exepciones
            }
        }
    }

    public void deleteSelectedStorage() {
        if (selected == null) {
            throw new NoSelectedException();
        } else {
            int resp = JOptionPane.showConfirmDialog(getView(), R.RESOURCE_BUNDLE.getString("dialogo_borrar_almacen") + " " + selected.getNombre());
            if (resp == JOptionPane.YES_OPTION) {
                destroy();
                showSuccessDialog(getView());
                getView().updateView();
            }
        }
    }

    public void openSelectedStorage() {
        if (selected == null) {
            throw new NoSelectedException();
        } else {
            AlmacenManageController manageController = new AlmacenManageController(getView(), selected);
        }

    }

    @Override
    public AbstractDetailController<Almacen> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Almacen> getDetailControllerForEdit(Almacen selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

}
