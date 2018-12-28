/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.seccion;

import GUI.Views.seccion.SeccionListView;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.exceptions.DevelopingOperationException;
import restManager.persistencia.Seccion;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.SeccionDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class SeccionListController extends AbstractListController<Seccion> {

    public SeccionListController() {
        super(new SeccionDAO());
    }

    public SeccionListController(Frame parent) {
        super(new SeccionDAO());
        constructView(parent);
    }

    @Override
    public void createInstance() {
        String nombre = JOptionPane.showInputDialog(getView(), "Introduzca el nombre de la sección a crear",
                "Nueva Sección", JOptionPane.QUESTION_MESSAGE);
        Seccion newSeccion = new Seccion();
        newSeccion.setDescripcion("");
        newSeccion.setNombreSeccion(nombre);
        newSeccion.setProductoVentaList(new ArrayList<>());

        if (nombre != null && !nombre.isEmpty()) {
            if (validate(newSeccion)) {
                create(newSeccion);
            } else {
                showErrorDialog(getView(), "La sección a crear ya existe");
            }
        }
    }

    @Override
    public void update(Seccion selected) {
         String nombre = JOptionPane.showInputDialog(getView(), "Introduzca el nuevo nombre de la sección",
                "Editar Sección", JOptionPane.QUESTION_MESSAGE);
         selected.setNombreSeccion(nombre);
        if (nombre != null && !nombre.isEmpty()) {
            if (validate(selected)) {
                update(selected);
            } else {
                showErrorDialog(getView(), "La sección a crear ya existe");
            }
        }
    }
    
    @Override
    public AbstractDetailController<Seccion> getDetailControllerForNew() {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractDetailController<Seccion> getDetailControllerForEdit(Seccion selected) {
        throw new DevelopingOperationException(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param parent the value of parent
     */
    @Override
    public void constructView(java.awt.Container parent) {
        setView(new SeccionListView(this, (Frame) parent, true));
        getView().updateView();
        getView().setVisible(true);
    }

    private boolean validate(Seccion newSeccion) {
        return getItems().stream().noneMatch((x)
                -> (x.getNombreSeccion().toLowerCase().equals(newSeccion.getNombreSeccion().toLowerCase())));
    }

}
