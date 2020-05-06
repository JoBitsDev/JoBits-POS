/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.ui.seccion;

import com.jobits.pos.ui.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import com.jobits.pos.controller.AbstractListController;
import com.jobits.pos.persistencia.Seccion;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class SeccionListView extends AbstractListView<Seccion>{

    public SeccionListView(AbstractListController<Seccion> controller, Frame parent, boolean modal) {
        super(controller, parent, modal);
    }

    public SeccionListView(AbstractListController<Seccion> controller, Dialog parent, boolean modal) {
        super(controller, parent, modal);
    }

    
    
    @Override
    public MyJTableModel<Seccion> generateTableModel(List<Seccion> items) {
        return new MyJTableModel<Seccion>(items) {
            @Override
            public int getColumnCount() {
                return 2;
            }
            
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch(columnIndex){
                    case 0: return items.get(rowIndex).getNombreSeccion();
                    case 1: return items.get(rowIndex).getProductoVentaList().size();
                    default: return null;
                }
            }
            
            @Override
            public String getColumnName(int column) {
                switch(column){
                    case 0: return "Nombre";
                    case 1: return "Productos de venta";
                    default:return null;
                }
            }
        };
    }

}
