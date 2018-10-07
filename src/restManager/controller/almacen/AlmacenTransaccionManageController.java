/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.controller.almacen;

import GUI.Views.Almacen.AlmacenTransaccionEditDialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JDialog;
import restManager.controller.AbstractController;
import restManager.persistencia.Almacen;
import restManager.persistencia.Transaccion;
import restManager.persistencia.models.TransaccionDAO;

/**
 * FirstDream
 * @author Jorge
 * 
 */
class AlmacenTransaccionManageController extends AbstractController<Almacen>{

    private AlmacenTransaccionEditDialog view;
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
        if(parent instanceof JDialog){
             view = new AlmacenTransaccionEditDialog(this,(JDialog) parent, true, f);
        }else{
            view = new AlmacenTransaccionEditDialog(this, (Frame) parent, true, f);
        }
        view.updateView(f);
        view.setVisible(true);
    }

   
    

}
