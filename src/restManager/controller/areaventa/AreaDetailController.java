/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.areaventa;

import GUI.Views.AbstractDetailView;
import GUI.Views.AbstractView;
import GUI.Views.areaventa.AreaCreateEditView;
import java.awt.Container;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.exceptions.ValidatingException;
import restManager.persistencia.Area;
import restManager.persistencia.Carta;
import restManager.persistencia.models.AbstractModel;
import restManager.persistencia.models.AreaDAO;
import restManager.persistencia.models.CartaDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class AreaDetailController extends AbstractDetailController<Area>{

    public AreaDetailController(Area instance, AbstractModel<Area> model) {
        super(instance, AreaDAO.getInstance());
    }

    public AreaDetailController(Window parent) {
        super(parent, AreaDAO.getInstance());
    }

    public AreaDetailController(Area instance, Window parent) {
        super(instance, parent, AreaDAO.getInstance());
    }
    
    @Override
    public Area createNewInstance() {
        Area ret = new Area();
        ret.setCapacidad(0);
        ret.setCartaList(new ArrayList<>());
        ret.setCodArea(getModel().generateStringCode("A-"));
        ret.setMesaList(new ArrayList<>());
        ret.setNombre("Nueva Area");
        ret.setPorcientoPorServicio(0);
        return ret;
    }

    @Override
    public void createUpdateInstance() {
         if (getView().validateData()) {
            switch (state) {
                case CREATING:
                    create(instance);
                    break;
                case EDITING:
                    
                    update(instance);
                    break;
            }
        }else{
            throw new ValidatingException();
        }
    }

    
    
    @Override
    public void constructView(Container parent) {
        setView(new AreaCreateEditView(getInstance(), this, (AbstractView) parent));
        getView().updateView();
        getView().setVisible(true);
    }

    public List<Carta> getCartaList(){
        return CartaDAO.getInstance().findAll();
    }

    @Override
    public AbstractDetailView<Area> getView() {
        return (AbstractDetailView<Area>) super.getView(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
