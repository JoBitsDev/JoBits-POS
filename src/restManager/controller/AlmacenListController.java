/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller;


import GUI.Views.Almacen.AlmacenListView;
import java.awt.Frame;
import javax.swing.JOptionPane;
import restManager.persistencia.Almacen;
import restManager.persistencia.beans.AlmacenDAO;
import restManager.resources.R;

/**
 * FirstDream
 * @author Jorge
 * 
 */



public class AlmacenListController extends AbstractController<Almacen>{
    
    private final AlmacenListView view;
    private final String PREFIX_FOR_ID = "A-";
    
     public AlmacenListController(Frame parent) {
        super(new AlmacenDAO());
        view = new AlmacenListView(this,parent, true);
        view.updateView(super.getItems());
        view.setVisible(true);
    }

     public void createNewStorage(){
         
        String storageName =  JOptionPane.showInputDialog(R.RESOURCE_BUNDLE.getString("dialogo_agregar_almacen"));
         
        if(!storageName.isEmpty() ){
          selected =  new Almacen();
          selected.setFichaList(null);
          selected.setCantidadInsumos(0);
          selected.setCodAlmacen(super.getFacade().generateStringCode(PREFIX_FOR_ID));
          selected.setNombre(storageName);
          selected.setInsumoList(null);
          
          super.getFacade().startTransaction();
          super.create();
          super.getFacade().commitTransaction();
          
          JOptionPane.showMessageDialog(view, R.RESOURCE_BUNDLE.getString("almacen_agregado_correctamente"));
          selected = null;
          view.updateView(getItems());
         }
        else{
            //TODO: implementar exepciones
        }
     }

    public void deleteSelectedStorage() {
        if(selected == null){
            //TODO: implementar exepciones
        }
        else{
            
        }
    }

    public void openSelectedStorage() {
         if(selected == null){
            //TODO: implementar exepciones
        }
         else{
             
         }

    }

   
    
    
    
    
    
    
    
    

}
