/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.trabajadores;


import GUI.Views.trabajadores.PersonalListView;
import java.awt.Frame;
import java.awt.Window;
import java.util.AbstractList;
import restManager.controller.AbstractController;
import restManager.controller.AbstractDetailController;
import restManager.controller.AbstractListController;
import restManager.persistencia.Personal;
import restManager.persistencia.models.PersonalDAO;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class PersonalListController extends AbstractListController<Personal> {

    public PersonalListController() {
        super(new PersonalDAO());
    }

    public PersonalListController(Frame frame) {
        this();
        constructView(frame);
    }

    @Override
    public void constructView(Window parent) {
        setView(new PersonalListView(this, (Frame) parent, true));
        getView().updateView();
        getView().setVisible(true);

    }

    @Override
    public AbstractDetailController<Personal> getDetailControllerForNew() {
    return new PersonalCreateEditController(getView());
    }

    @Override
    public AbstractDetailController<Personal> getDetailControllerForEdit(Personal selected) {
    return new PersonalCreateEditController(selected, getView());
    }
    
    

}
