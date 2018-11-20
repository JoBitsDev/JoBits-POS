/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.trabajadores;

import GUI.Views.trabajadores.PersonalCreateEditView;
import java.awt.Dialog;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.persistencia.Personal;
import restManager.persistencia.PuestoTrabajo;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.PersonalDAO;
import restManager.persistencia.models.PuestoTrabajoDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class PersonalCreateEditController extends AbstractDetailController<Personal>{

    public PersonalCreateEditController() {
        super(new PersonalDAO());
    }

    public PersonalCreateEditController(Personal instance) {
        super(instance, new PersonalDAO());
    }

    public PersonalCreateEditController(Window parent) {
        super(parent, new PersonalDAO());
    }

    public PersonalCreateEditController(Personal instance, Window parent) {
        super(instance, parent, new PersonalDAO());
    }

    
    @Override
    public void constructView(Window parent) {
        setView( new PersonalCreateEditView(instance, this, (Dialog) parent, true));
        getView().setVisible(true);
    }

    @Override
    public Personal createNewInstance() {
        Personal ret = new Personal();
        ret.setPuestoTrabajoList(new ArrayList<>());
        ret.setOrdenList(new ArrayList<>());
        return ret;
    }

  public List<PuestoTrabajo> getPuestoTrabajoList(){
      return new PuestoTrabajoDAO().findAll();
  }


    
    
    

}
