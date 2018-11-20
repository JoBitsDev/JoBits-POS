/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Views.Almacen;

import GUI.Views.AbstractListView;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.List;
import restManager.controller.AbstractController;
import restManager.controller.AbstractListController;
import restManager.persistencia.Almacen;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class AlmacenListView extends AbstractListView<Almacen>{

    public AlmacenListView(AbstractListController<Almacen> controller, Frame owner, boolean modal) {
        super(controller, owner, modal);
    }

    public AlmacenListView(AbstractListController<Almacen> controller, Dialog owner, boolean modal) {
        super(controller, owner, modal);
    }

    @Override
    public MyJTableModel<Almacen> generateTableModel(List<Almacen> items) {
        return new MyJTableModel<Almacen>(items) {
            @Override
            public int getColumnCount() {
                return 2;
            }
            
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch(columnIndex){
                    case 0: return items.get(rowIndex);
                    case 1: return items.get(rowIndex).getValorMonetario();
                    default: return null;
                }
            }
            
            @Override
            public String getColumnName(int column) {
                switch(column){
                    case 0: return "Nombre";
                    case 1: return "Valor Monetario";
                    default: return null;
                }
            }
        };
    }

}
