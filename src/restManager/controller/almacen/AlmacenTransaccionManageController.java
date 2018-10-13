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
import java.util.Comparator;
import java.util.List;
import javax.swing.JDialog;
import restManager.controller.AbstractController;
import restManager.persistencia.Almacen;
import restManager.persistencia.Insumo;
import restManager.persistencia.Transaccion;
import restManager.persistencia.models.InsumoDAO;
import restManager.persistencia.models.TransaccionDAO;
import restManager.resources.R;

/**
 * FirstDream
 * @author Jorge
 * 
 */
class AlmacenTransaccionManageController extends AbstractController<Transaccion>{

    private AlmacenTransaccionEditView view;
    private  Transaccion f;
    
    public AlmacenTransaccionManageController(Transaccion f) {
        super(new TransaccionDAO());
        this.f = f;
    }

    public AlmacenTransaccionManageController(Window parent,Transaccion f) {
        this(f);
        constructView(parent);
    }
    
    @Override
    public void constructView(Window parent) {
        ArrayList<Insumo> insumoList = new ArrayList<>(new InsumoDAO().findAll());
        insumoList.sort((Insumo o1, Insumo o2) -> o1.getNombre().compareTo(o2.getNombre()));
        
        if(parent instanceof JDialog){
             view = new AlmacenTransaccionEditView(f,insumoList,this,(JDialog) parent, true);
        }else{
            view = new AlmacenTransaccionEditView(f,insumoList,this, (Frame) parent, true);
        }
        view.updateView(f);
        view.setVisible(true);
    }

    @Override
    public void createInstance(Transaccion selected) {
        if(validateData(selected)){
        super.createInstance(selected); //To change body of generated methods, choose Tools | Templates.
        view.dispose();
        view = null;
        }
    }

    
     private boolean validateData(Transaccion trans) {
        for (int i = 0; i < trans.getInsumoTransaccionList().size() - 1; i++) {
            String transType = trans.getInsumoTransaccionList().get(i).getTipoTransaccion();
            String nombre = trans.getInsumoTransaccionList().get(i).getInsumo().getNombre();
            for (int j = i + 1; j < trans.getInsumoTransaccionList().size(); j++) {
                if (transType.equals(trans.getInsumoTransaccionList().get(j).getTipoTransaccion())
                        && nombre.equals(trans.getInsumoTransaccionList().get(j).getInsumo().getNombre())) {
                    showErrorDialog(view, R.RESOURCE_BUNDLE.getString("error_duplicacion")+"("+nombre +")");
                    return false;
                }
            }
        }
        return true;
    }
    
   
    

}
