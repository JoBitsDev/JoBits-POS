/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.almacen;

import GUI.Views.Almacen.AlmacenListView;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JOptionPane;
import restManager.controller.AbstractController;
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
public class AlmacenListController extends AbstractController<Almacen> {

    private AlmacenListView view;
    private final String PREFIX_FOR_ID = "A-";

    public AlmacenListController() {
        super(new AlmacenDAO());
    }

    public AlmacenListController(Frame parent) {
        this();
        constructView(parent);
    }

    @Override
    public void createInstance() {
        createNewStorage();
    }

    @Override
    public void deleteInstance(Almacen selected) {
        setSelected(selected);
        deleteSelectedStorage();
    }

    @Override
    public void editInstance(Almacen selected) {
        setSelected(selected);
        openSelectedStorage();
    }
    
    @Override
    public void constructView(Window parent) {
        view = new AlmacenListView(this, (Frame) parent, true);
        view.updateView(super.getItems());
        view.setVisible(true);
    }

    public void createNewStorage() {

        String storageName = JOptionPane.showInputDialog(R.RESOURCE_BUNDLE.getString("dialogo_agregar_almacen"));

        if (storageName.matches(RegularExpressions.ONLY_WORDS_SEPARATED_WITH_SPACES)) {
            selected = new Almacen();
            selected.setFichaList(null);
            selected.setCantidadInsumos(0);
            selected.setValorMonetario(Float.parseFloat("0"));
            selected.setCodAlmacen(super.getModel().generateStringCode(PREFIX_FOR_ID));
            selected.setNombre(storageName);
            selected.setInsumoList(null);

            create();
            showSuccessDialog(view);
            view.updateView(getItems());
        } else {
            //TODO: implementar exepciones
        }
    }

    public void deleteSelectedStorage() {
        if (selected == null) {
            throw new NoSelectedException();
        } else {
            int resp = JOptionPane.showConfirmDialog(view, R.RESOURCE_BUNDLE.getString("dialogo_borrar_almacen") + " " + selected.getNombre());
            if (resp == JOptionPane.YES_OPTION) {
                destroy();
                showSuccessDialog(view);
                view.updateView(getItems());
            }
        }
    }

    public void openSelectedStorage() {
        if (selected == null) {
            throw new NoSelectedException();
        } else {
            AlmacenManageController manageController = new AlmacenManageController(view, selected);
        }

    }

}
